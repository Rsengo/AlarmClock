package Library.User.UserBuilders;

import Library.DataHelpers.DataSaver;
import Library.User.IUser;
import Library.User.User;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public class SavedUserBuilder extends UserBuilder {

    private DataSaver dataSaver;

    public SavedUserBuilder(IUser user) {
        super(user);
    }

    @Override
    public void setName() {
        ((User) user).setName(DataSaver.getUserName());

    }

    @Override
    public void setRings() {

    }

    @Override
    public void setNotifications() {

    }

    @Override
    public void setMoneyQuantity() {
        ((User) user).setMoneyQuantity(DataSaver.getUserMoney());
    }

    @Override
    public void setEmail() {
        ((User) user).setEmail(DataSaver.getUserEmail());
    }

}
