package Library.ProgramInfo;

import java.io.File;
import java.util.UUID;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

@RealmClass
public class Slide implements RealmModel {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    @Required

    private String text;

    private int picture;

    public int getPicture() {
        return picture;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }
}
