package Library.Settings.UIBuilders;

import Library.Settings.UserInterface;

/**
 * Created by ytgv8b on 27.10.2017.
 */

public class DefaultUIBuilder extends UIBuilder {

    @Override
    public void setColorScheme() {
//        userInterface.setColorSchemeId(0);
//        ColorScheme colorScheme = dataBaseHelper.loadColorScheme(0);
//        userInterface.setColorScheme(colorScheme);
    }

    @Override
    public void setLanguage() {
        userInterface.setLanguage(UserInterface.LANGUAGE_RUSSIAN);
    }

    @Override
    public void setFontSize() {
        userInterface.setFontSize(UserInterface.FONT_SIZE_DEFAULT);
    }
}
