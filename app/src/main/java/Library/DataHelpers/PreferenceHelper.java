package Library.DataHelpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.ytgv8b.firsttry.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import Library.Settings.UserInterface;
import Library.User.User;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public final class PreferenceHelper {

    /***Добавить метку того, первый ли запуск***/

    private User user;
    private UserInterface userInterface;
    private static Context context;
    private static Editor userEditor;
    private static SharedPreferences userPreferences;
    private static Editor startTimeEditor;
    private static SharedPreferences melodyPreferences;
    private static Editor melodyTimeEditor;
    private static SharedPreferences startTimePreferences;
    private final static String userFile = "USER_PREFERENCES"; //Имя файла на диске
    // с польз настройками
    private final static String startFile = "START_TIME"; //первый запуск?
    private final static String melodiesFile = "MELODIES";


    private String userEmail;
    private String userName;
    private int userMoney;
    private byte language;
    private byte fontSize;
    private long colorSchemeID;
    private String[] melodies;

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

        userPreferences = context.getSharedPreferences(userFile, Context.MODE_PRIVATE);
        userEditor = userPreferences.edit();
        startTimePreferences = context.getSharedPreferences(startFile, Context.MODE_PRIVATE);
        startTimeEditor = startTimePreferences.edit();
        setContext(context);
    }

    public boolean isEmpty() {
        Map<String, ?> values = userPreferences.getAll();
        if (!values.isEmpty())
            return false;
        return true;
    }

    public boolean isFirstStart() {
        return startTimePreferences.getBoolean("isFirst", true);
    }

    public void changeStartTime() {
        if (isFirstStart()) {
            startTimeEditor.clear();
            startTimeEditor.putBoolean("isFirst", false);
        } else {
            startTimeEditor.clear();
            startTimeEditor.putBoolean("isFirst", true);
        }
        startTimeEditor.apply();
    }

    public void writePreference() {
        user = User.getInstance();
        userInterface = (UserInterface) user.getUserInterface();
        userName = user.getName();
        userEmail = user.getEmail();
        userMoney = user.getMoneyQuantity();
        language = userInterface.getLanguage();
        fontSize = userInterface.getFontSize();
        colorSchemeID = userInterface.getColorSchemeId();

        clearPreference(); //Очищаем старые записи

        userEditor.putString("USER_NAME", userName);
        userEditor.putString("USER_EMAIL", userEmail);
        userEditor.putInt("USER_MONEY", userMoney);
        userEditor.putInt("LANGUAGE", language);
        userEditor.putInt("FONT_SIZE", fontSize);
        userEditor.putLong("COLOR_SCHEME_ID", colorSchemeID);

        userEditor.apply();
    }

    private void loadPreference() {
        userName = userPreferences.getString("USER_NAME", "ss");
        userEmail = userPreferences.getString("USER_EMAIL", "hh");
        userMoney = userPreferences.getInt("USER_MONEY", 0);
        language = (byte) userPreferences.getInt("LANGUAGE", 0);
        fontSize = (byte) userPreferences.getInt("FONT_SIZE", 0);
        colorSchemeID = userPreferences.getLong("COLOR_SCHEME_ID", 0);
    }

    public ArrayList<String> loadMelodies() {
        Map<String, ?> values;
        try {
            values = melodyPreferences.getAll();
        } catch (NullPointerException ex) {
            return loadMelodiesFromFolder();
        }

            ArrayList<String> melodies;
            if (!values.isEmpty()) {
                melodies = new ArrayList<>();

                Observable<String> melodyObservable = Observable
                        .fromIterable(values.values())
                        .map(value -> (String) value);

                Disposable disposable = melodyObservable.subscribe(s -> melodies.add(s));
            } else {
                melodies = loadMelodiesFromFolder();
            }

        return melodies;
    }

    private ArrayList<String> loadMelodiesFromFolder() {
        File path = new File(context.getString(R.string.ringtone_folder));
        ArrayList<String> melodies = new ArrayList<>();

        // TODO: 14.01.2018 название мелодии
        Observable<String> fileObservable = Observable.fromArray(path.listFiles())
                .map(file -> file.getName())
                .filter(s -> {
                    String[] s1 = s.split("\\.");
                    return s1[1].equals("ogg");
                })
                .map(s -> {
                    String[] s1 = s.split("\\.");
                    return  s1[0];
                });

        Disposable disposable = fileObservable.subscribe(s -> melodies.add(s));

        return melodies;
    }

    public void clearPreference() {
        userEditor.clear();
        startTimeEditor.clear();
    }

    //getters and setters

    public static SharedPreferences getPreferences() {
        return userPreferences;
    }

    public static String getFileName() {
        return userFile;
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

    public long getColorSchemeID() {
        return colorSchemeID;
    }

    public void setColorSchemeID(long colorSchemeID) {
        this.colorSchemeID = colorSchemeID;
    }

    public static Context getContext() {
        return context;
    }

    private static void setContext(Context context) {
        PreferenceHelper.context = context;
    }
}
