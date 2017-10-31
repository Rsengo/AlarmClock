package Library.Settings.UIBuilders;

import android.content.Context;

import Library.Settings.UserInterface;

/**
 * Created by ytgv8b on 27.10.2017.
 */

public class UIDirector {

    UIBuilder builder;

    public UIDirector(UIBuilder builder)
    {
        this.builder = builder;
    }

    public UserInterface construct()
    {
        builder.setColorScheme();
        builder.setLanguage();
        builder.setFontSize();
        return (UserInterface) builder.getUserInterface();
    }
}
