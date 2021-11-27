package com.bawei.home.myview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import com.bawei.home.R;

import java.util.Timer;
import java.util.TimerTask;

public class CountDown extends View {

    private Paint mPaint = null;
    private Paint txtPaint = null;
    private int mColor = Color.GRAY;
    private int textColor = Color.LTGRAY;
    private int strokeWidth = 3;
    private int textSize = 30;
    private int defaultWidth = 100;
    private int defaultHeight = 100;
    private int startAngle = 0;
    private int sweepAngle = 0;
    private String text = "5S";
    private int centerX;
    private int centerY;
    private int animatorTime = 5000;
    private int padding = 10;
    private Timer timer = null;
    private int countDown = 5;

    public CountDown(Context context) {
        super(context);
        init();
    }

    public CountDown(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCountDown);
        mColor = typedArray.getColor(R.styleable.MyCountDown_strokeC, mColor);
        strokeWidth = typedArray.getInteger(R.styleable.MyCountDown_strokeW, strokeWidth);
        textSize = typedArray.getInteger(R.styleable.MyCountDown_textSize,textSize);
        textColor = typedArray.getColor(R.styleable.MyCountDown_textColor,textColor);
        typedArray.recycle();
    }

    public CountDown(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = getMeasuredWidth() / 2;
        centerY = getMeasuredHeight() / 2;

    }

    public void init(){

        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        txtPaint = new Paint();
        txtPaint.setTextSize(textSize);
        txtPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,defaultHeight);
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,heightSize);
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,defaultHeight);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(0 + padding,0 + padding,getMeasuredWidth() - padding,getMeasuredHeight() - padding);

        canvas.drawArc(rectF,startAngle,sweepAngle,false,mPaint);

        Rect bounds = new Rect();
        txtPaint.getTextBounds(text,0,text.length(),bounds);
        float offSet = (bounds.top + bounds.bottom) / 2;

        canvas.drawText(text,centerX,centerY - offSet,txtPaint);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimator();
    }

    private void startAnimator(){

        if(timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                countDown--;
                text = countDown + "S";
                if(countDown == 0){
                    timer.cancel();
                }
            }
        },1000,1000);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(animatorTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatorValue = (float) animation.getAnimatedValue();
                sweepAngle = (int) (0 + animatorValue * 360);
                if(Looper.getMainLooper().getThread() == Thread.currentThread()){
                    invalidate();
                }else{
                    postInvalidate();
                }
            }
        });
        valueAnimator.start();
    }

}
