package Library.User;

import android.util.Log;

import java.util.ArrayList;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.IRealmModelWithID;
import Library.Settings.ISetting;
import Library.Settings.UIBuilders.DefaultUIBuilder;
import Library.Settings.UIBuilders.UIBuilder;
import Library.Settings.UIBuilders.UIDirector;
import Library.Settings.UserInterface;
import Library.Signals.INotification;
import Library.Signals.IRing;
import Library.Signals.ISignal;
import Library.User.UserBuilders.DefaultUserBuilder;
import Library.User.UserBuilders.SavedUserBuilder;
import Library.User.UserBuilders.UserBuilder;
import Library.User.UserBuilders.UserDirector;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class User implements  IUser {

    private String name; //Имя
    private ArrayList<IRing> rings; //Будильники
    private ArrayList<INotification> notifications; //События
    private int moneyQuantity; //Число монет
    private ISetting userInterface;
    private String email; //Email пользователя


    private static PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
    private static DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

    private static User user; //Ссылка на себя

    private User() { //Новый пользователь
    }

    public static synchronized User getInstance() { //Создание пользователя
        if (user == null) { //Если вход не выполнен
            user = new User();

            //Если имеются сохраненные настройки
            if (!preferenceHelper.isEmpty()) { //если файл существует
                UserBuilder builder = new SavedUserBuilder(user);
                try {
                    UserDirector director = new UserDirector(builder);
                    director.construct();
                    user = builder.getResult();
                }
                catch (NullPointerException ex)
                {
                    Log.e("Exception", "Null pointer, creation");
                }
            }
            else {
                UIBuilder uiBuilder = new DefaultUIBuilder();
                UIDirector director = new UIDirector(uiBuilder);
                ISetting setting = director.construct();
                user.setRings(new ArrayList<>());
                user.setNotifications(new ArrayList<>());
                user.setUserInterface(setting);
            }
        }

        return user; //Возврат ссылки
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoneyQuantity() {
        return moneyQuantity;
    }

    public void setMoneyQuantity(int moneyQuantity) {
        this.moneyQuantity = moneyQuantity;
    }

    public ISetting getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(ISetting userInterface) {
        this.userInterface = userInterface;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<IRing> getRings() {
        return rings;
    }

    public void setRings(ArrayList<IRing> rings) {
        this.rings = rings;
    }

    public ArrayList<INotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<INotification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public void logOut() {
        /***Сохранение данных на сервер***/
        preferenceHelper = PreferenceHelper.getInstance();
        preferenceHelper.clearPreference();
    }

    @Override
    public void addRing(IRing ring) {
        dataBaseHelper.saveRecursive(ring);
        rings.add(ring);
    }

    @Override
    public void addNotification(INotification notification) {
        dataBaseHelper.saveRecursive(notification);
        notifications.add(notification);
    }

    @Override
    public void removeRing(IRing ring) {
        dataBaseHelper.deleteRecursive(ring);
        rings.remove(ring);
    }

    @Override
    public void removeNotification(INotification notification) {
        dataBaseHelper.deleteRecursive(notification);
        notifications.remove(notification);
    }

    @Override
    public void removeRing(int position) {
        IRing ring = rings.get(position);
        removeRing(ring);
    }

    @Override
    public void removeNotification(int position) {
        INotification notification = notifications.get(position);
        removeNotification(notification);
    }
}
