package Library;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class Instruction {
    private ArrayDeque<Slide> _slides = new ArrayDeque<>();

    private static Instruction _instruction;
    private Instruction() {
        /****подгрузить слайды****/
    }
    public Instruction getInstance() {
        if (_instruction == null) {
            _instruction = new Instruction();
        }
        return _instruction;
    }
}
