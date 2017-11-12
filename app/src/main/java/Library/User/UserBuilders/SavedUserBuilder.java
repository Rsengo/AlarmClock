package Library.User.UserBuilders;

import Library.DataHelpers.PreferenceHelper;
import Library.User.IUser;
import Library.User.User;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public class SavedUserBuilder extends UserBuilder {


    public SavedUserBuilder(User user) {
        super(user);
        PreferenceHelper.loadPreference();
    }

    @Override
    public void setName() {
        user.setName(PreferenceHelper.getUserName());

    }

    @Override
    public void setRings() {

    }

    @Override
    public void setNotifications() {

    }

    @Override
    public void setMoneyQuantity() {
        user.setMoneyQuantity(PreferenceHelper.getUserMoney());
    }

    @Override
    public void setEmail() {
        user.setEmail(PreferenceHelper.getUserEmail());
    }

}
