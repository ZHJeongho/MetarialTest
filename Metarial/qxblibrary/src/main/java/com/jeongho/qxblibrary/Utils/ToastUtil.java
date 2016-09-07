package com.jeongho.qxblibrary.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jeongho on 16/6/29.
 */
public class ToastUtil {
    public static void showShort(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
