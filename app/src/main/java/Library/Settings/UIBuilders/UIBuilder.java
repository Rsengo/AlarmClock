package Library.Settings.UIBuilders;

import android.content.Context;
import android.os.Environment;

import Library.Settings.UserInterface;

/**
 * Created by ytgv8b on 27.10.2017.
 */

public abstract class UIBuilder {
    protected UserInterface userInterface;

    public void createUI() {
        userInterface = UserInterface.getInstance();
    }

    public abstract void setColorScheme();
    public abstract void setLanguage();
    public abstract void setFontSize();

    public UserInterface getUserInterface() {
        return userInterface;
    }
}
