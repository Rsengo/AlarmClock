package Library.Signals;

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
