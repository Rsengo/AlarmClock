package Library.Signals;

import java.io.File;
import java.sql.Time;

import Library.Interfaces.IEdite;

/**
 * Created by ytgv8b on 16.10.2017.
 */

public abstract class Signal implements IEdite {
    /****Методы интерфейсов****/
    /****Методы класса****/
    /****Порождащий паттерн****/
    protected String name; // Название
    protected Time signalTime; /****заменить на новый тип****/  //Время запуска
    protected Time repeatSignalInterval; /****заменить на новый тип****/  //интервал повтора
    protected boolean vibrating; //вибрация(Вибрирующий)
    protected File melody; /****мб другой тип****/ //мелодия
    protected byte melodyVolume;/****мб int/long****/ //громкость
    protected  Time turnOffTime; /****заменить на новый тип****/
    //Время автовыключения звукового сигнала
    protected boolean onState; //Вкл/Выкл звукового сигнала

    public Signal(String name, Time signalTime, Time repeatSignalInterval, boolean vibrating,
                  File melody, byte melodyVolume, Time turnOffTime, boolean onState) {
        this.name = name;
        this.signalTime = signalTime;
        this.repeatSignalInterval = repeatSignalInterval;
        this.vibrating = vibrating;
        this.melody = melody;
        this.melodyVolume = melodyVolume;
        this.turnOffTime = turnOffTime;
        this.onState = onState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
