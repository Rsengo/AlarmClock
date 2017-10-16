package Library;

import java.io.File;

/**
 * Created by ytgv8b on 08.10.2017.
 */

class Slide {
    /****Загрузка из БД****/
    private String _text;
    private File _picture;

    public File getPicture() {
        return _picture;
    }

    public String getText() {
        return _text;
    }
}
