package Library;

import java.io.File;
import java.sql.Time;

/**
 * Created by ytgv8b on 16.10.2017.
 */

public class Case extends Signal {
    /****Методы интерфейсов****/
    public static final byte MIDPRIORITY = 0;
    public static final byte LOWPRIORITY = 1;
    public static final byte HIGHPRIORITY = 2;
    public static final byte NOREPEAT = 0;
    public static final byte EVERYHOUR = 1;
    public static final byte EVERYWEEK = 2;
    public static final byte EVERYMOUNTH = 3;
    public static final byte EVERYYEAR = 4;

    private byte _generalPeriodicity;
    private byte _specificPeriodicity;
    private Time _date;
    private byte _priority;
    private boolean _soundOn;

    public Case(String name, byte priority, Time date, boolean soundOn)
    {
        super(name);
        _priority = priority;
        _date = date;
        _soundOn = soundOn;
    }
    public Case(String name, Time signalTime, Time repeatSignalInterval, boolean vibrating,
                File melody, byte melodyVolume, Time turnOffTime, boolean onState, Time date,
                byte priority, boolean soundOn)
    {
        super(name, signalTime, repeatSignalInterval, vibrating, melody, melodyVolume,
                turnOffTime, onState);
        _date = date;
        _priority = priority;
        _soundOn = soundOn;
    }

    public byte getGeneralPeriodicity() {
        return _generalPeriodicity;
    }

    public void setGeneralPeriodicity(byte generalPeriodicity) {
        _generalPeriodicity = generalPeriodicity;
    }

    public byte getSpecificPeriodicity() {
        return _specificPeriodicity;
    }

    public void setSpecificPeriodicity(byte specificPeriodicity) {
        _specificPeriodicity = specificPeriodicity;
    }

    public Time getDate() {
        return _date;
    }

    public void setDate(Time date) {
        _date = date;
    }

    public byte getPriority() {
        return _priority;
    }

    public void setPriority(byte priority) {
        _priority = priority;
    }

    public boolean isSoundOn() {
        return _soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        _soundOn = soundOn;
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }
}
