package Library.User.UserBuilders;

import Library.User.User;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public class UserDirector {

    UserBuilder builder;

    public UserDirector(UserBuilder builder)
    {
        this.builder = builder;
    }

    public User construct()
    {
        builder.setName();
        builder.setEmail();
        builder.setMoneyQuantity();
        builder.setRings();
        builder.setNotifications();
        builder.setUserInterface();
        return (User) builder.getUser();
    }
}
