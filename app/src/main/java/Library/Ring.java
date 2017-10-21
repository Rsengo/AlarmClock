package Library;

import java.io.File;
import java.sql.Time;

/**
 * Created by ytgv8b on 16.10.2017.
 */

public class Ring extends Signal {
    /****добавить абстракции для головоломки****/
    /****Написать метод отложить будьник****/
    /****Методы интефкйсов****/
    public static final byte SWIPE = 0;
    public static final byte PUZZLE = 1;

    private byte turnOffMethod; //метод выключения
    private Puzzle puzzle; //головоломка
    private IMessage sms; //СМС
    private boolean[] repeatDays; //Дни повтора

    public Ring(String name, Time signalTime, Time repeatSignalInterval, boolean vibrating,
                File melody, byte melodyVolume, Time turnOffTime, boolean onState,
                byte turnOffMethod, Puzzle puzzle, SMS sms, boolean[] repeatDays)
    {
        super(name, signalTime, repeatSignalInterval, vibrating, melody, melodyVolume, turnOffTime,
                onState);
        this.turnOffMethod = turnOffMethod;
        this.puzzle = puzzle;
        this.sms = sms;
        this.repeatDays = repeatDays;
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
