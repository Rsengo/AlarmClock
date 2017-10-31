package Library.DataHelpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.io.File;

import Library.Settings.UserInterface;
import Library.User.User;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public final class DataSaver {

    /***Добавить метку того, первый ли запуск***/

    private static User user;
    private static UserInterface userInterface;
    private static Editor editor;
    private static SharedPreferences preferences;
    private final static String fileName = "USER_PREFERENCES"; //Имя файла на диске

    private static String userEmail;
    private static String userName;
    private static int userMoney;
    private static byte language;
    private static byte fontSize;

    public DataSaver(Context context) {
        user = User.getInstance();
        userInterface = (UserInterface) user.getUserInterface();
        userName = user.getName();
        userEmail = user.getEmail();
        userMoney = user.getMoneyQuantity();
        language = userInterface.getLanguage();
        fontSize = userInterface.getFontSize();
        preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }


    public static void writePreference() {
        editor.putString("USER_NAME", userName);
        editor.putString("USER_EMAIL", userEmail);
        editor.putInt("USER_MONEY", userMoney);
        editor.putInt("LANGUAGE", language);
        editor.putInt("FONT_SIZE", fontSize);
        editor.apply();
    }


    public static void loadPreference() {

    }

    public static void removePreference() {
        String path = "/data/data/alarmclock/shared_prefs/" + fileName + ".xml";
        File file= new File(path);
        file.delete();
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static String getUserName() {
        return userName;
    }

    public static int getUserMoney() {
        return userMoney;
    }

    public static byte getLanguage() {
        return language;
    }

    public static byte getFontSize() {
        return fontSize;
    }

    public static String getFileName() {
        return fileName;
    }
}
