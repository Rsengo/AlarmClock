package Library.Signals;

import java.io.File;
import java.sql.Time;

import Library.IEdite;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ytgv8b on 16.10.2017.
 */

public abstract class Signal extends RealmObject implements IEdite {
    /****Методы интерфейсов****/
    /****Методы класса****/
    /****Порождащий паттерн****/
    /****protected String name; // Название***/ /**Заменено на следующие id & description***/
    protected long id;
    protected Time signalTime; /****заменить на новый тип****/  //Время запуска
    protected Time repeatSignalInterval; /****заменить на новый тип****/  //интервал повтора
    protected boolean vibrating; //вибрация(Вибрирующий)
    protected File melody; /****мб другой тип****/ //мелодия
    protected byte melodyVolume;/****мб int/long****/ //громкость
    protected  Time turnOffTime; /****заменить на новый тип****/
    protected String description; /***Описание***/
    //Время автовыключения звукового сигнала
    protected boolean onState; //Вкл/Выкл звукового сигнала


    public long getId() {
        return id;
    }

    public Time getSignalTime() {
        return signalTime;
    }

    public void setSignalTime(Time signalTime) {
        this.signalTime = signalTime;
    }

    public Time getRepeatSignalInterval() {
        return repeatSignalInterval;
    }

    public void setRepeatSignalInterval(Time repeatSignalInterval) {
        this.repeatSignalInterval = repeatSignalInterval;
    }

    public boolean isVibrating() {
        return vibrating;
    }

    public void setVibration(boolean vibrating) {
        this.vibrating = vibrating;
    }

    public File getMelody() {
        return melody;
    }

    public void setMelody(File melody) {
        this.melody = melody;
    }

    public byte getMelodyVolume() {
        return melodyVolume;
    }

    public void setMelodyVolume(byte melodyVolume) {
        this.melodyVolume = melodyVolume;
    }

    public Time getTurnOffTime() {
        return turnOffTime;
    }

    public void setTurnOffTime(Time turnOffTime) {
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

    @Override
    public abstract void OpenEditeDialog();  //Редактирование
        /****Открытие диалога редактирования****/

    public void start()
    {

    }

    public void stop()
    {

    }
}
