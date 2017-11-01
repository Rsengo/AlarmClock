package Library.Settings;

import android.content.SharedPreferences;
import android.util.Log;

import Library.DataHelpers.DataSaver;
import Library.IEdite;
import Library.Settings.UIBuilders.DefaultUIBuilder;
import Library.Settings.UIBuilders.PreferenceUIBuilder;
import Library.Settings.UIBuilders.UIDirector;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class UserInterface implements IEdite, ISetting {
    /****Пока хз, что тут еще будет****/
    /****Реализовать методы интерфейсов****/

    private IUIAttribute colorScheme;

    private byte fontSize;
    private byte language;

    private static SharedPreferences preferences;
    private static UserInterface userInterface;
    private static UIDirector director;

    private UserInterface() {
        preferences = DataSaver.getPreferences();
    }

    public static UserInterface getInstance() {
        //Возврат ссылки на синглтон, либо создание объекта
        if (userInterface == null) {
            userInterface = new UserInterface();

            //Если имеются сохраненные настройки
            if (preferences.contains(DataSaver.getFileName())) { //если файл существует
                director = new UIDirector(new PreferenceUIBuilder(userInterface));
            }
            else {
                /***Либо настройки тоже хранить на сервере***/
                director = new UIDirector(new DefaultUIBuilder(userInterface));
            }

            try {
                director.construct();
            }
            catch (NullPointerException ex)
            {
                Log.e("Exception", "Null pointer, creation");
            }

        }
        return userInterface;
    }


    public IUIAttribute getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(IUIAttribute colorScheme) {
        this.colorScheme = colorScheme;
    }

    public byte getFontSize() {
        return fontSize;
    }

    public void setFontSize(byte fontSize) {
        this.fontSize = fontSize;
    }

    public byte getLanguage() {
        return language;
    }

    public void setLanguage(byte language) {
        this.language = language;
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }

    @Override
    public void setDefault() {
        UIDirector director = new UIDirector(new DefaultUIBuilder(userInterface));
        userInterface = director.construct();
    }

}
