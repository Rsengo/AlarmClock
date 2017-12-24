package Library.DataHelpers;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;

import Library.Messages.IMessage;
import Library.Messages.SMS;
import Library.Settings.ColorScheme;
import Library.Settings.IColorScheme;
import Library.Settings.ISetting;
import Library.Settings.UserInterface;
import Library.Signals.INotification;
import Library.Signals.IRing;
import Library.Signals.ISignal;
import Library.Signals.Notification;
import Library.Signals.Ring;
import Library.User.IUser;
import Library.User.User;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.RealmClass;

/**
 * Created by ytgv8b on 29.10.2017.
 */

public final class DataBaseHelper {
/****считывание ProgramInfo****/

    private User user;
    private UserInterface userInterface;
    private RealmQuery<Ring> ringRealmQuery;
    private RealmQuery<Notification> notificationRealmQuery;
    private RealmQuery<IColorScheme> colorSchemeRealmQuery;
    private RealmQuery<IMessage> messageRealmQuery;

    public static void init(Context context) {
        Realm.init(context);
    }

    private DataBaseHelper() {
        user = User.getInstance();
        userInterface = UserInterface.getInstance();
    }

    private static DataBaseHelper dataBaseHelper;

    public static DataBaseHelper getInstance()
    {
        if (dataBaseHelper == null)
            dataBaseHelper = new DataBaseHelper();
        return dataBaseHelper;
    }

    public void loadAllData() {
        user.setNotifications(loadNotifications());
        user.setRings(loadRings());
        userInterface.setColorScheme(loadColorScheme());
        /***добавить ProgramInfo***/
    }

    public ArrayList<INotification> loadNotifications() {
        ArrayList<INotification> notifications = new ArrayList<>();

        try (Realm realm = Realm.getDefaultInstance()) {
            notificationRealmQuery =
                    realm.where(Notification.class).equalTo("userEmail",
                            user.getEmail());
            RealmResults<Notification> realmResults = notificationRealmQuery.findAll();

            for (Notification notification: realmResults)
            {
                Notification copyNotification = realm.copyFromRealm(notification);
                notifications.add(copyNotification);
            }
        }

        return notifications;
    }

    public IColorScheme loadColorScheme() {
        IColorScheme scheme;

        try (Realm realm = Realm.getDefaultInstance()) {
            colorSchemeRealmQuery =
                    realm.where(IColorScheme.class).equalTo("id",
                            userInterface.getColorSchemeId());
            scheme = colorSchemeRealmQuery.findFirst();
        }

        return scheme;
    }

    public IMessage loadMesssage(long id)
    {
        IMessage message;

        try (Realm realm = Realm.getDefaultInstance()) {
            messageRealmQuery = realm.where(IMessage.class).equalTo("id", id);
            message = realm.copyFromRealm(messageRealmQuery.findFirst());
        }
        return message;
    }

    public ArrayList<IRing> loadRings()
    {
        ArrayList<IRing> rings = new ArrayList<>();

        try (Realm realm = Realm.getDefaultInstance())
        {
            ringRealmQuery =
                    realm.where(Ring.class).equalTo("userEmail",
                            user.getEmail());
            RealmResults<Ring> realmResults = ringRealmQuery.findAll();

            for (Ring ring: realmResults)
            {
                Ring copyRing = realm.copyFromRealm(ring);
                long messageID = copyRing.getMessageID();
                IMessage message = null;

                if (messageID != 0) {
                    message = loadMesssage(messageID);
                }

                copyRing.setMessage(message);
                rings.add(copyRing);
            }
        }

        return rings;
    }

    public Ring getRing(String id)
    {
        Ring ring;

        try (Realm realm = Realm.getDefaultInstance()) {
            ring = realm.where(Ring.class)
                    .equalTo("id", id).findFirst();
        }

        return ring;
    }

    public <T extends RealmObject> void saveData(T data) {
        try (Realm realm = Realm.getDefaultInstance())
        {
            realm.executeTransaction(realm1 -> {
                realm1.copyToRealmOrUpdate(data);
            });
        }
    }

    public <T extends RealmObject> void saveRecursive(T data) {
        saveData(data);

        Class dataClass = data.getClass();

        Field[] fields = dataClass.getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object newData = field.get(data);
                if ((newData instanceof RealmObject) || (newData instanceof RealmModel)) {
                    saveRecursive((RealmObject) newData);
                }
            }
        } catch (IllegalAccessException ex) {
            Log.e("Exception", "IllegalAccessException, recursive saving");
        }
    }

    public <T extends RealmObject> void deleteRecursive(T data)
    {
        Class dataClass = data.getClass();

        Field[] fields = dataClass.getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object newData = field.get(data);
                if ((newData instanceof RealmObject) || (newData instanceof RealmModel)) {
                    deleteRecursive((RealmObject) newData);
                }
            }
        } catch (IllegalAccessException ex) {
            Log.e("Exception", "IllegalAccessException, recursive saving");
        }

        data.deleteFromRealm();
    }
}
