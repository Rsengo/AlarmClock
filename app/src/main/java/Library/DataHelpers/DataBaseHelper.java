package Library.DataHelpers;

import android.content.Context;

import Library.Settings.ColorScheme;
import Library.Settings.ISetting;
import Library.Settings.UserInterface;
import Library.Signals.ISignal;
import Library.Signals.Notification;
import Library.Signals.Ring;
import Library.User.IUser;
import Library.User.User;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by ytgv8b on 29.10.2017.
 */

public final class DataBaseHelper {

    private Realm realm;
    private IUser user;
    private ISetting userInterface;
    private String userEmail;
    private RealmQuery<Ring> ringRealmQuery;
    private RealmQuery<Notification> notificationRealmQuery;
    private RealmQuery<ColorScheme> colorSchemeRealmQuery;

    private static void init(Context context) {
        Realm.init(context);
    }

    public DataBaseHelper(Context context) {
        user = User.getInstance();
        userInterface = UserInterface.getInstance();
        userEmail = ((User) user).getEmail();
        ringRealmQuery = realm.where(Ring.class).equalTo("userEmail", userEmail);
        notificationRealmQuery = realm.where(Notification.class).equalTo("userEmail", userEmail);
        colorSchemeRealmQuery = realm.where(ColorScheme.class).equalTo("userEmail", userEmail);
    }

    public void loadData() {
//        realm = Realm.getDefaultInstance();
//        ((User) user).setRings(ringRealmQuery.findAll());
//        ((User) user).setNotifications(notificationRealmQuery.findAll());
//        ((UserInterface) userInterface).setColorScheme(colorSchemeRealmQuery.findFirst());
//        realm.close();
    }

    public <T> void saveData(T data) {
//        realm = Realm.getDefaultInstance();
//        realm.beginTransaction();

    }

   // public DataBaseHelper()
}
