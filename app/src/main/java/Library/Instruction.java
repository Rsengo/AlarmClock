package Library;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class Instruction {
    /****подгрузить слайды из БД****/
    /****методы класса****/
    private ArrayDeque<Slide> _slides;

    private static Instruction _instruction;
    private Instruction() {
        /****подгрузить слайды из БД****/
    }
    public Instruction getInstance() {
        if (_instruction == null) {
            _instruction = new Instruction();
        }
        return _instruction;
    }

    public ArrayDeque<Slide> getSlides() {
        return _slides;
    }

    public void skip()
    {

    }

    public void restart()
    {

    }
}
