package Library.ProgramInfo;

import java.util.ArrayDeque;

import io.realm.RealmList;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class Information {

    @Required
    private String version;

    @Required
    private RealmList<Developer> developers;

    private static Information information;

    private Information() {
        /****подгрузить разработчиков из БД****/
    }

    public Information getInstance() { //ссылка на singleton
        if (information == null) {
            information = new Information();
        }
        return information;
    }

    public String getVersion() {
        return version;
    }

    public RealmList<Developer> getDevelopers() {
        return developers;
    }
}
