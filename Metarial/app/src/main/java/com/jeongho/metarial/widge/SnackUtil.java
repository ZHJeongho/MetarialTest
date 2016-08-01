package com.jeongho.metarial.widge;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.jeongho.metarial.R;

/**
 * Created by Jeongho on 16/8/1.
 */
public class SnackUtil {

    public static final int INFO = 1;
    public static final int WARN = 2;
    public static final int ALERT = 3;

    public static final int INFO_COLOR = 0xFF3F51B5;
    public static final int WARN_COLOR = 0xFFCDDC39;
    public static final int ALERT_COLOR = 0xFFFF0000;

    public static Snackbar createShortSnackbar(View view, String message, int type){
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        setSnackbarBackgroud(snackbar, type);
        return snackbar;
    }

    public static Snackbar createShortSnackbar(View view, int resId, int type){
        String message = view.getResources().getString(resId);
        return createShortSnackbar(view, message, type);
    }

    public static Snackbar setSnackColor(Snackbar snackbar, int snackType, int textColor){
        setSnackbarBackgroud(snackbar, snackType);
        setSnackbarBackgroud(snackbar, textColor);
        return snackbar;
    }

    private static void setSnackbarBackgroud(Snackbar snackbar, int snackbarType) {
        switch (snackbarType){
            case INFO:
                setBackground(snackbar, INFO_COLOR);
                break;
            case WARN:
                setBackground(snackbar, WARN_COLOR);
                break;
            case ALERT:
                setBackground(snackbar, ALERT_COLOR);
                break;
        }
    }

    private static void setBackground(Snackbar snackbar, int color) {
        View view = snackbar.getView();
        if (view != null){
            view.setBackgroundColor(color);
        }
    }

    private static void setSnackbarTextColor(Snackbar snackbar, int textColor){
        View messageView = snackbar.getView();
        TextView tv = (TextView) messageView.findViewById(R.id.snackbar_text);
        tv.setTextColor(textColor);
    }
}
