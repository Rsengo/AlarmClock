package Library.ProgramInfo;

import java.util.ArrayDeque;

import io.realm.RealmList;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class Instruction extends RealmList {
    /****подгрузить слайды из БД****/
    /****методы класса****/

    @Required
    private RealmList<Slide> slides;

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

    public RealmList<Slide> getSlides() {
        return slides;
    }

    public void skip()
    {

    }

    public void restart()
    {

    }
}
