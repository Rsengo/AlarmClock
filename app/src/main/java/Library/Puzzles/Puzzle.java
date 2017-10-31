package Library.Puzzles;

import java.util.UUID;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by ytgv8b on 08.10.2017.
 */

@RealmClass
public class Puzzle implements RealmModel {
    /****Вот пока рили хз как тут и что делать****/

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }
}
