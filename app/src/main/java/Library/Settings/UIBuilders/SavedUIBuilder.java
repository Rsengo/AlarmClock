package Library.Settings.UIBuilders;

import Library.DataHelpers.PreferenceHelper;
import Library.Settings.ColorScheme;
import Library.Settings.ISetting;
import Library.Settings.UserInterface;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public class SavedUIBuilder extends UIBuilder {

    private PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();

    @Override
    public void setColorScheme() {
//        long id = preferenceHelper.getColorSchemeID();
//        userInterface.setColorSchemeId(id);
//        ColorScheme colorScheme = dataBaseHelper.loadColorScheme(id);
//        userInterface.setColorScheme(colorScheme);
    }

    @Override
    public void setLanguage() {
        userInterface.setLanguage(preferenceHelper.getLanguage());
    }

    @Override
    public void setFontSize() {
        userInterface.setLanguage(preferenceHelper.getFontSize());
    }
}
