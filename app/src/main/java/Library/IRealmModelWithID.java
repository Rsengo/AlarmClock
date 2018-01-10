package Library;

import io.realm.RealmModel;

/**
 * Created by ytgv8b on 10.01.2018.
 */

public interface IRealmModelWithID extends RealmModel {
    int getId();
    void setId(int id);
}
