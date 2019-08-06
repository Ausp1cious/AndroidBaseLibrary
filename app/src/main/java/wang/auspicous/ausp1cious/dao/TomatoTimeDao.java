package wang.auspicous.ausp1cious.dao;

import wang.auspicous.ausp1cious.AppApplication;
import wang.auspicous.ausp1cious.dao.entity.DaoSession;
import wang.auspicous.ausp1cious.dao.entity.TomatoTimeEntity;
import wang.auspicous.ausp1cious.dao.entity.TomatoTimeEntityDao;
import wang.auspicous.ausp1cious.utils.AppTimeUtils;
import wang.auspicous.ausp1cious.utils.TomatoTimeUtils;

public class TomatoTimeDao {
    private TomatoTimeEntityDao tomatoTimeEntityDao;
    private DaoSession daoSession;
    private TomatoTimeDao(){
        daoSession = AppApplication.getInstance().getDaoSession();
        tomatoTimeEntityDao = daoSession.getTomatoTimeEntityDao();
    }
    private static class TomatoTimeDaoHolder{
        private static final TomatoTimeDao INSTANCE = new TomatoTimeDao();
    }
    public TomatoTimeDao getInstance() {
        return TomatoTimeDaoHolder.INSTANCE;
    }

    private void insertTomatoTime(TomatoTimeEntity tomatoTimeEntity) {
        tomatoTimeEntityDao.insert(tomatoTimeEntity);
    }

    public void insertTomatoTime(long taskID) {
        TomatoTimeEntity insertTomatoTime = new TomatoTimeEntity();
        insertTomatoTime.setTaskId(taskID);
        long currentTimeAsLong = AppTimeUtils.getCurrentTimeAsLong();
        insertTomatoTime.setTomatoTimeStart(currentTimeAsLong);
        insertTomatoTime.setTomatoTimeForecastEnd(TomatoTimeUtils.getForecastEndTimeAsLong(currentTimeAsLong));

    }

}
