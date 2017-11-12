package Library.Settings;

import android.util.Log;

import Library.DataHelpers.PreferenceHelper;
import Library.Settings.UIBuilders.DefaultUIBuilder;
import Library.Settings.UIBuilders.SavedUIBuilder;
import Library.Settings.UIBuilders.UIBuilder;
import Library.Settings.UIBuilders.UIDirector;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class UserInterface implements ISetting {
    /****Пока хз, что тут еще будет****/
    /****Реализовать методы интерфейсов****/

    private ColorScheme colorScheme;

    private byte fontSize;
    private byte language;

    private static UserInterface userInterface;
    private static UIDirector director;
    private static UIBuilder builder;


    private UserInterface() {
    }

    public static UserInterface getInstance() {
        //Возврат ссылки на синглтон, либо создание объекта
        if (userInterface == null) {

            //Если имеются сохраненные настройки
            if (!PreferenceHelper.isEmpty()) { //если файл существует
                builder = new SavedUIBuilder(userInterface);
                director = new UIDirector(builder);
            }
            else {
                /***Либо настройки тоже хранить на сервере***/
                builder = new DefaultUIBuilder(userInterface);
                director = new UIDirector(builder);
            }

            try {
                director.construct();
                userInterface = builder.getResult();
            }
            catch (NullPointerException ex)
            {
                Log.e("Exception", "Null pointer, creation");
            }

        }
        return userInterface;
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(ColorScheme colorScheme) {
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
    public void setDefault() {
        builder = new DefaultUIBuilder(userInterface);
        director = new UIDirector(builder);
        director.construct();
        userInterface = builder.getResult();
    }

}
