package Library.Settings.UIBuilders;

import android.content.Context;
import android.os.Environment;
import android.provider.ContactsContract;

import Library.DataHelpers.DataBaseHelper;
import Library.Settings.ISetting;
import Library.Settings.UserInterface;

/**
 * Created by ytgv8b on 27.10.2017.
 */

public abstract class UIBuilder {

    protected UserInterface userInterface;
    protected DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

    public UIBuilder(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public abstract void setColorScheme();
    public abstract void setLanguage();
    public abstract void setFontSize();

    public UserInterface getResult() {
        return userInterface;
    }

}
