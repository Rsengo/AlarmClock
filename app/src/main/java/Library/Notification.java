package Library;

import java.io.File;
import java.sql.Time;

import Library.Enums.GeneralPereodicity;
import Library.Enums.Priority;

/**
 * Created by ytgv8b on 16.10.2017.
 */

public class Notification extends Signal {
    /****Методы интерфейсов****/

    private GeneralPereodicity generalPeriodicity;
    private byte specificPeriodicity;
    private Time date;
    private Priority priority;

    public Notification(String name, Time signalTime, Time repeatSignalInterval, boolean vibrating,
                        File melody, byte melodyVolume, Time turnOffTime, boolean onState, Time date,
                        Priority priority, GeneralPereodicity generalPeriodicity, byte specificPeriodicity)
    {
        super(name, signalTime, repeatSignalInterval, vibrating, melody, melodyVolume,
                turnOffTime, onState);
        this.date = date;
        this.priority = priority;
        this.generalPeriodicity = generalPeriodicity;
        this.specificPeriodicity = specificPeriodicity;
    }

    public GeneralPereodicity getGeneralPeriodicity() {
        return generalPeriodicity;
    }

    public void setGeneralPeriodicity(GeneralPereodicity generalPeriodicity) {
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }
}
