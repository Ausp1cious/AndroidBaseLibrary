package wang.auspicous.ausp1cious.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.orhanobut.logger.Logger;

import wang.auspicous.ausp1ciouslib.utils.deviceutils.DensityUtils;

public class ProgressButtonView extends View {

    private float mWidth;
    private float mHeight;
    private float roundRectRadius = 16;
    private float mProgressWidth = DensityUtils.dp2px(4);
    private float mRoundRectButtonWidth;
    private float mRoundRectButtonHeight;


    private Paint roundRectButtonPaint;
    private Paint progressPaint;

    private int colorRoundRectButton;
    private int colorProgress;


    public ProgressButtonView(Context context) {
        this(context, null);
    }

    public ProgressButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = DensityUtils.dp2px(96);
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = DensityUtils.dp2px(56);
        }
        setMeasuredDimension(widthSize + getPaddingLeft() + getPaddingRight(),
                heightSize + getPaddingTop() + getPaddingBottom());
        Logger.i("onMeasure 实际宽度："+widthSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = (float) (getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
        mHeight = (float) (getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
        mRoundRectButtonWidth = mWidth - 2 * mProgressWidth;
        mRoundRectButtonHeight = mHeight - 2 * mProgressWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawColor(Color.BLACK);
        canvas.translate(getWidth() / 2f, getHeight() / 2f);
//        drawRoundRectButton(canvas);
        drawProgress(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void init() {
        initPaint();
    }

    private void initPaint() {
        roundRectButtonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        roundRectButtonPaint.setColor(Color.RED);

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(Color.GREEN);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(mProgressWidth);
//        mPaint.setShadowLayer(24, 0, 24, Color.GREEN);
    }


    private void drawRoundRectButton(Canvas canvas) {
        float width = mRoundRectButtonWidth / 2;
        float height = mRoundRectButtonHeight / 2;
        canvas.drawRoundRect(-width, -height, width, height,
                DensityUtils.dp2px(roundRectRadius), DensityUtils.dp2px(roundRectRadius),
                roundRectButtonPaint);
    }

    private void drawProgress(Canvas canvas) {
        float sweepAngle = 180f;
        float width = mWidth / 2;
        float height = mHeight / 2;
        float radius = (float) Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        RectF rectFRoundRect = new RectF(-width, -height, width, height);

        Path innerPath = new Path();
        innerPath.moveTo(0, 0);
        innerPath.addArc(rectF, 270, sweepAngle);
        innerPath.moveTo(0, 0);
        innerPath.lineTo((float) (radius * Math.cos(270 * Math.PI / 180)),
                (float) (radius * Math.sin(270 * Math.PI / 180)));
        innerPath.lineTo((float) (radius * Math.cos((270 + sweepAngle) * Math.PI / 180)),
                (float) (radius * Math.sin((270 + sweepAngle) * Math.PI / 180)));
        innerPath.close();
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(1);
//        progressPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(innerPath, progressPaint);
        Path outerPath = new Path();
        outerPath.addRoundRect(rectFRoundRect, DensityUtils.dp2px(8), DensityUtils.dp2px(8),
                Path.Direction.CW);
        roundRectButtonPaint.setStyle(Paint.Style.STROKE);
//        progressPaint.setStrokeWidth(4);
//        canvas.drawPath(outerPath, progressPaint);
//        progressPaint.setStyle(Paint.Style.FILL);
//        canvas.drawPath(innerPath,progressPaint);
//        canvas.drawPath(innerPath, roundRectButtonPaint);
        canvas.drawPath(outerPath, roundRectButtonPaint);
//        outerPath.op(innerPath, Path.Op.REVERSE_DIFFERENCE);
//        canvas.drawPath(outerPath,progressPaint);


    }
}
