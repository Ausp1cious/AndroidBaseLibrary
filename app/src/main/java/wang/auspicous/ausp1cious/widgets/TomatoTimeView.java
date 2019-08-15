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

import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.bean.ShowSingleTimeBean;
import wang.auspicous.ausp1cious.bean.TomatoPeriodsBean;
import wang.auspicous.ausp1cious.bean.TomatoTimeShowBean;

public class TomatoTimeView extends View {
    private int i = 0;
    private Float mWidth;
    private Float mHeight;

    private Float mInnerCircleRadius;
    private Float mProgressArcWidth;
    private Float mProgressArcRadius;

    private Float mSchedulerWidth;
    private Float mSchedulerRadius;

    private Float mCircleTimeSize;
    private Float mCircleTimeUnitSize;
    private Float mCircleStatusSize;


    private Paint mScheduleBorderPaint;
    private Paint mSchedulePaint;
    private Paint mScheduleOverTimePaint;

    private Paint mProgressPaint;
    private Paint mInnerCirclePaint;

    private Paint mCircleTimePaint;
    private Paint mCircleTimeUnitPaint;
    private Paint mCircleTimeStatus;

    private int colorScheduler;
    private int colorSchedulerBorder;
    private int colorSchedulerOverTime;
    private int[] progressColor;
    private int progressColorBackground;
    private int colorInnerCircle;
    private int colorCircleTime;
    private int colorCircleTimeUnit;
    private int colorCircleTimeStatus;


    private float minLength;

    private TomatoTimeShowBean tomatoTimeShowBean;


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
        mSchedulerRadius = (float) (minLength * 0.9) / 2;
        mSchedulerWidth = (float) (mSchedulerRadius * 0.2);
        mProgressArcRadius = mSchedulerRadius - mSchedulerWidth / 2;
        mProgressArcWidth = mSchedulerWidth / 2;
        mInnerCircleRadius = mProgressArcRadius - mProgressArcWidth / 2;

        mCircleTimeSize = minLength * 0.3f;
        mCircleTimePaint.setTextSize(mCircleTimeSize);

        mCircleTimeUnitSize = mCircleTimeSize * 0.2f;
        mCircleTimeUnitPaint.setTextSize(mCircleTimeUnitSize);

        mCircleStatusSize = mCircleTimeSize * 0.22f;
        mCircleTimeStatus.setTextSize(mCircleStatusSize);
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
        mProgressPaint.setColor(progressColorBackground);
        //里面圆圈画笔
        mInnerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerCirclePaint.setColor(colorInnerCircle);
        //圈里面的时间文字
        mCircleTimePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleTimePaint.setColor(colorCircleTime);
        mCircleTimePaint.setTextAlign(Paint.Align.CENTER);
        //圆里面时间文字单位
        mCircleTimeUnitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleTimeUnitPaint.setColor(colorCircleTime);
        mCircleTimeUnitPaint.setTextAlign(Paint.Align.CENTER);
        //圆里面时间文字状态
        mCircleTimeStatus = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleTimeStatus.setColor(colorCircleTime);
        mCircleTimeStatus.setTextAlign(Paint.Align.CENTER);
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
        progressColorBackground = ContextCompat.getColor(context, R.color.white);
        colorInnerCircle = ContextCompat.getColor(context, R.color.blue500);
        colorCircleTime = ContextCompat.getColor(context, R.color.white);
        colorCircleTimeUnit = ContextCompat.getColor(context, R.color.white);
        colorCircleTimeStatus = ContextCompat.getColor(context, R.color.white);
    }

    private void drawSchedule(Canvas canvas) {
        float startDegree = -90;
        int periods;
        if (tomatoTimeShowBean == null || tomatoTimeShowBean.getTomatoPeriodsBean() == null) {
            return;
        }
        TomatoPeriodsBean tomatoPeriodsBean = tomatoTimeShowBean.getTomatoPeriodsBean();
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
        if (tomatoTimeShowBean == null) {
            return;
        }
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
        canvas.drawArc(mProgressRectF, 270, tomatoTimeShowBean.getRestRate() * 360, false,
                mProgressPaint);
    }

    private void drawTomatoTime(Canvas canvas) {
        canvas.drawCircle(0, 0, mInnerCircleRadius, mInnerCirclePaint);
        if (tomatoTimeShowBean == null || tomatoTimeShowBean.getShowSingleTimeBean() == null) {
            return;
        }
        ShowSingleTimeBean showSingleTimeBean = tomatoTimeShowBean.getShowSingleTimeBean();
        //显示倒计时文字
        Paint.FontMetrics tomatoTimeFontMetrics = mCircleTimePaint.getFontMetrics();
        Paint.FontMetrics tomatoTimeUnitFontMetrics = mCircleTimeUnitPaint.getFontMetrics();
        Paint.FontMetrics tomatoTimeStatusFontMetrics = mCircleTimeStatus.getFontMetrics();
        float distanceTimeBetweenUnit =
                -tomatoTimeUnitFontMetrics.top * 2;
        float distanceUnitBetweenStatus =
                distanceTimeBetweenUnit + tomatoTimeUnitFontMetrics.bottom - tomatoTimeStatusFontMetrics.top;
        //显示当前时间单位
        canvas.drawText(showSingleTimeBean.getTime(), 0, tomatoTimeFontMetrics.bottom * 0.5f,
                mCircleTimePaint);
        canvas.drawText(showSingleTimeBean.getTimeUnit(), 0, distanceTimeBetweenUnit,
                mCircleTimeUnitPaint);
        //显示当前状态
        canvas.drawText(tomatoTimeShowBean.getTomatoTimeStatus(), 0, distanceUnitBetweenStatus,
                mCircleTimeStatus);
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

    public void setTomatoTimeShowBean(TomatoTimeShowBean tomatoTimeShowBean) {
        this.tomatoTimeShowBean = tomatoTimeShowBean;
    }

    public void updateTomatoTime() {
        if (tomatoTimeShowBean != null) {
            invalidate();
        }
    }


}
