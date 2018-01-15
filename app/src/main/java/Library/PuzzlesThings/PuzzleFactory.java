package Library.PuzzlesThings;

import com.example.ytgv8b.firsttry.Puzzles.DefaultPuzzleActivity;
import com.example.ytgv8b.firsttry.Puzzles.DisplayCalculateActivity;
import com.example.ytgv8b.firsttry.Puzzles.DisplayConnectActivity;
import com.example.ytgv8b.firsttry.MainActivity;

import java.util.HashMap;

import Library.Signals.Ring;

/**
 * Created by ytgv8b on 11.01.2018.
 */

public class PuzzleFactory {

    private static HashMap<Byte, Class> puzzles = new HashMap<>();

    private static void complete() {
        if (puzzles.isEmpty()) {
            puzzles.put(Ring.PUZZLE_DEFAULT, DefaultPuzzleActivity.class);
            puzzles.put(Ring.PUZZLE_CALCULATE, DisplayCalculateActivity.class);
            puzzles.put(Ring.PUZZLE_CONNECT, DisplayConnectActivity.class);
        }
    }

    public static Class getFactory(byte puzzle) {
        complete();
        return puzzles.get(puzzle);
    }
}
