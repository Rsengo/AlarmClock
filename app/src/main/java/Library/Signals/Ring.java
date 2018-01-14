package Library.Signals;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import Library.Signals.SignalBuilders.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Library.DataHelpers.DataBaseHelper;
import Library.Messages.IMessage;
import Library.PuzzlesThings.PuzzleFactory;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 16.10.2017.
 */


public class Ring extends RealmObject implements IRing{

    @PrimaryKey
    private int id;

    private byte puzzle = PUZZLE_DEFAULT; //головоломка
    @Required
    private Date signalTime = null; //Время запуска
    private long repeatSignalInterval = 600000L; //интервал повторного запуска после откладывания
    private boolean vibrating = true; //вибрация(Вибрирующий)
    private String melody; //мелодия
    @Ignore
    private static final long turnOffTime = 300000L; //Время автовыключения в мс (default = 5 мин)
    private String description = "Будильник"; //Описание
    private boolean onState = true; //Вкл/Выкл звукового сигнала
    private boolean deleteAfterUsing = false; //удалить после срабатывания
    private byte[] repeatDays = null; //Дни повтора

    @Required
    private String userEmail; //Почта пользователя владельца

    @Ignore
    private IMessage message = null; //СМС

    public static byte PUZZLE_DEFAULT = 0;
    public static byte PUZZLE_CALCULATE = 1;
    public static byte PUZZLE_CONNECT = 2;

    public static int getNextId() {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        return dataBaseHelper.getNextId(Ring.class);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setRepeatDays(byte[] repeatDays) {
        this.repeatDays = repeatDays;
    }

    public byte getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(byte puzzle) {
        this.puzzle = puzzle;
    }

    public IMessage getMessage() { //Null obj pattern
        if (message == null)
        {
            DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
            message = dataBaseHelper.loadMesssage(id);
        }
        return message;
    }

    public void setMessage (IMessage message) {
        this.message = message;
    }

    public byte[] getRepeatDays() {
        return repeatDays;
    }

    @Override
    public Date getSignalTime() {
        return signalTime;
    }

    public void setSignalTime(Date signalTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(signalTime);
        calendar.set(Calendar.SECOND, 0);
        this.signalTime = calendar.getTime();

        if (repeatDays == null)
            recountUnrepeatable();
        else
            recountRepeatable();
    }

    public long getRepeatSignalInterval() {
        return repeatSignalInterval;
    }

    public void setRepeatSignalInterval(long repeatSignalInterval) {
        this.repeatSignalInterval = repeatSignalInterval;
    }

    @Override
    public boolean isVibrating() {
        return vibrating;
    }

    public void setVibration(boolean vibrating) {
        this.vibrating = vibrating;
    }

    @Override
    public String getMelody() {
        return melody;
    }

    public void setMelody(String melody) {
        this.melody = melody;
    }

    @Override
    public long getTurnOffTime() {
        return turnOffTime;
    }

    @Override
    public boolean isOnState() {
        return onState;
    }

    public void setOnState(boolean onState) {
        this.onState = onState;
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
    public boolean isDeleteAfterUsing() {
        return deleteAfterUsing;
    }

    public void setDeleteAfterUsing(boolean deleteAfterUsing) {
        this.deleteAfterUsing = deleteAfterUsing;
    }

    @Override
    public void postpound(Context context) {  //отложить

        sendMessage();

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = createIntent(context);

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND, (int)repeatSignalInterval);
        Date closeDate = calendar.getTime();

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, closeDate.getTime(), pendingIntent);
    }

    @Override
    public void sendMessage()
    {
        if (message != null)
            message.send();
    }

    private void recountUnrepeatable () {
        GregorianCalendar tempTime = new GregorianCalendar();
        GregorianCalendar newSignalTime = new  GregorianCalendar();

        newSignalTime.setTime(signalTime);
        newSignalTime.set(Calendar.YEAR, tempTime.get(Calendar.YEAR));
        newSignalTime.set(Calendar.MONTH, tempTime.get(Calendar.MONTH));
        newSignalTime.set(Calendar.DAY_OF_YEAR, tempTime.get(Calendar.DAY_OF_YEAR));

        if (newSignalTime.before(tempTime)) {
            newSignalTime.add(Calendar.DAY_OF_YEAR, 1);
        }

        signalTime = newSignalTime.getTime();
    }

    private void recountRepeatable() {
        GregorianCalendar signalCalendar = new GregorianCalendar();
        GregorianCalendar tempCalendar = new GregorianCalendar();
        signalCalendar.setTime(signalTime);

        if (tempCalendar.before(signalCalendar))
            return;

        signalCalendar.add(Calendar.DAY_OF_WEEK, 1);
        int tempDay = signalCalendar.get(Calendar.DAY_OF_WEEK);
        int additionalDays = 1;

        while (repeatDays[tempDay] == 0) {
            tempDay++;
            additionalDays++;

            if (tempDay > 6)
                tempDay = 0;
        }

        signalCalendar.add(Calendar.DAY_OF_YEAR, additionalDays);

        signalTime = signalCalendar.getTime();
    }

    @Override
    public void turnOn(Context context) {
        onState = true;

        if (repeatDays == null) {
            recountUnrepeatable();
        } else {
            recountRepeatable();
        }

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = createIntent(context);

        long signalTime = this.signalTime.getTime();

        try {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, signalTime, pendingIntent);
        } catch (NullPointerException ex) {
            Log.e("Ring turn on", ex.getMessage());
        }
    }

    @Override
    public void turnOff(Context context) {
        onState = false;

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = createIntent(context);

        alarmManager.cancel(pendingIntent);
    }

    @Override
    public void recountSignalTime(Context context) {
        if (repeatDays == null) {
            setOnState(false);
            return;
        }

        recountRepeatable();

        turnOn(context);
    }

    private PendingIntent createIntent(Context context) {
        Class puzzleClass = PuzzleFactory.getFactory(puzzle);

        Intent intent = new Intent(context, puzzleClass);
        intent.putExtra("id", id);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, id, intent, 0);

        return pendingIntent;
    }

    @Override
    public long remainingTimeInMillis() {
        Date tempTime = new Date();
        return signalTime.getTime() - tempTime.getTime();
    }

    @NonNull
    public static RingBuilder builder() {
        return new RingBuilder();
    }
}
