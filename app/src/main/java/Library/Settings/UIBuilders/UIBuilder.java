package Library.Settings.UIBuilders;

import android.content.Context;
import android.os.Environment;

import Library.Settings.ISetting;
import Library.Settings.UserInterface;

/**
 * Created by ytgv8b on 27.10.2017.
 */

public abstract class UIBuilder {

    protected ISetting userInterface;

    public UIBuilder(ISetting userInterface) {
        this.userInterface = userInterface;
    }

    public abstract void setColorScheme();
    public abstract void setLanguage();
    public abstract void setFontSize();

    public ISetting getUserInterface() {
        return userInterface;
    }

}
