package Library.Settings.UIBuilders;

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

    public void construct()
    {
        builder.setColorScheme();
        builder.setLanguage();
        builder.setFontSize();
    }
}
