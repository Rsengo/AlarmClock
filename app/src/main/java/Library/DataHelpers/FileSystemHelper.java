package Library.DataHelpers;

import android.content.Context;

import com.example.ytgv8b.firsttry.R;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ytgv8b on 14.01.2018.
 */

public class FileSystemHelper {
    private static Context context;
    private static FileSystemHelper fileSystemHelper;

    public static void init(Context context) {
        FileSystemHelper.context = context;
    }

    private FileSystemHelper() {}

    public static synchronized FileSystemHelper getInstance() {
        if (fileSystemHelper == null)
            fileSystemHelper = new FileSystemHelper();
        return fileSystemHelper;
    }

    public ArrayList<String> loadMelodies() {
        File path = new File(context.getString(R.string.ringtone_folder));
        ArrayList<String> melodies = new ArrayList<>();

        Observable<String> fileObservable = Observable.fromArray(path.listFiles())
                .map(file -> file.getName())
                .filter(s -> {
                    String[] s1 = s.split("\\.");
                    return s1[1].equals("ogg");
                })
                .map(s -> {
                    String[] s1 = s.split("\\.");
                    String result = "";
                    for (int i = 0; i < s1.length - 1; i++)
                        result += s1[i];
                    return  result;
                });

        Disposable disposable = fileObservable.subscribe(s -> melodies.add(s));

        return melodies;
    }
}
