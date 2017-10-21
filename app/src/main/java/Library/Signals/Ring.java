package Library.Signals;

import java.io.File;
import java.sql.Time;

import Library.Enums.TurnOffMethod;
import Library.Interfaces.IMessage;
import Library.Puzzles.Puzzle;
import Library.Messages.SMS;

/**
 * Created by ytgv8b on 16.10.2017.
 */

public class Ring extends Signal {
    /****добавить абстракции для головоломки****/
    /****Написать метод отложить будьник****/
    /****Методы интефкйсов****/


    private TurnOffMethod turnOffMethod; //метод выключения
    private Puzzle puzzle; //головоломка
    private IMessage sms; //СМС
    private boolean[] repeatDays; //Дни повтора

    public Ring(String name, Time signalTime, Time repeatSignalInterval, boolean vibrating,
                File melody, byte melodyVolume, Time turnOffTime, boolean onState,
                TurnOffMethod turnOffMethod, Puzzle puzzle, SMS sms, boolean[] repeatDays)
    {
        super(name, signalTime, repeatSignalInterval, vibrating, melody, melodyVolume, turnOffTime,
                onState);
        this.turnOffMethod = turnOffMethod;
        this.puzzle = puzzle;
        this.sms = sms;
        this.repeatDays = repeatDays;
    }

    public TurnOffMethod getTurnOffMethod() {
        return turnOffMethod;
    }

    public void setTurnOffMethod(TurnOffMethod turnOffMethod) {
        this.turnOffMethod = turnOffMethod;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public IMessage getSms() {
        return sms;
    }

    public void setSms(SMS sms) {
        this.sms = sms;
    }

    public boolean[] getRepeatDays() {
        return repeatDays;
    }

    public void postpound() {  //отложить
        /****отложить будильник****/
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }


}
