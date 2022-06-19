package com.petterp.latte_core.util.Context;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.petterp.latte_core.app.Latte;

public class ToastUtils {
    public static void showText(String res) {
        Toast.makeText(Latte.getContext(), res, Toast.LENGTH_SHORT).show();
    }

    public static void showLongText(String res) {
        Toast.makeText(Latte.getContext(), res, Toast.LENGTH_LONG).show();
    }

    public static void showCenterText(String res) {
        Toast centerToast = Toast.makeText(Latte.getContext(), res, Toast.LENGTH_SHORT);
        centerToast.setGravity(Gravity.CENTER, 0, 0);
        centerToast.show();
    }

}
