package com.jeongho.metarial.Utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Jeongho on 2016/7/22.
 */
public class TextFontUtil {
    private static TextFontUtil mTextFontUtil;
    private Context mContext;
    private TextFontUtil(Context context){
        mContext = context;
    }

    public static TextFontUtil get(Context context){
        if (mTextFontUtil == null){
            mTextFontUtil = new TextFontUtil(context);
        }
        return mTextFontUtil;
    }
    public Typeface getRegular(){
        return Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf");
    }

    public Typeface getMedium(){
        return Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Medium.ttf");
    }
}
