package Library.Settings;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ytgv8b on 16.10.2017.
 */

public class ColorScheme extends RealmObject implements RealmModel {
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
