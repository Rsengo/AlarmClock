package Toasts;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import Library.Signals.IRing;
import Library.Signals.ISignal;

/**
 * Created by ytgv8b on 14.01.2018.
 */

public class ToastMaker {
    public static void kostyl(Context context) {
        Toast toast = Toast.makeText(context, "Функция пока не доступна. Проект находится " +
                "в активной доработке ;)", Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showRemainingTime(Context context, IRing signal) {
        long millis = signal.remainingTimeInMillis();
        millis /= 1000L;

        long hours = millis / (3600L);
        millis -= hours*3600L;
        long minutes = millis / (60L);

        String time = String.format("Cпать осталось %02dч. %02dмин.", hours, minutes);

        Toast toast = Toast.makeText(context, time, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showValidate(Context context) {
        Toast toast = Toast.makeText(context, "введен неверный формат данных", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }
}
