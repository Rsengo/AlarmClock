package Library.ProgramInfo;

import java.io.File;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class Slide extends RealmObject {
    /****Загрузка из БД****/

    @Required
    private String text;

    private File picture;

    public File getPicture() {
        return picture;
    }

    public String getText() {
        return text;
    }
}
