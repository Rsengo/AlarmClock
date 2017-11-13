package Library.DataHelpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.io.File;
import java.util.Map;

import Library.Settings.UserInterface;
import Library.User.User;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public final class PreferenceHelper {

    /***Добавить метку того, первый ли запуск***/

    private User user;
    private UserInterface userInterface;
    private static Editor editor;
    private static SharedPreferences preferences;
    private final static String fileName = "USER_PREFERENCES"; //Имя файла на диске

    private String userEmail;
    private String userName;
    private int userMoney;
    private byte language;
    private byte fontSize;

    private PreferenceHelper() {
        loadPreference();
    }

    private static PreferenceHelper preferenceHelper;

    public static PreferenceHelper getInstance()
    {
        if (preferenceHelper == null)
        {
            preferenceHelper = new PreferenceHelper();
        }
        return preferenceHelper;
    }

    public static void init(Context context) {
        preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public boolean isEmpty() {
        Map<String, ?> values = preferences.getAll();
        if (!values.isEmpty())
            return false;
        return true;
    }


    public void writePreference() {
        user = User.getInstance();
        userInterface = (UserInterface) user.getUserInterface();
        userName = user.getName();
        userEmail = user.getEmail();
        userMoney = user.getMoneyQuantity();
        language = userInterface.getLanguage();
        fontSize = userInterface.getFontSize();

        clearPreference(); //Очищаем старые записи

        editor.putString("USER_NAME", userName);
        editor.putString("USER_EMAIL", userEmail);
        editor.putInt("USER_MONEY", userMoney);
        editor.putInt("LANGUAGE", language);
        editor.putInt("FONT_SIZE", fontSize);

        editor.apply();
    }


    public void loadPreference() {
        userName = preferences.getString("USER_NAME", "ss");
        userEmail = preferences.getString("USER_EMAIL", "hh");
        userMoney = preferences.getInt("USER_MONEY", 0);
        language = (byte) preferences.getInt("LANGUAGE", 0);
        fontSize = (byte) preferences.getInt("FONT_SIZE", 0);
    }

    public void removePreference() {
        String path = "/data/data/alarmclock/shared_prefs/" + fileName + ".xml";
        File file= new File(path);
        file.delete();
    }

    public static void clearPreference() {
        editor.clear();
    }

    //getters and setters

    public static SharedPreferences getPreferences() {
        return preferences;
    }

    public static String getFileName() {
        return fileName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserMoney() {
        return userMoney;
    }

    public byte getLanguage() {
        return language;
    }

    public byte getFontSize() {
        return fontSize;
    }
}
