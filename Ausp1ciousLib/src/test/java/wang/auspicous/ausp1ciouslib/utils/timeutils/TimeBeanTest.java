package wang.auspicous.ausp1ciouslib.utils.timeutils;

import org.junit.Test;

import java.util.Locale;

/**
 * Created by Ausp1cious on 2019/3/29.
 */
public class TimeBeanTest {

    @Test
    public void test() {
        TimeBean timeBean = TimeBean.of(Locale.getDefault(), System.currentTimeMillis());
        System.out.print(timeBean.getTime());
    }
}