package Library.ProgramInfo;

import java.util.ArrayDeque;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class Information {
    private String version;
    private ArrayDeque<Developer> developers;
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

    public ArrayDeque<Developer> getDevelopers() {
        return developers;
    }
}
