package com.jeongho.metarial.ui;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Jeongho on 2016/7/20.
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;
    @Override
    public void transformPage(View page, float position) {

        int pageHeight = page.getHeight();
        int pageWidth = page.getWidth();

        if (position < -1){
            page.setAlpha(0);
        }

        if (position >= -1 && position <= 1){//the page view is displaying on the screen

            float scale = Math.max(MIN_SCALE, 1 - Math.abs(position));

            //calculate the margins in the horizontal direction and vertical direction
            float horiMargin = ( 1 - scale ) * pageWidth / 2;
            float vertMargin = ( 1 - scale ) * pageHeight / 2;

            if (position < 0){
                page.setTranslationX(horiMargin - vertMargin / 2);
            }else {
                page.setTranslationX(vertMargin / 2 - horiMargin);
            }

            page.setScaleX(scale);
            page.setScaleY(scale);

            page.setAlpha(MIN_ALPHA + (scale - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        }

        if (position > 1){
            page.setAlpha(0);
        }
    }
}
