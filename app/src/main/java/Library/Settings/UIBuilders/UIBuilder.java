package Library.Settings.UIBuilders;

import android.content.Context;
import android.os.Environment;

import Library.Settings.ISetting;
import Library.Settings.UserInterface;

/**
 * Created by ytgv8b on 27.10.2017.
 */

public abstract class UIBuilder {

    protected UserInterface userInterface;

    public UIBuilder(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public abstract void setColorSchemeId();
    public abstract void setLanguage();
    public abstract void setFontSize();

    public UserInterface getResult() {
        return userInterface;
    }

}
