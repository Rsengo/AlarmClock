package Library;
import android.os.Vibrator;

import java.io.File;
import java.sql.Time;

/**
 * Created by ytgv8b on 16.10.2017.
 */

public abstract class Signal implements IEdite {
    protected String _name; // Название
    protected Time _signalTime; /****заменить на новый тип****/  //Время запуска
    protected Time _repeatSignalInterval; /****заменить на новый тип****/  //интервал повтора
    protected boolean _vibrating; //вибрация(Вибрирующий)
    protected File _melody; /****мб другой тип****/ //мелодия
    protected byte _melodyVolume;/****мб int/long****/ //громкость
    protected  Time _turnOffTime; /****заменить на новый тип****/
    //Время автовыключения звукового сигнала
    protected boolean _onState; //Вкл/Выкл звукового сигнала

    public Signal(String name) {
        _name = name;
    }

    public Signal(String name, Time signalTime, Time repeatSignalInterval, boolean vibrating,
                  File melody, byte melodyVolume, Time turnOffTime, boolean onState) {
        _name = name;
        _signalTime = signalTime;
        _repeatSignalInterval = repeatSignalInterval;
        _vibrating = vibrating;
        _melody = melody;
        _melodyVolume = melodyVolume;
        _turnOffTime = turnOffTime;
        _onState = onState;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Time getSignalTime() {
        return _signalTime;
    }

    public void setSignalTime(Time signalTime) {
        _signalTime = signalTime;
    }

    public Time getRepeatSignalInterval() {
        return _repeatSignalInterval;
    }

    public void setRepeatSignalInterval(Time repeatSignalInterval) {
        _repeatSignalInterval = repeatSignalInterval;
    }

    public boolean isVibrating() {
        return _vibrating;
    }

    public void setVibration(boolean vibrating) {
        _vibrating = vibrating;
    }

    public File getMelody() {
        return _melody;
    }

    public void setMelody(File melody) {
        _melody = melody;
    }

    public byte getMelodyVolume() {
        return _melodyVolume;
    }

    public void setMelodyVolume(byte melodyVolume) {
        _melodyVolume = melodyVolume;
    }

    public Time getTurnOffTime() {
        return _turnOffTime;
    }

    public void setTurnOffTime(Time turnOffTime) {
        _turnOffTime = turnOffTime;
    }

    public boolean isOnState() {
        return _onState;
    }

    public void setOnState(boolean onState) {
        _onState = onState;
    }

    @Override
    public abstract void OpenEditeDialog();  //Редактирование
        /****Открытие диалога редактирования****/

    /****Методы класса****/


}
