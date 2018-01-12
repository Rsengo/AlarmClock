package Library.PuzzlesThings;

import com.example.ytgv8b.firsttry.Puzzles.DisplayCalculateActivity;
import com.example.ytgv8b.firsttry.Puzzles.DisplayConnectActivity;
import com.example.ytgv8b.firsttry.MainActivity;

import java.util.HashMap;

/**
 * Created by ytgv8b on 11.01.2018.
 */

public class PuzzleFactory {
    // TODO: 13.01.2018 add default puzzle
    public static byte DEFAULT = 0;
    public static byte CALCULATE = 1;
    public static byte CONNECT = 2;

    private static HashMap<Byte, Class> puzzles = new HashMap<>();

    private static void complete() {
        if (puzzles.isEmpty()) {
            puzzles.put(CONNECT, MainActivity.class);
            puzzles.put(CALCULATE, DisplayCalculateActivity.class);
            puzzles.put(CONNECT, DisplayConnectActivity.class);
        }
    }

    public static Class getFactory(byte puzzle) {
        complete();
        return puzzles.get(puzzle);
    }
}
