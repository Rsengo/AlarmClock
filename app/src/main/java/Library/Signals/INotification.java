package Library.Signals;

/**
 * Created by ytgv8b on 16.11.2017.
 */

public interface INotification extends ISignal {
    String getName();
    byte getPriority();
    byte getGeneralPeriodicity();
}
