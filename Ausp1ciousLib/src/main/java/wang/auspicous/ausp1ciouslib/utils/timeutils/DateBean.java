package wang.auspicous.ausp1ciouslib.utils.timeutils;

import androidx.annotation.NonNull;
import wang.auspicous.ausp1ciouslib.component.BaseBean;

/**
 * Created by Ausp1cious on 2019/3/27.
 * 日期类（参考LocalDate）
 */
public final class DateBean extends BaseBean {
    private static final int DAYS_PER_CYCLE = 146097;
    static final long DAYS_0000_TO_1970 = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);

    private final int year;
    private final short month;
    private final short day;

    private DateBean(int year, int month, int day) {
        this.year = year;
        this.month = (short) month;
        this.day = (short) day;
    }

    public static DateBean of(int year, int month, int dayOfMonth) {
        return create(year, month, dayOfMonth);
    }

    /**
     * 去获取当前年份
     */
    public String getYear() {
        return String.valueOf(year);
    }

    /**
     * 获取当前月份
     */
    public String getMonth() {
        return String.valueOf(month);
    }

    /**
     * 获取当前日期
     */
    public String getDay() {
        return String.valueOf(day);
    }

    /**
     * 添加天数
     *
     * @param daysToAdd 添加的天数
     * @return 添加天数后的日期
     */
    public DateBean plusDays(long daysToAdd) {
        if (daysToAdd == 0) {
            return this;
        }
        long mjDay = toEpochDay() + daysToAdd;
        return ofEpochDay(mjDay);
    }

    /**
     * 减少天数
     * @param daysToSubtract 减少的天数
     * @return 减少后的天数
     */
    public DateBean minusDays(long daysToSubtract) {
        return (daysToSubtract == Long.MAX_VALUE ? new DateBean(-1, -1, -1) : plusDays(
                -daysToSubtract));
    }

    /**
     * 添加周数
     *
     * @param weeksToAdd 添加的周数
     * @return 添加周数后的日期
     */
    public DateBean plusWeeks(long weeksToAdd) {
        return plusDays(weeksToAdd * 7);
    }
    /**
     * 减少周数
     * @param weeksToSubtract 减少的周数
     * @return 减少周数后的日期
     */
    public DateBean minusWeeks(long weeksToSubtract) {
        return (weeksToSubtract == Long.MAX_VALUE ? new DateBean(-1, -1, -1) : plusWeeks(
                -weeksToSubtract));
    }

    /**
     * 添加月份
     * @param monthsToAdd 添加的月份
     * @return 添加月份后的日期
     */
    public DateBean plusMonths(long monthsToAdd) {
        if (monthsToAdd == 0) {
            return this;
        }
        long monthCount = year * 12L + (month - 1);
        long calcMonths = monthCount + monthsToAdd;  // safe overflow
        int newYear = (int) (calcMonths / 12);
        int newMonth = (int) ((calcMonths / 12) + 1);
        return resolvePreviousValid(newYear, newMonth, day);
    }

    /**
     * 减少月份
     * @param monthToSubtract 减少的月份
     * @return 减少后的月份
     */
    public DateBean minusMonths(long monthToSubtract) {
        return (monthToSubtract == Long.MAX_VALUE ? new DateBean(-1, -1, day) : plusMonths(
                -monthToSubtract));
    }

    /**
     * 添加年份
     * @param yearsToAdd 添加的年份
     * @return 添加后的年份
     */
    public DateBean plusYears(long yearsToAdd) {
        if (yearsToAdd == 0) {
            return this;
        }
        int newYear = (int) (year + yearsToAdd);
        return resolvePreviousValid(newYear, month, day);
    }

    /**
     * 减少年份
     * @param yearsToSubtract 减少的年份
     * @return 减少后的年份
     */
    public DateBean minusYears(long yearsToSubtract) {
        return (yearsToSubtract == Long.MAX_VALUE ? new DateBean(-1, month, day) : plusYears(
                -yearsToSubtract));
    }

    /**
     * 获取日期
     * @return 日期，格式为：yyyy-MM-dd
     */
    public String getDate() {
        return year + "-" + month + "-" + day;
    }

    /**
     * 创建日期
     */
    private static DateBean create(int year, int month, int dayOfMonth) {
        if (dayOfMonth > 28) {
            int dom = 31;
            switch (month) {
                case 2:
                    dom = (isLeapYear(year) ? 29 : 28);
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dom = 30;
                    break;
            }
            if (dayOfMonth > dom) {
                if (dayOfMonth == 29) {
                    throw new RuntimeException(
                            "Invalid date 'February 29' as '" + year + "' is not a leap year");
                } else {
                    throw new RuntimeException("Invalid dayOfMonth:" + dayOfMonth);
                }
            }
        }
        return new DateBean(year, month, dayOfMonth);
    }


    private static DateBean resolvePreviousValid(int year, int month, int day) {
        switch (month) {
            case 2:
                day = Math.min(day, isLeapYear(year) ? 29 : 28);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = Math.min(day, 30);
                break;
        }
        return new DateBean(year, month, day);
    }

    /**
     * 判断是否是闰年
     */
    private static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }


    private long toEpochDay() {
        long y = year;
        long m = month;
        long total = 0;
        total += 365 * y; //计算天数
        if (y >= 0) {
            total += (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400; //计算闰年数
        } else {
            total -= y / -4 - y / -100 + y / -400;
        }
        //计算月数。但是为什么是这个公式，不是很理解
        total += ((367 * m - 362) / 12);
        total += day - 1;
        if (m > 2) {
            total--;
            if (!isLeapYear((int) year)) {
                total--;
            }
        }
        return total - DAYS_0000_TO_1970;
    }


    private static DateBean ofEpochDay(long epochDay) {
        long zeroDay = epochDay + DAYS_0000_TO_1970;
        // find the march-based year
        //这里为什么要减60？
        zeroDay -= 60;  // adjust to 0000-03-01 so leap day is at end of four year cycle
        long adjust = 0;
        if (zeroDay < 0) {
            // adjust negative years to positive for calculation
            long adjustCycles = (zeroDay + 1) / DAYS_PER_CYCLE - 1;
            adjust = adjustCycles * 400;
            zeroDay += -adjustCycles * DAYS_PER_CYCLE;
        }
        long yearEst = (400 * zeroDay + 591) / DAYS_PER_CYCLE;
        long doyEst = zeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400);
        if (doyEst < 0) {
            // fix estimate
            yearEst--;
            doyEst = zeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400);
        }
        yearEst += adjust;  // reset any negative year
        int marchDoy0 = (int) doyEst;

        // convert march-based values back to january-based
        int marchMonth0 = (marchDoy0 * 5 + 2) / 153;
        int month = (marchMonth0 + 2) % 12 + 1;
        int dom = marchDoy0 - (marchMonth0 * 306 + 5) / 10 + 1;
        yearEst += marchMonth0 / 10;

        // check year now we are certain it is correct
        int year = (int) yearEst;
        return new DateBean(year, month, dom);
    }


}
