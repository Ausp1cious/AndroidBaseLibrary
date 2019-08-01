package wang.auspicous.ausp1ciouslib.component.bean;

import java.math.BigDecimal;

public class BatteryBean extends BaseBean {
    private int currentLevel;
    private int totalScale;

    public BatteryBean(int currentLevel, int totalScale) {
        this.currentLevel = currentLevel;
        this.totalScale = totalScale;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getTotalScale() {
        return totalScale;
    }

    public double getCurrenBatteryRate() {
        if (totalScale != 0) {
            return new BigDecimal(currentLevel / (totalScale * 1.0)).setScale(4,
                    BigDecimal.ROUND_DOWN).doubleValue();
        } else {
            return 0;
        }
    }
}
