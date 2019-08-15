package wang.auspicous.ausp1cious.Presenters;

import android.annotation.SuppressLint;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle3.android.ActivityEvent;

import io.reactivex.functions.Consumer;
import wang.auspicous.ausp1cious.Presenters.Contracts.TomatoTimeContract;
import wang.auspicous.ausp1cious.bean.ShowSingleTimeBean;
import wang.auspicous.ausp1cious.bean.TomatoPeriodsBean;
import wang.auspicous.ausp1cious.bean.TomatoSettingBean;
import wang.auspicous.ausp1cious.bean.TomatoTimeShowBean;
import wang.auspicous.ausp1cious.constants.TomatoTimeStatus;
import wang.auspicous.ausp1cious.dao.entity.TomatoTimeEntity;
import wang.auspicous.ausp1cious.utils.AppSpUtils;
import wang.auspicous.ausp1cious.utils.AppTimeUtils;
import wang.auspicous.ausp1cious.utils.RxTimeUtils;
import wang.auspicous.ausp1cious.utils.TomatoTimeUtils;
import wang.auspicous.ausp1ciouslib.base.presenter.BasePresenterImpl;

public class TomatoTimePresenterImpl extends BasePresenterImpl<TomatoTimeContract.TomatoTimeView> implements TomatoTimeContract.TomatoTimePresenter {

    private TomatoTimeShowBean tomatoTimeShowBean;
    TomatoTimeEntity mTomatoTimeEntity;

    @Override
    public void getTomatoTimeData() {
        mTomatoTimeEntity = mockData();
        tomatoTimeShowBean = new TomatoTimeShowBean();
        tomatoTimeShowBean.setTomatoPeriodsBean(new TomatoPeriodsBean(3, 2));
        if (getView() != null) {
            getView().setTomatoTimeViewDate(tomatoTimeShowBean);
        }
        initTomatoTimeViewShow();
    }

    private TomatoTimeEntity mockData() {
        TomatoTimeEntity tomatoTimeEntity = new TomatoTimeEntity();
        tomatoTimeEntity.setTomatoTimeStart(AppTimeUtils.getCurrentTimeAsLong());
        tomatoTimeEntity.setTomatoTimePrepareStarted(AppTimeUtils.getCurrentTimeAsLong());
        TomatoSettingBean tomatoTimeConfiguration = AppSpUtils.getTomatoTimeConfiguration();
        tomatoTimeEntity.setPlanTime(tomatoTimeConfiguration.getPlanTime());
        tomatoTimeEntity.setTomatoTime(tomatoTimeConfiguration.getUnitTime());
        tomatoTimeEntity.setSummarizeTime(tomatoTimeConfiguration.getSummarizeTime());
        tomatoTimeEntity.setTomatoTimeStatus(TomatoTimeStatus.TOMATO_STATUS_PREPARE);
        return tomatoTimeEntity;
    }

    @SuppressLint("CheckResult")
    private void initTomatoTimeViewShow() {
        if (getView() != null) {
            RxTimeUtils.showScreenTime(getView().bindApiLifecycle(ActivityEvent.DESTROY)).subscribe(o -> {
                switch (mTomatoTimeEntity.getTomatoTimeStatus()) {
                    case TomatoTimeStatus.TOMATO_STATUS_PREPARE:
                        ShowSingleTimeBean planRestTime =
                                TomatoTimeUtils.getRestTime(mTomatoTimeEntity.getTomatoTimePrepareStarted(),
                                        mTomatoTimeEntity.getPlanTime());
                        tomatoTimeShowBean.setShowSingleTimeBean(planRestTime);
                        tomatoTimeShowBean.setTomatoTimeStatus("准备阶段");
                        tomatoTimeShowBean.setRestRate(TomatoTimeUtils.getRestRate(mTomatoTimeEntity.getTomatoTimePrepareStarted(),
                                mTomatoTimeEntity.getPlanTime()));
                        if (AppTimeUtils.getCurrentTimeAsLong() > mTomatoTimeEntity.getTomatoTimePrepareStarted() + mTomatoTimeEntity.getPlanTime()) {
                            mTomatoTimeEntity.setTomatoTimeStatus(TomatoTimeStatus.TOMATO_STATUS_TOMATO_TIME);
                            mTomatoTimeEntity.setTomatoTimeStarted(AppTimeUtils.getCurrentTimeAsLong());
                        }
                        break;
                    case TomatoTimeStatus.TOMATO_STATUS_TOMATO_TIME:
                        ShowSingleTimeBean tomatoTimeRestTime =
                                TomatoTimeUtils.getRestTime(mTomatoTimeEntity.getTomatoTimeStarted(),
                                        mTomatoTimeEntity.getTomatoTime());
                        tomatoTimeShowBean.setShowSingleTimeBean(tomatoTimeRestTime);
                        tomatoTimeShowBean.setTomatoTimeStatus("番茄时间阶段");
                        tomatoTimeShowBean.setRestRate(TomatoTimeUtils.getRestRate(mTomatoTimeEntity.getTomatoTimeStarted(),
                                mTomatoTimeEntity.getTomatoTime()));
                        break;
                    case TomatoTimeStatus.TOMATO_STATUS_SUMMARIZE:
                        break;
                }
                getView().updateTomatoTime();
            });
        }
    }
}
