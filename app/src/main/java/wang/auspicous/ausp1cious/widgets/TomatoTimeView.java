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

import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.bean.TomatoPeriodsBean;
import wang.auspicous.ausp1cious.bean.TomatoTimeStatus;

public class TomatoTimeView extends View {
    private int i = 0;
    private Float mWidth;
    private Float mHeight;

    private Float mInnerCircleRadius;
    private Float mProgressArcWidth;
    private Float mProgressArcRadius;

    private Float mSchedulerWidth;
    private Float mSchedulerRadius;


    private Paint mScheduleBorderPaint;
    private Paint mSchedulePaint;
    private Paint mScheduleOverTimePaint;

    private Paint mProgressPaint;
    private Paint mInnerCirclePaint;

    private int colorScheduler;
    private int colorSchedulerBorder;
    private int colorSchedulerOverTime;
    private int[] progressColor;
    private int colorInnerCircle;


    private float minLength;


    private TomatoPeriodsBean tomatoPeriodsBean;
    private TomatoTimeStatus tomatoTimeStatus;


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
        mSchedulerRadius = (float) (minLength * 0.8) / 2;
        mSchedulerWidth = (float) (mSchedulerRadius * 0.2);
        mProgressArcRadius = mSchedulerRadius - mSchedulerWidth / 2;
        mProgressArcWidth = mSchedulerWidth / 2;
        mInnerCircleRadius = mProgressArcRadius - mProgressArcWidth / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawColor(Color.WHITE);
        canvas.translate(mWidth / 2, mHeight / 2);
        drawProgress(canvas);
        drawSchedule(canvas);
        drawTomatoTime(canvas);
        canvas.restore();
    }

    private void init(Context context) {
        initColor(context);
        initPaint(context);
    }

    private void initPaint(Context context) {
        //任务表画笔
        mScheduleBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScheduleBorderPaint.setStyle(Paint.Style.STROKE);
        mScheduleBorderPaint.setStrokeWidth(2);
        mScheduleBorderPaint.setColor(colorSchedulerBorder);
        mSchedulePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSchedulePaint.setColor(colorScheduler);
        mScheduleOverTimePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScheduleOverTimePaint.setColor(colorSchedulerOverTime);

        //进度条画笔
        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaint.setColor(ContextCompat.getColor(context, R.color.white));
        mInnerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //里面圆圈画笔
        mInnerCirclePaint.setColor(Color.BLUE);
    }

    private void initColor(Context context) {
        colorScheduler = ContextCompat.getColor(context, R.color.green600);
        colorSchedulerOverTime = ContextCompat.getColor(context, R.color.orange500);
        colorSchedulerBorder = ContextCompat.getColor(context, R.color.green200);
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


    private void drawSchedule(Canvas canvas) {
        float startDegree = -90;
        int periods;
        if (tomatoPeriodsBean.isOverTime()) {
            periods = tomatoPeriodsBean.getConductedTime();
        } else {
            periods = tomatoPeriodsBean.getPlanTime();
        }
        float sweepDegree = 360 / (periods * 1f);
        float innerRadius = mSchedulerRadius - mSchedulerWidth / 2;
        for (int i = 1; i < periods + 1; i++) {
            if (periods == 3) { //图像会变形的地方特殊处理
                if (i == 2) {
                    Path path = drawRing(canvas, innerRadius, mSchedulerRadius, startDegree,
                            sweepDegree + 0.1f);
                    canvas.drawPath(path, mScheduleBorderPaint);
                    if (i < tomatoPeriodsBean.getConductedTime()) {
                        if (i > tomatoPeriodsBean.getPlanTime()) {//超时
                            canvas.drawPath(path, mScheduleOverTimePaint);
                        } else {//正常进度时间
                            canvas.drawPath(path, mSchedulePaint);
                        }
                    }
                    startDegree += sweepDegree;
                    continue;
                }
            }
            Path path = drawRing(canvas, innerRadius, mSchedulerRadius, startDegree, sweepDegree);
            canvas.drawPath(path, mScheduleBorderPaint);
            if (i <= tomatoPeriodsBean.getConductedTime()) {
                if (i > tomatoPeriodsBean.getPlanTime()) {//超时
                    canvas.drawPath(path, mScheduleOverTimePaint);
                } else {//正常进度时间
                    canvas.drawPath(path, mSchedulePaint);
                }
            }
            startDegree += sweepDegree;
        }
    }

    private void drawProgress(Canvas canvas) {
        //绘制背景色
        RectF mProgressRectF = new RectF(
                (-mProgressArcRadius + mProgressArcWidth / 2),
                -mProgressArcRadius + mProgressArcWidth / 2,
                mProgressArcRadius - mProgressArcWidth / 2,
                mProgressArcRadius - mProgressArcWidth / 2
        );
        mProgressPaint.setStrokeWidth(mProgressArcWidth);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setShader(null);
        canvas.drawArc(mProgressRectF, 270, 360, false, mProgressPaint);
        //绘制前景色
        mProgressPaint.setShader(new SweepGradient(0, 0, progressColor, null));
        canvas.drawArc(mProgressRectF, 270, tomatoTimeStatus.getRestRate()*360, false, mProgressPaint);
    }

    private void drawTomatoTime(Canvas canvas) {
        canvas.drawCircle(0, 0, mInnerCircleRadius, mInnerCirclePaint);
    }

    private Path drawRing(Canvas canvas, float innerRadius, float outerRadius,
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
        canvas.restore();
        return outerPath;
    }

    /**
     * 设置全部的进度
     */
    public void setPeriods(TomatoPeriodsBean periods) {
        this.tomatoPeriodsBean = periods;
        invalidate();
    }

    /**
     * 设置番茄时间的状态
     */
    public void setTomatoTimeStatus(TomatoTimeStatus tomatoTimeStatus) {
        this.tomatoTimeStatus = tomatoTimeStatus;
    }

    public void updateTomatoTimeStatus() {
        if (tomatoTimeStatus != null) {
            invalidate();
        }
    }


}
