package Library;

/**
 * Created by ytgv8b on 27.10.2017.
 */

public interface ISharedPreference<T> {
    void setSharePreference(T value);
    T getSharedPreference();
}
