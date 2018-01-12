package Library.User.UserBuilders;

import java.util.ArrayList;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.Settings.ISetting;
import Library.Settings.UIBuilders.SavedUIBuilder;
import Library.Settings.UIBuilders.UIBuilder;
import Library.Settings.UIBuilders.UIDirector;
import Library.Signals.IRing;
import Library.User.IUser;
import Library.User.User;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public class SavedUserBuilder extends UserBuilder {

    private PreferenceHelper preferenceHelper;
    private DataBaseHelper dataBaseHelper;

    public SavedUserBuilder(User user) {
        super(user);
        preferenceHelper = PreferenceHelper.getInstance();
        dataBaseHelper = DataBaseHelper.getInstance();
    }

    @Override
    public void setName() {
        user.setName(preferenceHelper.getUserName());

    }

    @Override
    public void setRings() {
        user.setRings(dataBaseHelper.loadRings(preferenceHelper.getUserEmail()));

        if (user.getRings() == null)
            user.setRings(new ArrayList<>());
    }

    @Override
    public void setNotifications() {
        user.setNotifications(dataBaseHelper.loadNotifications(preferenceHelper.getUserEmail()));

        if (user.getNotifications() == null)
            user.setNotifications(new ArrayList<>());
    }

    @Override
    public void setMoneyQuantity() {
        user.setMoneyQuantity(preferenceHelper.getUserMoney());
    }

    @Override
    public void setEmail() {
        user.setEmail(preferenceHelper.getUserEmail());
    }

    @Override
    public void setUserInterface() {
        UIBuilder builder = new SavedUIBuilder();
        UIDirector director = new UIDirector(builder);
        ISetting setting = director.construct();
        user.setUserInterface(setting);
    }
}
