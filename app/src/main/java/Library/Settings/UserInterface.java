package Library.Settings;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Library.Enums.FontSize;
import Library.Enums.Language;
import Library.IEdite;
import Library.ISerialize;
import Library.Settings.UIBuilders.DefaultUIBuilder;
import Library.Settings.UIBuilders.UIBuilder;
import Library.Settings.UIBuilders.UIDirector;
import Library.User;
import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class UserInterface implements IEdite, ISetting, Serializable, ISerialize {
    /****Пока хз, что тут еще будет****/
    /****Реализовать методы интерфейсов****/

    private IUIAttribute colorScheme;

    private FontSize fontSize;
    private Language language;

    private final static String fileName = "UserInterface"; //Имя сериализованного файла

    private static UserInterface userInterface;

    private UserInterface() {
    }

    public static UserInterface getInstance(Context context) {
        //Возврат ссылки на синглтон, либо создание объекта
        if (userInterface == null) {
            if (new File(fileName).exists()) {  //Существование файла
                userInterface = deserialize(context);
            }
            else {
                UIDirector director = new UIDirector(new DefaultUIBuilder());
                userInterface = director.construct();
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

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }

    @Override
    public void setDefault() {
        UIDirector director = new UIDirector(new DefaultUIBuilder());
        userInterface = director.construct();
    }

    @Override
    public void serialize(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(this);
            outputStream.close();
            fos.close();
        }
        catch (FileNotFoundException ex)
        {

        }
        catch (IOException ex)
        {

        }
    }

    private static UserInterface deserialize(Context context) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            UserInterface temp = (UserInterface) inputStream.readObject();
            return temp;
        }
        catch (FileNotFoundException ex)
        {
            return null;
        }
        catch (IOException ex)
        {
            return  null;
        }
        catch (ClassNotFoundException ex)
        {
            return null;
        }
    }
}
