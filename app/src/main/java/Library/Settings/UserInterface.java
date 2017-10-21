package Library.Settings;

import Library.IEdite;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class UserInterface implements IEdite, ISetting {
    /****Пока хз, что тут еще будет****/
    /****Реализовать методы интерфейсов****/
    public static final byte NORMALFONT = 0;
    public static final byte SMALLFONT = 1;
    public static final byte BIGFONT = 2;
    public static final byte RUSSIAN = 0;
    public static final byte ENGLISH = 1;

    private IUIAttribute colorScheme;
    private byte fontSize;
    private byte language;

    private static UserInterface userInterface;

    private UserInterface() {}

    private UserInterface(ColorScheme colorScheme, byte fontSize, byte language)
    {
        this.colorScheme = colorScheme;
        this.fontSize = fontSize;
        this.language = language;
    }

    public UserInterface getInstance() //Возврат ссылки на синглтон, либо создание объекта
    {
        if (userInterface == null) {
            userInterface = new UserInterface();
        }
        return userInterface;
    }

    public UserInterface getInstance(ColorScheme colorScheme, byte fontSize, byte language) {
        //Возврат ссылки на синглтон, либо создание объекта
        if (userInterface == null) {
            userInterface = new UserInterface(colorScheme, fontSize, language);
        }
        return userInterface;
    }

    public IUIAttribute getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(IUIAttribute colorScheme) {
        this.colorScheme = colorScheme;
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }

    @Override
    public void reset() {

    }
}
