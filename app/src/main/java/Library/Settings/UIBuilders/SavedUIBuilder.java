package Library.Settings.UIBuilders;

import Library.DataHelpers.PreferenceHelper;
import Library.Settings.ISetting;
import Library.Settings.UserInterface;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public class SavedUIBuilder extends UIBuilder {

    public SavedUIBuilder(ISetting userInterface) {
        super(userInterface);
        PreferenceHelper.loadPreference();
    }

    @Override
    public void setColorScheme() {
        /***Найти дефолтную, либо из базы***/
    }

    @Override
    public void setLanguage() {
        ((UserInterface)userInterface).setLanguage(PreferenceHelper.getLanguage());
    }

    @Override
    public void setFontSize() {
        ((UserInterface)userInterface).setLanguage(PreferenceHelper.getFontSize());
    }
}
