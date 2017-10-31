package Library.Signals;

import java.util.Date;
import java.util.UUID;

import Library.Enums.TurnOffMethod;
import Library.IEdite;
import Library.Messages.IMessage;
import Library.Puzzles.Puzzle;
import Library.Messages.SMS;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by ytgv8b on 16.10.2017.
 */

@RealmClass
public class Ring implements RealmModel, IEdite, ISignal{
    /****добавить абстракции для головоломки****/
    /****Написать метод отложить будьник****/
    /****Методы интефкйсов****/
    /***Обратная связь с юзером***/

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private byte turnOffMethod; //метод выключения
    private Puzzle puzzle; //головоломка
    //private IMessage sms; //СМС
    private Date signalTime; //Время запуска
    private Date repeatSignalInterval; //интервал повтора
    private boolean vibrating; //вибрация(Вибрирующий)
    private int melody; //мелодия
    private byte melodyVolume; //громкость
    private Date turnOffTime; //Время автовыключения звукового сигнала
    private String description; //Описание
    private boolean onState; //Вкл/Выкл звукового сигнала
    private String userEmail; //Почта пользователя-владельца

    /***Можно ли использовать массив?***/
//    private boolean[] repeatDays; //Дни повтора

    public String getId() {
        return id;
    }

    public byte getTurnOffMethod() {
        return turnOffMethod;
    }

    public void setTurnOffMethod(byte turnOffMethod) {
        this.turnOffMethod = turnOffMethod;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

//    public IMessage getSms() {
//        return sms;
//    }
//
//    public void setSms(SMS sms) {
//        this.sms = sms;
//    }

//    public boolean[] getRepeatDays() {
//        return repeatDays;
//    }

    public Date getSignalTime() {
        return signalTime;
    }

    public void setSignalTime(Date signalTime) {
        this.signalTime = signalTime;
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

    public void setOnState(boolean onState) {
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

    public void postpound() {  //отложить
        /****отложить будильник****/
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
