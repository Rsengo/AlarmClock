package Library.Settings.UIBuilders;

import android.content.Context;

import Library.Enums.FontSize;
import Library.Enums.Language;

/**
 * Created by ytgv8b on 27.10.2017.
 */

public class DefaultUIBuilder extends UIBuilder {

    @Override
    public void createUI() {
        super.createUI();
    }

    @Override
    public void setColorScheme() {
        /***Найти дефолтную***/
    }

    @Override
    public void setLanguage() {
        userInterface.setLanguage(Language.RUSSIAN); /**Можно в завис. от страны***/
    }

    @Override
    public void setFontSize() {
        userInterface.setFontSize(FontSize.NORMAL);
    }
}
