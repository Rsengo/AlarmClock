package Library.Settings;

import java.util.UUID;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by ytgv8b on 16.10.2017.
 */

@RealmClass
public class ColorScheme implements IUIAttribute, RealmModel {
    /****Хз пока про содержимое, после создания интерфейса решим****/

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

    private String userEmail; //Почта пользователя-владельца
}
