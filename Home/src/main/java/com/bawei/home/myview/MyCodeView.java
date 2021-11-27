package com.bawei.home.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyCodeView extends LinearLayout {

    private Paint mPaint;
    private int strokeWidth = 2;

    public MyCodeView(Context context) {
        super(context);
        init();
    }

    public MyCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){

        for (int i = 0; i < 4;i++){
            EditText editText = new EditText(getContext());
            initChildView(editText,i);
            if(i == 0){
                editText.setFocusable(true);
            }
        }

    }

    private void initChildView(EditText editText,int position) {

        mPaint = new Paint();
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        RectF rectF = new RectF(20,20,20,20);

        Canvas canvas = new Canvas();
        canvas.drawRect(rectF,mPaint);

        editText.draw(canvas);

        addView(editText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);



    }

}
