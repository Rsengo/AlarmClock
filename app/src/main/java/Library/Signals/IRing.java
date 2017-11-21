package Library.Signals;

/**
 * Created by ytgv8b on 16.11.2017.
 */

public interface IRing extends ISignal {
    void postpound();
    void sendMessage();
}
