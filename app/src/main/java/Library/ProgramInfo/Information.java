package Library.ProgramInfo;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

@RealmClass
public class Information implements RealmModel {

    @Required
    private String version;

    private RealmList<Developer> developers;

    private static Information information;

    public Information() {

    }

    public String getVersion() {
        return version;
    }

    public RealmList<Developer> getDevelopers() {
        return developers;
    }
}
