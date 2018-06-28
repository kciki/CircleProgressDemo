package com.example.circleprogressview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by xq
 * on 2018/6/28.
 **/
public class CircleProgressView extends View {


    private Paint progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint percentageNumberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int defaultColor = Color.GREEN;
    private int defaultAngle = 0;
    private int defaultProgresstWidth = 10;
    private int defaultTextSize = 10;
    private int defaultWidth = 200;
    private int defaultHeight = 200;
    private int defaultSweepAngle = 180;

    private int startColor;
    private int endColor;
    private int textColor;
    private int startAngle;
    private float textSize;
    private float progressWidth;
    private int sweepAngle;

    private float progress = 0;

    private String percentage;
    private String percentageNumberStr = "  %";


    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        startColor = ta.getColor(R.styleable.CircleProgressView_start_color, defaultColor);
        endColor = ta.getColor(R.styleable.CircleProgressView_end_color, defaultColor);
        textColor = ta.getColor(R.styleable.CircleProgressView_text_color, defaultColor);
        startAngle = ta.getInteger(R.styleable.CircleProgressView_start_angle, defaultAngle);
        textSize = ta.getDimension(R.styleable.CircleProgressView_text_size, defaultTextSize);
        progressWidth = ta.getDimension(R.styleable.CircleProgressView_progress_width, defaultProgresstWidth);
        sweepAngle = ta.getInteger(R.styleable.CircleProgressView_sweep_angle, defaultSweepAngle);
        init();
    }

    private void init() {

        progressPaint.setStrokeWidth(progressWidth);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);

        percentageNumberPaint.setTextSize(textSize/3);
        percentageNumberPaint.setStyle(Paint.Style.STROKE);
        percentageNumberPaint.setColor(textColor);
        percentageNumberPaint.setAntiAlias(true);

        textPaint.setTextSize(textSize);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST
                && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, defaultHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, defaultHeight);
        }
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int size = Math.min(getHeight(), getWidth());

        float outerRadius = (size / 2) - (progressWidth / 2);
        float offsetY = (canvas.getHeight() - outerRadius * 2) / 2;
        Shader shader = new LinearGradient(0, offsetY, 0, offsetY + outerRadius * 2, new int[]{startColor,
                endColor},
                null, Shader.TileMode.CLAMP);
        progressPaint.setShader(shader);

        RectF rectF = new RectF(progressWidth/2 + paddingLeft,
                progressWidth/2 + paddingTop,
                getWidth()-progressWidth/2 - paddingRight,
                getHeight()-progressWidth/2 - paddingBottom);


        canvas.drawArc(rectF,startAngle,progress,false,progressPaint);



        int width = getWidth()-paddingLeft-paddingRight;
        int height = getHeight()-paddingTop-paddingBottom;
        percentage =String.valueOf((int)( progress/360*100));
        Rect bounds = new Rect();
        textPaint.getTextBounds(percentage,0,percentage.length(),bounds);
        canvas.drawText(percentage,width/2- bounds.width()/2+paddingLeft,height/2 + bounds.height()/2+paddingTop,textPaint);
        canvas.drawText(percentageNumberStr,width/2+bounds.width()/2+paddingLeft,height/2 + bounds.height()/2+paddingTop,percentageNumberPaint);


    }

    public void startAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "progress", 0, sweepAngle);
        animator.setDuration(1600);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.start();
    }

}
