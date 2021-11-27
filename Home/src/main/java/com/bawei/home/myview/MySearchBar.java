package com.bawei.home.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

public class MySearchBar extends androidx.appcompat.widget.AppCompatEditText {

    private float rx = 50f;
    private float ry = 50f;
    private Paint paint = null;
    private int strokeWidth = 2;
    private int mColor = Color.WHITE;
    private int textSize = 20;

    public MySearchBar(Context context) {
        super(context);
        init();
    }

    public MySearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(mColor);
        paint.setTextSize(textSize);

        setBackground(null);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(15,15,getMeasuredWidth() - 15,getMeasuredHeight() - 15);

        canvas.drawRoundRect(rectF,rx,ry,paint);

    }
}
