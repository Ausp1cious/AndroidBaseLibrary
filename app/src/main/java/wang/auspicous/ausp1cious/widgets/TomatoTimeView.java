package wang.auspicous.ausp1cious.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.orhanobut.logger.Logger;

import java.math.BigDecimal;

import wang.auspicous.ausp1cious.R;

public class TomatoTimeView extends View {
    private int i = 0;
    private Float mWidth;
    private Float mHeight;

    private Float mInnerCircleRadius;

    private Paint mScheduleBoderPaint;
    private Paint mSchedulePaint;

    private Paint mProgressPaint;
    private Paint mInnerCirclePaint;

    private Float mProgressArcWidth;
    private float minLength;
    private int[] progressColor;

    private int periods;


    public TomatoTimeView(Context context) {
        this(context, null);
    }

    public TomatoTimeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TomatoTimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = (float) (getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
        mHeight = (float) (getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
        minLength = Math.min(mWidth, mHeight);
        mInnerCircleRadius = (float) (minLength * 0.8) / 2;

        mProgressArcWidth = (float) (0.02 * minLength);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawColor(Color.WHITE);
        canvas.translate(mWidth / 2, mHeight / 2);
        if (periods != 0) {
            drawSchedule(canvas);
        }
//        drawProgress(canvas);
//        drawTomatoTime(canvas);
        canvas.restore();
    }

    private void init(Context context) {
        initPaint(context);
        progressColor = new int[]{
                ContextCompat.getColor(context, R.color.cyan300),
                ContextCompat.getColor(context, R.color.cyan400),
                ContextCompat.getColor(context, R.color.cyan500),
                ContextCompat.getColor(context, R.color.cyan600),
                ContextCompat.getColor(context, R.color.cyan600),
                ContextCompat.getColor(context, R.color.cyan500),
                ContextCompat.getColor(context, R.color.cyan400),
                ContextCompat.getColor(context, R.color.cyan300),

        };
    }

    private void initPaint(Context context) {
        mScheduleBoderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScheduleBoderPaint.setColor(Color.GREEN);

        mSchedulePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSchedulePaint.setColor(Color.RED);

        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaint.setColor(ContextCompat.getColor(context, R.color.white));
        mInnerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerCirclePaint.setColor(Color.BLUE);
    }


    private void drawSchedule(Canvas canvas) {
        float startDegree = -90;
        float sweepDegree = 360 / (periods * 1f);
        for (int i = 1; i < periods + 1; i++) {
            if (periods == 3) {
                if (i == 2) {
                    drawRing(canvas, (float) (mInnerCircleRadius * 0.8), mInnerCircleRadius, Color
                                    .RED,
                            startDegree, sweepDegree+0.1f);
                    startDegree += sweepDegree;
                    continue;
                }
            }
            drawRing(canvas, (float) (mInnerCircleRadius * 0.8), mInnerCircleRadius, Color
                            .RED,
                    startDegree, sweepDegree);
            startDegree += sweepDegree;
        }

//        float startDegree = -90;
//        float sweepDegree = 360 / (3 * 1f);
//        drawRing(canvas, (float) (mInnerCircleRadius * 0.8), mInnerCircleRadius, Color.RED,
//                startDegree+sweepDegree*1, sweepDegree);
    }

    private void drawProgress(Canvas canvas) {
        //绘制背景色
        RectF mProgressRectF = new RectF(
                (-mInnerCircleRadius - mProgressArcWidth / 2),
                -mInnerCircleRadius - mProgressArcWidth / 2,
                mInnerCircleRadius + mProgressArcWidth / 2,
                mInnerCircleRadius + mProgressArcWidth / 2
        );
        mProgressPaint.setStrokeWidth(mProgressArcWidth);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(mProgressRectF, 0, 360, false, mProgressPaint);
        //绘制前景色
        mProgressPaint.setShader(new SweepGradient(0, 270, progressColor, null));
        canvas.drawArc(mProgressRectF, 270, 270, false, mProgressPaint);
    }

    private void drawTomatoTime(Canvas canvas) {
        canvas.drawCircle(0, 0, mInnerCircleRadius, mInnerCirclePaint);
    }

    private void drawRing(Canvas canvas, float innerRadius, float outerRadius, int color,
                          float startAngle, float sweepAngle) {
        RectF outerRectF = new RectF(
                -outerRadius,
                -outerRadius,
                outerRadius,
                outerRadius
        );
        RectF innerRectF = new RectF(
                -innerRadius,
                -innerRadius,
                innerRadius,
                innerRadius
        );
        canvas.save();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint paintSolid = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paintSolid.setColor(Color.GREEN);
        Path innerPath = new Path();
        innerPath.addArc(innerRectF, startAngle, sweepAngle);
        innerPath.moveTo(0, 0);
        innerPath.lineTo((float) (innerRadius * Math.cos(startAngle * Math.PI / 180)),
                (float) (innerRadius * Math.sin(startAngle * Math.PI / 180)));
        innerPath.lineTo((float) (innerRadius * Math.cos((sweepAngle + startAngle) * Math.PI / 180)),
                (float) (innerRadius * Math.sin((sweepAngle + startAngle) * Math.PI / 180)));
        innerPath.close();
        canvas.clipPath(innerPath);
        canvas.restore();
        canvas.save();
        Path outerPath = new Path();
        outerPath.addArc(outerRectF, startAngle, sweepAngle);
        outerPath.moveTo(0, 0);
        outerPath.lineTo((float) (outerRadius * Math.cos(startAngle * Math.PI / 180)),
                (float) (outerRadius * Math.sin(startAngle * Math.PI / 180)));
        outerPath.lineTo((float) (outerRadius * Math.cos((sweepAngle + startAngle) * Math.PI / 180)),
                (float) (outerRadius * Math.sin((sweepAngle + startAngle) * Math.PI / 180)));
        outerPath.close();
        outerPath.op(innerPath, Path.Op.DIFFERENCE);
        canvas.drawPath(outerPath, paintSolid);
        canvas.drawPath(outerPath, paint);
        canvas.restore();
    }

    public void setPeriods(int n) {
        this.periods = n;
        invalidate();
    }
}
