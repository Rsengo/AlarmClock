package Library.ProgramInfo;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class Information {

    private float version;

    private RealmList<Developer> developers;

    public Information() {

    }

    public float getVersion() {
        return version;
    }

    public RealmList<Developer> getDevelopers() {
        return developers;
    }
}
