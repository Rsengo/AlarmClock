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

    private byte _turnOffMethod; //метод выключения
    private Puzzle _puzzle; //головоломка
    private IMessage _sms; //СМС
    private boolean[] _repeatDays; //Дни повтора

    public Ring(String name, Time signalTime, Time repeatSignalInterval, boolean vibrating,
                File melody, byte melodyVolume, Time turnOffTime, boolean onState,
                byte turnOffMethod, Puzzle puzzle, SMS sms, boolean[] repeatDays)
    {
        super(name, signalTime, repeatSignalInterval, vibrating, melody, melodyVolume, turnOffTime,
                onState);
        _turnOffMethod = turnOffMethod;
        _puzzle = puzzle;
        _sms = sms;
        _repeatDays = repeatDays;
    }

    public byte getTurnOffMethod() {
        return _turnOffMethod;
    }

    public void setTurnOffMethod(byte turnOffMethod) {
        _turnOffMethod = turnOffMethod;
    }

    public Puzzle getPuzzle() {
        return _puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        _puzzle = puzzle;
    }

    public IMessage getSms() {
        return _sms;
    }

    public void setSms(SMS sms) {
        _sms = sms;
    }

    public boolean[] getRepeatDays() {
        return _repeatDays;
    }

    public void postpound() {  //отложить
        /****отложить будильник****/
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }


}
