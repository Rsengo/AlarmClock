package Library.Enums;

import android.support.v4.app.NotificationCompat;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by ytgv8b on 21.10.2017.
 */

public class Priority{
    public static final byte LOW = NotificationCompat.PRIORITY_LOW;
    public static final byte DEFAULT = NotificationCompat.PRIORITY_DEFAULT;
    public static final byte HIGH = NotificationCompat.PRIORITY_MAX;
}
