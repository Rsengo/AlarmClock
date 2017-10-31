package Library.Settings;

import android.content.Context;
import android.util.Log;

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

    private byte fontSize;
    private byte language;

    private final static String FILE_NAME = "UserInterface"; //Имя файла на диске

    private static UserInterface userInterface;

    private UserInterface() {
    }

    public static UserInterface getInstance() {
        //Возврат ссылки на синглтон, либо создание объекта
        if (userInterface == null) {
            if (new File(FILE_NAME).exists()) {  //Существование файла
                userInterface = deserialize();
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
        UIDirector director = new UIDirector(new DefaultUIBuilder());
        userInterface = director.construct();
    }

    @Override
    public void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(this);
            outputStream.close();
            fos.close();
        }
        catch (FileNotFoundException ex)
        {
            Log.e("Exception", "FileNotFoundException");
        }
        catch (IOException ex)
        {
            Log.e("Exception", "IOException");
        }
    }

    private static UserInterface deserialize() {
        try {
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            UserInterface temp = (UserInterface) inputStream.readObject();
            return temp;
        }
        catch (FileNotFoundException ex)
        {
            Log.e("Exception", "FileNotFoundException");
            return null;
        }
        catch (IOException ex)
        {
            Log.e("Exception", "IOException");
            return  null;
        }
        catch (ClassNotFoundException ex)
        {
            Log.e("Exception", "ClassNotFoundException");
            return null;
        }
    }
}
