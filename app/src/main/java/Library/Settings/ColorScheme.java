package Library.Settings;

import java.util.UUID;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 16.10.2017.
 */

public class ColorScheme extends RealmObject implements IColorScheme {
    /****Хз пока про содержимое, после создания интерфейса решим****/

    @PrimaryKey
    private long id;

    public long getId() {
        return id;
    }

    public String getUserEmail() {
        return null;
    }

    public void setUserEmail(String userEmail) {

    }
}
