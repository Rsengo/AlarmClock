package Library;

import java.io.Serializable;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class UserInterface implements IEdite {
    /****Пока хз, что тут еще будет****/
    /****Возможно добавить абстракции****/
    public static final byte NORMALFONT = 0;
    public static final byte SMALLFONT = 1;
    public static final byte BIGFONT = 2;
    public static final byte RUSSIAN = 0;
    public static final byte ENGLISH = 1;

    private ColorScheme _colorScheme;
    private byte _fontSize;
    private byte _language;

    private static UserInterface _userInterface;

    private UserInterface() {}

    private UserInterface(ColorScheme colorScheme, byte fontSize, byte language)
    {
        _colorScheme = colorScheme;
        _fontSize = fontSize;
        _language = language;
    }

    public UserInterface getInstance() //Возврат ссылки на синглтон, либо создание объекта
    {
        if (_userInterface == null) {
            _userInterface = new UserInterface();
        }
        return _userInterface;
    }

    public UserInterface getInstance(ColorScheme colorScheme, byte fontSize, byte language) {
        //Возврат ссылки на синглтон, либо создание объекта
        if (_userInterface == null) {
            _userInterface = new UserInterface(colorScheme, fontSize, language);
        }
        return _userInterface;
    }

    public ColorScheme getColorScheme() {
        return _colorScheme;
    }

    public void setColorScheme(ColorScheme colorScheme) {
        _colorScheme = colorScheme;
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }
}
