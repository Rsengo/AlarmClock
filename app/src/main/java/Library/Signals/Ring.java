package Library.Signals;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


import com.example.ytgv8b.firsttry.MainActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Library.DataHelpers.DataBaseHelper;
import Library.IRealmModelWithID;
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
    /****добавить абстракции для головоломки****/
    /****Написать метод отложить будьник****/

    @PrimaryKey
    private int id;

    private byte puzzle; //головоломка
    private Date signalTime; //Время запуска
    private byte repeatSignalInterval; //интервал повторного запуска после откладывания в мин
    private boolean vibrating; //вибрация(Вибрирующий)
    private int melody; //мелодия
    private byte melodyVolume; //громкость
    private Date turnOffTime; //Время автовыключения звукового сигнала
    private String description; //Описание
    private boolean onState; //Вкл/Выкл звукового сигнала
    private boolean deleteAfterUsing; //удалить после срабатывания
    private byte[] repeatDays; //Дни повтора

    @Required
    private String userEmail; //Почта пользователя-владельца

    @Ignore
    private IMessage message; //СМС

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

    public Date getSignalTime() {
        return signalTime;
    }

    public void setSignalTime(Date signalTime) {
        this.signalTime = signalTime;
    }

    public byte getRepeatSignalInterval() {
        return repeatSignalInterval;
    }

    public void setRepeatSignalInterval(byte repeatSignalInterval) {
        this.repeatSignalInterval = repeatSignalInterval;
    }

    public boolean isVibrating() {
        return vibrating;
    }

    public void setVibration(boolean vibrating) {
        this.vibrating = vibrating;
    }

    public int getMelody() {
        return melody;
    }

    public void setMelody(int melody) {
        this.melody = melody;
    }

    public byte getMelodyVolume() {
        return melodyVolume;
    }

    public void setMelodyVolume(byte melodyVolume) {
        this.melodyVolume = melodyVolume;
    }

    public Date getTurnOffTime() {
        return turnOffTime;
    }

    public void setTurnOffTime(Date turnOffTime) {
        this.turnOffTime = turnOffTime;
    }

    public boolean isOnState() {
        return onState;
    }

    private void setOnState(boolean onState) {
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

    public boolean isDeleteAfterUsing() {
        return deleteAfterUsing;
    }

    public void setDeleteAfterUsing(boolean deleteAfterUsing) {
        this.deleteAfterUsing = deleteAfterUsing;
    }

    @Override
    public void postpound(Context context) {  //отложить
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = createIntent(context);

        // TODO: 26.12.2017 время автовыкл.

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MINUTE, repeatSignalInterval);
        Date closeDate = calendar.getTime();

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, closeDate.getTime(), pendingIntent);
    }

    @Override
    public void sendMessage()
    {
        if (message != null)
            message.send();
    }

    @Override
    public void turnOn(Context context) {
        onState = true;

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = createIntent(context);

        long signalTime = this.signalTime.getTime();
        // TODO: 26.12.2017 время автовыкл.
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, signalTime, pendingIntent);
    }

    @Override
    public void turnOff(Context context) {
        onState = false;

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = createIntent(context);

        alarmManager.cancel(pendingIntent);
    }

    @Override
    public void recountCloseDate() {

    }

    private PendingIntent createIntent(Context context) {
        Class puzzleClass = PuzzleFactory.getFactory(puzzle);

        Intent intent = new Intent(context, puzzleClass);
        intent.putExtra("id", id);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, id, intent, 0);

        return pendingIntent;
    }
}
