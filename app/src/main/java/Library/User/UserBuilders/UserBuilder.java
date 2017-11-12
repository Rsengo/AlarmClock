package Library.User.UserBuilders;

import Library.Settings.UserInterface;
import Library.User.IUser;
import Library.User.User;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public abstract class UserBuilder {
    protected User user;

    public UserBuilder(User user) {
        this.user = user;
    }

    public abstract void setName();
    public abstract void setRings();
    public abstract void setNotifications();
    public abstract void setMoneyQuantity();
    public abstract void setEmail();
    public void setUserInterface() {
        user.setUserInterface(UserInterface.getInstance());
    }


    public User getResult() {
        return user;
    }
}
