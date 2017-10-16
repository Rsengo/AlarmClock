package Library;

import java.util.ArrayDeque;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class Information {
    private String _version;
    private ArrayDeque<Developer> _developers;
    private static Information _information;
    private Information() {
        /****подгрузить разработчиков из БД****/
    }
    public Information getInstance() { //ссылка на singleton
        if (_information == null) {
            _information = new Information();
        }
        return _information;
    }

    public String getVersion() {
        return _version;
    }

    public ArrayDeque<Developer> getDevelopers() {
        return _developers;
    }
}
