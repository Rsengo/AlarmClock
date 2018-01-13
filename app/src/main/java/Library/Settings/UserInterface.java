package Library.Settings;

import Library.Settings.UIBuilders.DefaultUIBuilder;
import Library.Settings.UIBuilders.UIBuilder;
import Library.Settings.UIBuilders.UIDirector;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class UserInterface implements ISetting {

    private ColorScheme colorScheme;

    private byte fontSize;
    private byte language;
    private long colorSchemeId;

    private static UIDirector director;
    private static UIBuilder builder;

    public static final byte FONT_SIZE_SMALL = 1;
    public static final byte FONT_SIZE_DEFAULT = 0;
    public static final byte FONT_SIZE_BIG = 2;

    public static final byte LANGUAGE_RUSSIAN = 0;
    public static final byte LANGUAGE_ENGLISH = 1;

    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
    }

    public byte getFontSize() {
        return fontSize;
    }

    public void setFontSize(byte fontSize) {
        this.fontSize = fontSize;
    }

    public byte getLanguage() {
        return language;
    }

    public void setLanguage(byte language) {
        this.language = language;
    }

    public long getColorSchemeId() {
        return colorSchemeId;
    }

    public void setColorSchemeId(long colorSchemeId) {
        this.colorSchemeId = colorSchemeId;
    }

    @Override
    public ISetting getDefault() {
        builder = new DefaultUIBuilder();
        director = new UIDirector(builder);
        return director.construct();
    }
}
