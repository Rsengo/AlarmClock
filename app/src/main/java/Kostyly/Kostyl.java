package Kostyly;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ytgv8b on 14.01.2018.
 */

public class Kostyl {
    public static void makeToast(Context context) {
        Toast toast = Toast.makeText(context, "Функция пока не доступна. Проект находится " +
                "в активной доработке ;)", Toast.LENGTH_LONG);
        toast.show();
    }

}
