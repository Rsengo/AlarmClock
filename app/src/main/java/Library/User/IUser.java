package Library.User;

import Library.Signals.INotification;
import Library.Signals.IRing;

/**
 * Created by ytgv8b on 01.11.2017.
 */

public interface IUser {
    void logOut();
    void addRing(IRing ring);
    void addNotification(INotification notification);
    void removeRing(IRing ring);
    void removeNotification(INotification notification);
    void removeRing(int position);
    void removeNotification(int position);
}
