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
        drawSchedule(canvas);
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
//        RectF mScheduleRectF = new RectF(
//                (float) (-minLength * 0.9 / 2),
//                (float) (-minLength * 0.9 / 2),
//                (float) (minLength * 0.9 / 2),
//                (float) (minLength * 0.9 / 2)
//        );
//        mScheduleBoderPaint.setStyle(Paint.Style.STROKE);
//        mScheduleBoderPaint.setStrokeWidth(4);
//        mSchedulePaint.setStyle(Paint.Style.STROKE);
//        mSchedulePaint.setStrokeWidth((float) (minLength*0.1/2));

        float startDegree = 270;
        int n = 33;
        float sweepDegree = 360 / (n * 1f);
        for (int i = 1; i < n+1; i++) {
            if (startDegree + sweepDegree == 270) {
                if (n == 5) {
                    drawRing(canvas, (float) (mInnerCircleRadius * 0.8), mInnerCircleRadius, Color.RED,
                            startDegree, sweepDegree+0.1f);
                }else {
                    drawRing(canvas, (float) (mInnerCircleRadius * 0.8), mInnerCircleRadius, Color.RED,
                            startDegree, sweepDegree+0.00001f);
                }

            } else {
                drawRing(canvas, (float) (mInnerCircleRadius * 0.8), mInnerCircleRadius, Color.RED,
                        startDegree, sweepDegree);
            }
            startDegree -= sweepDegree;
        }


//        for (int i = 1; i < 3; i++) {
//            completeScheduleDegree = (360 / 3) * i;
//            canvas.drawArc(mScheduleRectF, 270, completeScheduleDegree, true, mSchedulePaint);
//        }
//        canvas.drawCircle(0, 0, (float) (minLength*0.8/2+mProgressArcWidth*1.05),
//        mScheduleBoderPaint);



//        drawRing(canvas, (float) (mInnerCircleRadius * 0.8), mInnerCircleRadius, Color.RED,
//                0, 72);
//
//        drawRing(canvas, (float) (mInnerCircleRadius * 0.8), mInnerCircleRadius, Color.RED,
//                342, 72);
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
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint paintSolid = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paintSolid.setColor(Color.GREEN);
        Path innerPath = new Path();
        innerPath.lineTo((float) (innerRadius * Math.cos(startAngle % 360 * Math.PI / 180)),
                (float) (innerRadius * Math.sin(startAngle % 360 * Math.PI / 180)));
        innerPath.lineTo((float) (innerRadius * Math.cos((sweepAngle + startAngle) % 360 * Math.PI / 180)),
                (float) (innerRadius * Math.sin((sweepAngle + startAngle) % 360 * Math.PI / 180)));
        innerPath.close();
        innerPath.addArc(innerRectF, startAngle % 360, sweepAngle % 360);
        canvas.save();
        canvas.clipPath(innerPath);
//        canvas.drawPath(innerPath,paint);


        canvas.restore();
        canvas.save();
        Path outerPath = new Path();
        outerPath.lineTo((float) (outerRadius * Math.cos(startAngle % 360 * Math.PI / 180)),
                (float) (outerRadius * Math.sin(startAngle % 360 * Math.PI / 180)));
        outerPath.lineTo((float) (outerRadius * Math.cos((sweepAngle + startAngle) % 360 * Math.PI / 180)),
                (float) (outerRadius * Math.sin((sweepAngle + startAngle) % 360 * Math.PI / 180)));
        outerPath.close();
        outerPath.addArc(outerRectF, startAngle % 360, sweepAngle % 360);
        canvas.clipPath(outerPath);
//        canvas.drawPath(outerPath, paint);


        outerPath.op(innerPath, Path.Op.DIFFERENCE);
        canvas.drawPath(outerPath, paintSolid);
        canvas.drawPath(outerPath, paint);
        canvas.restore();
    }

}
