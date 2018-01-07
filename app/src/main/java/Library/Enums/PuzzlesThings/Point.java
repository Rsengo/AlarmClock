package Library.Enums.PuzzlesThings;

/**
 * Created by Татьяна on 07.01.2018.
 */

public class Point {
    private int x, y, color, alpha, width;

    public Point(int x, int y, int color, int alpha, int width) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.alpha = alpha;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getWidth() {
        return width;
    }

}
