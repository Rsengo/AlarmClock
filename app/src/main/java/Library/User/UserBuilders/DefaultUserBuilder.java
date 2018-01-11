package Library.User.UserBuilders;

import java.util.ArrayList;

import Library.Settings.ISetting;
import Library.Settings.UIBuilders.DefaultUIBuilder;
import Library.Settings.UIBuilders.UIBuilder;
import Library.Settings.UIBuilders.UIDirector;
import Library.User.User;

/**
 * Created by ytgv8b on 11.01.2018.
 */

public class DefaultUserBuilder extends UserBuilder {
    public DefaultUserBuilder(User user) {
        super(user);
    }

    @Override
    public void setName() {
        user.setName("user_name");
    }

    @Override
    public void setRings() {
        user.setRings(new ArrayList<>());
    }

    @Override
    public void setNotifications() {
        user.setNotifications(new ArrayList<>());
    }

    @Override
    public void setMoneyQuantity() {
        user.setMoneyQuantity(0);
    }

    @Override
    public void setEmail() {
        user.setEmail("user_email@gmail.com");
    }

    @Override
    public void setUserInterface() {
        UIBuilder builder = new DefaultUIBuilder();
        UIDirector director = new UIDirector(builder);
        ISetting setting = director.construct();
        user.setUserInterface(setting);
    }
}
