package wang.auspicous.ausp1cious.widgets;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1ciouslib.utils.deviceutils.DensityUtils;

public class ProgressButtonView extends View {

    private float mWidth;
    private float mHeight;
    private float roundRectRadius = DensityUtils.dp2px(8);
    private float mProgressWidth = DensityUtils.dp2px(4);
    private float mRoundRectButtonWidth;
    private float mRoundRectButtonHeight;

    private Float mShadowRadius = 16f;

    private int colorButton;
    private int colorProgress;
    private int colorText;
    private int colorShadow;


    private Paint roundRectButtonPaint;
    private Paint progressPaint;
    private Paint shadowPaint;
    private Paint textPaint;

    private boolean press = false;

    private boolean useProgress = true;

    private int during = 2000;
    private float sweepAngle = 0f;
    private ValueAnimator valueAnimator;
    private String text;
    private OnProgressButtonClickListener listener;


    public ProgressButtonView(Context context) {
        this(context, null);
    }

    public ProgressButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = DensityUtils.dp2px(108);
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = DensityUtils.dp2px(64);
        }
        setMeasuredDimension(widthSize + getPaddingLeft() + getPaddingRight(),
                heightSize + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = (float) (getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
        mHeight = (float) (getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
        mRoundRectButtonWidth = mWidth - 2 * mProgressWidth;
        mRoundRectButtonHeight = mHeight - 2 * mProgressWidth;

        textPaint.setTextSize(mHeight * 0.24f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(getWidth() / 2f, getHeight() / 2f);
        if (useProgress) {
            drawProgress(canvas);
        }
        drawShadow(canvas);
        drawRoundRectButton(canvas);
        drawText(canvas);

        canvas.restore();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mShadowRadius = 8f;
            shadowPaint.setShadowLayer(mShadowRadius, 0, mShadowRadius / 2, colorShadow);
            if (useProgress) {
                press = false;
                valueAnimator.start();
            }
            invalidate();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            mShadowRadius = 16f;
            shadowPaint.setShadowLayer(mShadowRadius, 0, mShadowRadius / 2, colorShadow);
            if (useProgress) {
                if (press) {
                    valueAnimator.end();
                } else {
                    valueAnimator.reverse();
                }
            }
            if (listener != null) {
                listener.onClick();
            }
            invalidate();
        }
        return true;
    }

    private void init(Context context, AttributeSet attrs) {
        initColor(context);
        getAttributeSet(context, attrs);
        initPaint();
        initValueAnimator();
    }

    private void getAttributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ProgressButtonView);
        text = typedArray.getString(R.styleable.ProgressButtonView_text);
        colorButton = typedArray.getColor(R.styleable.ProgressButtonView_colorButton, colorButton);
        colorProgress = typedArray.getColor(R.styleable.ProgressButtonView_colorProgress,
                colorProgress);
        colorText = typedArray.getColor(R.styleable.ProgressButtonView_colorText, colorText);
        useProgress = typedArray.getBoolean(R.styleable.ProgressButtonView_useProgress, true);
        mProgressWidth = typedArray.getDimension(R.styleable.ProgressButtonView_progressWidth,
                mProgressWidth);
        roundRectRadius = typedArray.getDimension(R.styleable.ProgressButtonView_roundRectRadius,
                roundRectRadius);
        during = typedArray.getInteger(R.styleable.ProgressButtonView_during, during);
        typedArray.recycle();
    }

    private void initValueAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(during);
        valueAnimator.setRepeatCount(0);
        valueAnimator.addUpdateListener(animation -> {
            float f = (float) animation.getAnimatedValue();
            sweepAngle = 360 * f;
            if (f > 0.98) {
                press = true;
                if (listener != null) {
                    listener.onProgressEnd();
                }
            }
            invalidate();
        });
    }

    private void initPaint() {
        roundRectButtonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        roundRectButtonPaint.setColor(colorButton);
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(colorProgress);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(mProgressWidth);

        shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shadowPaint.setColor(colorButton);
        shadowPaint.setShadowLayer(mShadowRadius, 0, mShadowRadius / 2, colorShadow);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(colorText);
        textPaint.setTextAlign(Paint.Align.CENTER);

    }

    private void initColor(Context context) {
        colorButton = ContextCompat.getColor(context, R.color.orange400);
        colorProgress = ContextCompat.getColor(context, R.color.orange700);
        colorText = ContextCompat.getColor(context, R.color.white);
        colorShadow = ContextCompat.getColor(context, R.color.grey700);
    }


    private void drawRoundRectButton(Canvas canvas) {
        float width = mRoundRectButtonWidth / 2;
        float height = mRoundRectButtonHeight / 2;
        canvas.drawRoundRect(-width + roundRectRadius / 2 + mProgressWidth / 2,
                -height + roundRectRadius / 2 + mProgressWidth / 2,
                width - roundRectRadius / 2 - mProgressWidth / 2,
                height - roundRectRadius / 2 - mProgressWidth / 2,
                DensityUtils.dp2px(roundRectRadius), DensityUtils.dp2px(roundRectRadius),
                roundRectButtonPaint);
    }

    private void drawProgress(Canvas canvas) {
        float width = (mWidth) / 2;
        float height = mHeight / 2;
        float radius = (float) Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
        float roundRectRadius = DensityUtils.dp2px(this.roundRectRadius);
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        RectF rectFRoundRect = new RectF(-width + roundRectRadius / 2,
                -height + roundRectRadius / 2, width - roundRectRadius / 2,
                height - roundRectRadius / 2);
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
        progressPaint.setStrokeWidth(16);
        Path outerPath = new Path();
        outerPath.addRoundRect(rectFRoundRect, roundRectRadius, roundRectRadius,
                Path.Direction.CW);
        outerPath.op(innerPath, Path.Op.INTERSECT);
        canvas.drawPath(outerPath, progressPaint);
    }

    private void drawShadow(Canvas canvas) {
        float width = (mWidth) / 2;
        float height = mHeight / 2;
        float roundRectRadius = DensityUtils.dp2px(this.roundRectRadius);
        RectF rectFRoundRect = new RectF(-width + roundRectRadius / 2,
                -height + roundRectRadius / 2, width - roundRectRadius / 2,
                height - roundRectRadius / 2);
        canvas.drawRoundRect(rectFRoundRect, roundRectRadius, roundRectRadius, shadowPaint);
    }

    private void drawText(Canvas canvas) {
        if (!TextUtils.isEmpty(text)) {
            Paint.FontMetrics tomatoTimeFontMetrics = textPaint.getFontMetrics();
            canvas.drawText(text, 0, tomatoTimeFontMetrics.bottom, textPaint);
        }
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    public void setProgressMode(boolean useProgress) {
        this.useProgress = useProgress;
        invalidate();
    }

    public interface OnProgressButtonClickListener {
        void onProgressEnd();

        void onClick();
    }

    public void setOnProgressButtonClickListener(OnProgressButtonClickListener listener) {
        this.listener = listener;
    }
}
