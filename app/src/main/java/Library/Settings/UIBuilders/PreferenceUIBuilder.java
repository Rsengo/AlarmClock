package Library.Settings.UIBuilders;

import Library.DataHelpers.DataSaver;
import Library.Settings.ISetting;
import Library.Settings.UserInterface;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public class PreferenceUIBuilder extends UIBuilder {

    public PreferenceUIBuilder(ISetting userInterface) {
        super(userInterface);
    }

    @Override
    public void setColorScheme() {
        /***Найти дефолтную, либо из базы***/
    }

    @Override
    public void setLanguage() {
        ((UserInterface)userInterface).setLanguage(DataSaver.getLanguage());
    }

    @Override
    public void setFontSize() {
        ((UserInterface)userInterface).setLanguage(DataSaver.getFontSize());
    }
}
