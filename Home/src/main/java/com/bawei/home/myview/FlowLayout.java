package com.bawei.home.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {

    private int measuredWidth;
    private int measuredHeight;
    private int currentWidth = 0;
    private int sumHeight = 0;
    private int sumWidth = 0;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if(i == 0){
                childView.layout(0,0,childView.getMeasuredWidth(),childView.getMeasuredHeight());
                sumWidth += childView.getMeasuredWidth();
                currentWidth += childView.getMeasuredWidth();
            }
            sumWidth += childView.getMeasuredWidth();
            if(sumWidth < getMeasuredWidth()){
                childView.layout(currentWidth,sumHeight,childView.getMeasuredWidth() + currentWidth,childView.getMeasuredHeight() + sumHeight);
                currentWidth += childView.getMeasuredWidth();
            }else{
                currentWidth = 0;
                sumWidth = childView.getMeasuredWidth();
                sumHeight += childView.getMeasuredHeight();
                childView.layout(currentWidth,sumHeight,currentWidth + childView.getMeasuredWidth(),childView.getMeasuredHeight() + sumHeight);
                currentWidth += childView.getMeasuredWidth();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

    }

}
