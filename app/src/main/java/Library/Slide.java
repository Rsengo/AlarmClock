package Library;

import java.io.File;

/**
 * Created by ytgv8b on 08.10.2017.
 */

class Slide {
    /****Загрузка из БД****/
    private String text;
    private File picture;

    public File getPicture() {
        return picture;
    }

    public String getText() {
        return text;
    }
}
