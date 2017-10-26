package Library.ProgramInfo;

import java.io.File;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

@RealmClass
public class Slide implements RealmModel {

    @Required
    private String text;

    private int picture;

    public int getPicture() {
        return picture;
    }

    public String getText() {
        return text;
    }
}
