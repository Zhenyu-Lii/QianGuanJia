package com.petterp.latte_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.petterp.latte_core.app.Latte;

public class DimenUtil {


    /**得到屏幕的宽
     * @return
     */
    public static int getScreenWidth(){
        final Resources resources=Latte.getContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    /**得到屏幕的高
     * @return
     */
    public static int getScreenHeight(){
        final Resources resources=Latte.getContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
