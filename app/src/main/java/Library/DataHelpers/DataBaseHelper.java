package Library.DataHelpers;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;

import Library.IRealmModelWithID;
import Library.Messages.IMessage;
import Library.Messages.SMS;
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

    private User user;
    private UserInterface userInterface;
    private RealmQuery<Ring> ringRealmQuery;
    private RealmQuery<Notification> notificationRealmQuery;
    private RealmQuery<ColorScheme> colorSchemeRealmQuery;

    public static void init(Context context) {
        Realm.init(context);
    }

    private DataBaseHelper() {
        user = User.getInstance();
        userInterface = (UserInterface) user.getUserInterface();
    }

    private static DataBaseHelper dataBaseHelper;

    public static synchronized DataBaseHelper getInstance()
    {
        if (dataBaseHelper == null)
            dataBaseHelper = new DataBaseHelper();
        return dataBaseHelper;
    }

    public ArrayList<INotification> loadNotifications(String userEmail) {
        ArrayList<INotification> notifications = new ArrayList<>();

        try (Realm realm = Realm.getDefaultInstance()) {
            notificationRealmQuery =
                    realm.where(Notification.class).equalTo("userEmail",
                            userEmail);
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

    public IMessage loadMesssage(int id) {

        IMessage message = null;
        try (Realm realm = Realm.getDefaultInstance()) {
            try {
                // TODO: 14.01.2018 просматривать остальные классы
                IMessage realmMessage = realm.where(SMS.class)
                        .equalTo("id", id)
                        .findFirst();
                if (realmMessage != null)
                    message = realm.copyFromRealm(realmMessage);
            } catch (Exception ex) {
                Log.d("Null pointer", "null");
            }

        }
        return message;
    }

    public ArrayList<IRing> loadRings(String userEmail)
    {
        ArrayList<IRing> rings = new ArrayList<>();

        try (Realm realm = Realm.getDefaultInstance())
        {
            ringRealmQuery =
                    realm.where(Ring.class).equalTo("userEmail",
                            userEmail);
            RealmResults<Ring> realmResults = ringRealmQuery.findAll();

            for (Ring ring: realmResults)
            {
                Ring copyRing = realm.copyFromRealm(ring);
                IMessage message = null;

                message = loadMesssage(ring.getId());

                copyRing.setMessage(message);
                rings.add(copyRing);
            }
        }

        return rings;
    }

    public IRing getRing(int id)
    {
        Ring ring = null;

        try (Realm realm = Realm.getDefaultInstance()) {
            try {
                ring = realm.copyFromRealm(realm.where(Ring.class)
                        .equalTo("id", id).findFirst());
                ring.setMessage(loadMesssage(ring.getId()));
            } catch (Exception ex) {
                Log.d("Find ring by id", "rind not found");
            }

        }

        return ring;
    }

    public INotification getNotification(int id)
    {
        INotification notification = null;

        try (Realm realm = Realm.getDefaultInstance()) {
            try {
                notification = realm.copyFromRealm(realm.where(Notification.class)
                        .equalTo("id", id).findFirst());
            } catch (Exception ex) {
                Log.d("Find ring by id", "rind not found");
            }

        }

        return notification;
    }

    public <T extends RealmModel> void saveData(T data) {
        try (Realm realm = Realm.getDefaultInstance())
        {
            realm.executeTransaction(realm1 -> {
                realm1.copyToRealmOrUpdate(data);
            });
        }
    }

    public <T extends RealmModel> void saveRecursive(T data) {
        saveData(data);

        Class dataClass = data.getClass();

        Field[] fields = dataClass.getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object newData = field.get(data);
                if (newData instanceof RealmModel) {
                    saveRecursive((RealmObject) newData);
                }
            }
        } catch (IllegalAccessException ex) {
            Log.e("Exception", "IllegalAccessException, recursive saving");
        }
    }

    public <T extends IRealmModelWithID> void deleteRecursive(T data)
    {
        Class dataClass = data.getClass();

        Field[] fields = dataClass.getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object newData = field.get(data);
                if (newData instanceof RealmModel && newData instanceof IRealmModelWithID) {
                    deleteRecursive((IRealmModelWithID) newData);
                }
            }
        } catch (IllegalAccessException ex) {
            Log.e("Exception", "IllegalAccessException, recursive saving");
        }

        deleteData(data);
    }

    public <T extends IRealmModelWithID> void deleteData(T data) {
        Class clazz = data.getClass();
        int id = data.getId();

        try (Realm realm = Realm.getDefaultInstance()) {
            try {
                RealmObject realmData = (RealmObject) realm.where(clazz)
                        .equalTo("id", id)
                        .findFirst();
                realm.executeTransaction(realm1 -> realmData.deleteFromRealm());
            } catch (NullPointerException ex1) {
                Log.e("Null pointer", "null in deleting");
            } catch (Exception ex2) {
                Log.e("Exception", "Deleteting data");
            }
        }
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
