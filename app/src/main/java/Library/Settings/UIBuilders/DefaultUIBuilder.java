package Library.Settings.UIBuilders;

import android.content.Context;

import Library.Enums.FontSize;
import Library.Enums.Language;
import Library.Settings.ISetting;
import Library.Settings.UserInterface;
import Library.User.User;

/**
 * Created by ytgv8b on 27.10.2017.
 */

public class DefaultUIBuilder extends UIBuilder {

    public DefaultUIBuilder(UserInterface userInterface) {
        super(userInterface);
    }

    @Override
    public void setColorScheme() {
        /***Найти дефолтную, либо из базы***/
    }

    @Override
    public void setLanguage() {
        userInterface.setLanguage(Language.RUSSIAN);
        /**Можно в завис. от страны***/
    }

    @Override
    public void setFontSize() {
        ((UserInterface)userInterface).setFontSize(FontSize.NORMAL);
    }
}
