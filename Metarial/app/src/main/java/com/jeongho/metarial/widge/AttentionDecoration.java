package com.jeongho.metarial.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jeongho.metarial.R;
import com.jeongho.qxblibrary.Utils.DisplayUtil;

/**
 * Created by Jeongho on 2016/8/3.
 */
public class AttentionDecoration extends RecyclerView.ItemDecoration{

    private Drawable mDivider;
    private Context mContext;

    public AttentionDecoration(Context context) {
        mContext = context;
        mDivider = context.getResources().getDrawable(R.drawable.attention_divider);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {


        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            final int left = parent.getPaddingLeft() + DisplayUtil.dp2px(mContext, 72);
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }
}
