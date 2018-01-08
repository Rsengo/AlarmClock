package Library.User.UserBuilders;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
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
        user.setRings(dataBaseHelper.loadRings());
    }

    @Override
    public void setNotifications() {
        user.setNotifications(dataBaseHelper.loadNotifications());
    }

    @Override
    public void setMoneyQuantity() {
        user.setMoneyQuantity(preferenceHelper.getUserMoney());
    }

    @Override
    public void setEmail() {
        user.setEmail(preferenceHelper.getUserEmail());
    }

}
