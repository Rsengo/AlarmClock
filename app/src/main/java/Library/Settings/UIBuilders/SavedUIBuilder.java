package Library.Settings.UIBuilders;

import Library.DataHelpers.PreferenceHelper;
import Library.Settings.ISetting;
import Library.Settings.UserInterface;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public class SavedUIBuilder extends UIBuilder {

    private PreferenceHelper preferenceHelper;

    public SavedUIBuilder(UserInterface userInterface) {
        super(userInterface);
        preferenceHelper.loadPreference();
    }

    @Override
    public void setColorScheme() {
        /***Найти дефолтную, либо из базы***/
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
