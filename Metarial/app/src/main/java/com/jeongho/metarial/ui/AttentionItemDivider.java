package com.jeongho.metarial.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jeongho.metarial.R;
import com.jeongho.qxblibrary.Utils.DisplayUtil;

/**
 * Created by Jeongho on 2016/9/6.
 */
public class AttentionItemDivider extends RecyclerView.ItemDecoration{

    private Drawable mDivider;
    private Context mContext;
    public AttentionItemDivider(Context context) {
        mContext = context;
        mDivider = context.getResources().getDrawable(R.drawable.attention_divider);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int left = DisplayUtil.dp2px(mContext, 120);
            int bottom = top + mDivider.getIntrinsicHeight();
            int right = left + mDivider.getIntrinsicWidth() + params.rightMargin;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }
}
