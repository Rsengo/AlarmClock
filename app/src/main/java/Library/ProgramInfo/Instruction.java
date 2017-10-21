package Library.ProgramInfo;

import java.util.ArrayDeque;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class Instruction {
    /****подгрузить слайды из БД****/
    /****методы класса****/
    private ArrayDeque<Slide> slides;

    private static Instruction instruction;
    private Instruction() {
        /****подгрузить слайды из БД****/
    }
    public Instruction getInstance() {
        if (instruction == null) {
            instruction = new Instruction();
        }
        return instruction;
    }

    public ArrayDeque<Slide> getSlides() {
        return slides;
    }

    public void skip()
    {

    }

    public void restart()
    {

    }
}
