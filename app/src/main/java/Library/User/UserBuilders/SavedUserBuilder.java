package Library.User.UserBuilders;

import Library.DataHelpers.PreferenceHelper;
import Library.User.IUser;
import Library.User.User;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public class SavedUserBuilder extends UserBuilder {


    public SavedUserBuilder(IUser user) {
        super(user);
        PreferenceHelper.loadPreference();
    }

    @Override
    public void setName() {
        ((User) user).setName(PreferenceHelper.getUserName());

    }

    @Override
    public void setRings() {

    }

    @Override
    public void setNotifications() {

    }

    @Override
    public void setMoneyQuantity() {
        ((User) user).setMoneyQuantity(PreferenceHelper.getUserMoney());
    }

    @Override
    public void setEmail() {
        ((User) user).setEmail(PreferenceHelper.getUserEmail());
    }

}
