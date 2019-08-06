package wang.auspicous.ausp1cious.utils;

import wang.auspicous.ausp1cious.bean.TomatoSettingBean;
import wang.auspicous.ausp1ciouslib.utils.sharedpreferences.SpHelper;
import wang.auspicous.ausp1ciouslib.utils.sharedpreferences.SpUtils;

public class AppSpUtils extends SpUtils {
    private static final String TOMATO_TIME_CONFIGURATION = "tomatoTimeConfiguration";

    /**
     * 设置番茄时间默认配置
     */
    public static void setTomatoTimeConfiguration(TomatoSettingBean settingBean) {
        SpHelper.putObj(TOMATO_TIME_CONFIGURATION, settingBean);
    }

    public static TomatoSettingBean getTomatoTimeConfiguration() {
        return SpHelper.getObj(TOMATO_TIME_CONFIGURATION, TomatoSettingBean.class);
    }

}
