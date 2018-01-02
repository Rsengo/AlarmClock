package Library.Signals;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


import com.example.ytgv8b.firsttry.MainActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import Library.Messages.IMessage;
import io.realm.RealmList;
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
    private String id = UUID.randomUUID().toString();

    private byte turnOffMethod; //метод выключения
    private byte puzzle; //головоломка
    private long messageID; //ID сообщения
    private Date signalTime; //Время запуска
    private Date closeDate; //Ближайшее время запуска
    private Date repeatSignalInterval; //интервал повторного запуска после откладывания
    private boolean vibrating; //вибрация(Вибрирующий)
    private int melody; //мелодия
    private byte melodyVolume; //громкость
    private Date turnOffTime; //Время автовыключения звукового сигнала
    private String description; //Описание
    private boolean onState; //Вкл/Выкл звукового сигнала
    private boolean deleteAfterUsing; //удалить после срабатывания

    @Required
    private String userEmail; //Почта пользователя-владельца

    @Ignore
    private IMessage message; //СМС


    /***Можно ли использовать массив?***/
    private RealmList<Boolean> repeatDays; //Дни повтора

    @Override
    public String getId() {
        return id;
    }

    public byte getTurnOffMethod() {
        return turnOffMethod;
    }

    public void setTurnOffMethod(byte turnOffMethod) {
        this.turnOffMethod = turnOffMethod;
    }

    public byte getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(byte puzzle) {
        this.puzzle = puzzle;
    }

    public IMessage getMessage() {
        return message;
    }

    public void setMessage (IMessage message) {
        this.message = message;
    }

    public RealmList<Boolean> getRepeatDays() {
        return repeatDays;
    }

    public void setRepeatDays(RealmList<Boolean> repeatDays) {
        this.repeatDays = repeatDays;
    }

    public Date getSignalTime() {
        return signalTime;
    }

    public void setSignalTime(Date signalTime) {
        this.signalTime = signalTime;
        closeDate = signalTime;
    }

    public Date getRepeatSignalInterval() {
        return repeatSignalInterval;
    }

    public void setRepeatSignalInterval(Date repeatSignalInterval) {
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

    public long getMessageID() {
        return messageID;
    }

    public void setMessageID(long messageID) {
        this.messageID = messageID;
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
//         TODO: 26.12.2017 switch-case для выбора способа выключения
        Intent intent = new Intent(context, MainActivity.class);
        recountCloseDate();
        // TODO: 26.12.2017 request code для различия
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intent, 0);
        // TODO: 26.12.2017 время автовыкл.
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, closeDate.getTime(), pendingIntent);
    }

    @Override
    public void recountCloseDate() {
//        closeDate = closeDate + repeatSignalInterval;
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
        // TODO: 26.12.2017 switch-case для выбора способа выключения 
        Intent intent = new Intent(context, MainActivity.class);
        // TODO: 26.12.2017 request code для различия
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intent, 0);

        long signalTime = this.signalTime.getTime();
        // TODO: 26.12.2017 время автовыкл.
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, signalTime, pendingIntent);
    }

    @Override
    public void turnOff(Context context) {
        onState = false;

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        // TODO: 26.12.2017 switch-case для выбора способа выключения
        Intent intent = new Intent(context, MainActivity.class);
        // TODO: 26.12.2017 request code для различия
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intent, 0);

        alarmManager.cancel(pendingIntent);
    }
}
