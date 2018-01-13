package Library.Signals;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ytgv8b.firsttry.Services.NotificationReceiver;

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
    private byte generalPeriodicity = Notification.NOREPEAT; //Общая переодичность
    private byte specificPeriodicity = 0; //Подробная
    private Date closeDate = null; //Ближайшая дата
    private byte priority = PRIORITY_DEFAULT; //Приоритет
    @Required
    private Date signalTime = new Date(); //Время запуска
    private long repeatSignalInterval = AlarmManager.INTERVAL_DAY; //интервал повтора
    // (Константа AlarmManager)
    private String description = null; //Описание
    @Required
    private String userEmail; //Почта пользователя-владельца

    public static final byte NOREPEAT = 0;
    public static final byte REPEAT_EVERYHOUR = 1;
    public static final byte REPEAT_EEVEYDAY = 2;
    public static final byte REPEAT_EVERYWEEK = 3;
    public static final byte REPEAT_EVERYMOUNTH = 4;
    public static final byte REPEAT_EVERYYEAR = 5;

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

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Date getSignalTime() {
        return signalTime;
    }

    public void setSignalTime(Date signalTime) {
        this.signalTime = signalTime;
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

    @Override
    public void recountSignalTime(Context context) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(closeDate);

        switch (generalPeriodicity) {
            case (NOREPEAT):
                break;
            case (REPEAT_EVERYHOUR):
                calendar.add(Calendar.HOUR, specificPeriodicity);
                break;
            case (REPEAT_EEVEYDAY):
                calendar.add(Calendar.DAY_OF_YEAR, specificPeriodicity);
                break;
            case (REPEAT_EVERYMOUNTH):
                calendar.add(Calendar.MONTH, specificPeriodicity);
                break;
            case (REPEAT_EVERYYEAR):
                calendar.add(Calendar.YEAR, specificPeriodicity);
                break;
        }

        closeDate = calendar.getTime();

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
                PendingIntent.getActivity(context, id, intent, 0);

        return pendingIntent;
    }

    @NonNull
    public static SignalFactory builder() {
        return new NotificationBuilder();
    }
}
