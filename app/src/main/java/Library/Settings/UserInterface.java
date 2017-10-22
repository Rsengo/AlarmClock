package Library.Settings;

import Library.Enums.FontSize;
import Library.Enums.Language;
import Library.IEdite;
import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class UserInterface extends RealmObject implements IEdite, ISetting {
    /****Пока хз, что тут еще будет****/
    /****Реализовать методы интерфейсов****/

    private IUIAttribute colorScheme;

    private FontSize fontSize;
    private Language language;

    private static UserInterface userInterface;

    private UserInterface() {}

    private UserInterface(ColorScheme colorScheme, FontSize fontSize, Language language)
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

    public UserInterface getInstance(ColorScheme colorScheme, FontSize fontSize,
                                     Language language) {
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
