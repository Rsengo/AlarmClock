package Library.DataHelpers;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;

import Library.Messages.IMessage;
import Library.Settings.ColorScheme;
import Library.Settings.UserInterface;
import Library.Signals.INotification;
import Library.Signals.IRing;
import Library.Signals.Notification;
import Library.Signals.Ring;
import Library.User.User;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by ytgv8b on 29.10.2017.
 */

public final class DataBaseHelper {
/****считывание ProgramInfo****/

    private User user;
    private UserInterface userInterface;
    private RealmQuery<Ring> ringRealmQuery;
    private RealmQuery<Notification> notificationRealmQuery;
    private RealmQuery<ColorScheme> colorSchemeRealmQuery;
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

    public ColorScheme loadColorScheme(long id) {
        ColorScheme scheme;

        try (Realm realm = Realm.getDefaultInstance()) {
            colorSchemeRealmQuery =
                    realm.where(ColorScheme.class).equalTo("id", id);
            scheme = realm.copyFromRealm(colorSchemeRealmQuery.findFirst());
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
                IMessage message = null;

                IMessage message1 = realm.where(IMessage.class)
                        .equalTo("id", copyRing.getId())
                        .findFirst(); /***********************************************/

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

    public int getNextId(final Class clazz) {
        int newId = 0;
        if (clazz.getSuperclass().equals(RealmObject.class) ||
                clazz.getSuperclass().equals(RealmModel.class)) {
            try (Realm realm = Realm.getDefaultInstance()) {
                Number maxId = realm.where(clazz)
                        .max("id");
                newId = (maxId == null) ? 0 : maxId.intValue() + 1;
            }
        }

        return newId;
    }
}
