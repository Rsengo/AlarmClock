package Library.Signals;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ytgv8b.firsttry.Services.NotificationReceiver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Library.DataHelpers.DataBaseHelper;
import Library.Signals.SignalBuilders.NotificationBuilder;
import Library.Signals.SignalBuilders.SignalFactory;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 16.10.2017.
 */

public class Notification extends RealmObject implements INotification {
    /****Методы интерфейсов****/
    /***Обратная связь с юзером***/

    @PrimaryKey
    private int id;
    @Required
    private String name = ""; //Имя
    private byte generalPeriodicity = Notification.PEREODICITY_NOREPEAT; //Общая переодичность
    private byte specificPeriodicity = 0; //Подробная
    private Date closeDate = null; //Ближайшая дата
    private byte priority = PRIORITY_DEFAULT; //Приоритет
    @Required
    private Date signalTime = new Date(); //Время запуска
    private long repeatSignalInterval = 0; //интервал повтора
    // (Константа AlarmManager)
    private String description = null; //Описание
    @Required
    private String userEmail; //Почта пользователя-владельца

    public static final byte PEREODICITY_NOREPEAT = 0;
    public static final byte PEREODICITY_EVERYHOUR = 1;
    public static final byte PEREODICITY_EVERYDAY = 2;
    public static final byte PEREODICITY_EVERYWEEK = 3;
    public static final byte PEREODICITY_EVERYMOUNTH = 4;
    public static final byte PEREODICITY_EVERYYEAR = 5;

    public static final long INTERVAL_NOREPEAT = 0;
    public static final long INTERVAL_HALF_DAY = AlarmManager.INTERVAL_HALF_DAY;
    public static final long FIFITEEN_MINUTES = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
    public static final long INTEVAL_HALF_HOUR = AlarmManager.INTERVAL_HALF_HOUR;
    public static final long INTEVAL_HOUR = AlarmManager.INTERVAL_HOUR;

    public static final byte PRIORITY_LOW = 0;
    public static final byte PRIORITY_DEFAULT = 1;
    public static final byte PRIORITY_HIGH = 2;

    public static int getNextId() {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        return dataBaseHelper.getNextId(Notification.class);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public byte getGeneralPeriodicity() {
        return generalPeriodicity;
    }

    public void setGeneralPeriodicity(byte generalPeriodicity) {
        this.generalPeriodicity = generalPeriodicity;
    }

    public byte getSpecificPeriodicity() {
        return specificPeriodicity;
    }

    public void setSpecificPeriodicity(byte specificPeriodicity) {
        this.specificPeriodicity = specificPeriodicity;
    }

    @Override
    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    @Override
    public Date getSignalTime() {
        return signalTime;
    }

    public void setSignalTime(Date signalTime) {
        this.signalTime = signalTime;

        if (System.currentTimeMillis() > signalTime.getTime())
            recountSignalTime();

        closeDate = signalTime;
    }

    public long getRepeatSignalInterval() {
        return repeatSignalInterval;
    }

    public void setRepeatSignalInterval(long repeatSignalInterval) {
        this.repeatSignalInterval = repeatSignalInterval;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    private void recountSignalTime() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(closeDate);

        switch (generalPeriodicity) {
            case (PEREODICITY_NOREPEAT):
                break;
            case (PEREODICITY_EVERYHOUR):
                calendar.add(Calendar.HOUR, specificPeriodicity);
                break;
            case (PEREODICITY_EVERYDAY):
                calendar.add(Calendar.DAY_OF_YEAR, specificPeriodicity);
                break;
            case (PEREODICITY_EVERYMOUNTH):
                calendar.add(Calendar.MONTH, specificPeriodicity);
                break;
            case (PEREODICITY_EVERYYEAR
            ):
                calendar.add(Calendar.YEAR, specificPeriodicity);
                break;
        }

        closeDate = calendar.getTime();
    }

    @Override
    public void recountSignalTime(Context context) {
        turnOn(context);
    }

    @Override
    public void turnOn(Context context) {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = createIntent(context);

        long signalTime = this.closeDate.getTime();

        try {
            if (repeatSignalInterval != 0)
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, signalTime,
                        this.repeatSignalInterval, pendingIntent);
            else
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, signalTime, pendingIntent);
        } catch (NullPointerException ex) {
            Log.e("Notification turn on", ex.getMessage());
        }

    }

    @Override
    public void turnOff(Context context) {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = createIntent(context);

        alarmManager.cancel(pendingIntent);
    }

    @Override
    public long remainingTimeInMillis() {
        Date tempDate = new Date();
        return closeDate.getTime() - tempDate.getTime();
    }

    private PendingIntent createIntent(Context context) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("id", id);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, id, intent, 0);

        return pendingIntent;
    }

    @NonNull
    public static NotificationBuilder builder() {
        return new NotificationBuilder();
    }
}
