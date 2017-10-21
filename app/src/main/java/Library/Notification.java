package Library;

import java.io.File;
import java.sql.Time;

/**
 * Created by ytgv8b on 16.10.2017.
 */

public class Notification extends Signal {
    /****Методы интерфейсов****/
    public static final byte MIDPRIORITY = 0;
    public static final byte LOWPRIORITY = 1;
    public static final byte HIGHPRIORITY = 2;
    public static final byte NOREPEAT = 0;
    public static final byte EVERYHOUR = 1;
    public static final byte EVERYWEEK = 2;
    public static final byte EVERYMOUNTH = 3;
    public static final byte EVERYYEAR = 4;

    private byte generalPeriodicity;
    private byte specificPeriodicity;
    private Time date;
    private byte priority;
    private boolean soundOn;

    public Notification(String name, Time signalTime, Time repeatSignalInterval, boolean vibrating,
                        File melody, byte melodyVolume, Time turnOffTime, boolean onState, Time date,
                        byte priority, boolean soundOn, byte generalPeriodicity, byte specificPeriodicity)
    {
        super(name, signalTime, repeatSignalInterval, vibrating, melody, melodyVolume,
                turnOffTime, onState);
        this.date = date;
        this.priority = priority;
        this.soundOn = soundOn;
        this.generalPeriodicity = generalPeriodicity;
        this.specificPeriodicity = specificPeriodicity;
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

    public Time getDate() {
        return date;
    }

    public void setDate(Time date) {
        this.date = date;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }
}
