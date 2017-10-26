package Library.ProgramInfo;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

@RealmClass
public class Developer implements RealmModel{
    /****подгрузка данных из БД****/

    @Required
    private String name;

    @Required
    private String information;

    public String getName() {
        return name;
    }

    public String getInformation() {
        return information;
    }
}
