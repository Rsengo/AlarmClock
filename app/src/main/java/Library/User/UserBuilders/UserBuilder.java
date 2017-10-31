package Library.User.UserBuilders;

import Library.Settings.UserInterface;
import Library.User.IUser;
import Library.User.User;

/**
 * Created by ytgv8b on 01.11.2017.
 */

abstract class UserBuilder {
    protected IUser user;

    public UserBuilder(IUser user) {
        this.user = user;
    }

    public abstract void setName();
    public abstract void setRings();
    public abstract void setNotifications();
    public abstract void setMoneyQuantity();
    public abstract void setEmail();
    public void setUserInterface() {
        ((User) user).setUserInterface(UserInterface.getInstance());
    }


    public IUser getUser() {
        return user;
    }
}
