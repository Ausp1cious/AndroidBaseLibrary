package wang.auspicous.ausp1ciouslib.utils.timeutils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ausp1cious on 2019/3/28.
 */
public class DateBeanTest {
    @Test
    public void testAddDays() {
        DateBean date2019328 = DateBean.of(2019, 3, 28);
        Assert.assertEquals("2019-3-28", date2019328.getDate());
        DateBean data2019331 = date2019328.plusDays(3);
        Assert.assertEquals("2019-3-31", data2019331.getDate());
        DateBean date201941 = data2019331.plusDays(1);
        Assert.assertEquals("2019-4-1", date201941.getDate());

        DateBean date2019228 = DateBean.of(2019, 2, 28);
        DateBean date201931 = date2019228.plusDays(1);
        Assert.assertEquals("2019-3-1", date201931.getDate());

        DateBean date2016228 = DateBean.of(2016, 2, 28);
        DateBean date2016229 = date2016228.plusDays(1);
        Assert.assertEquals("2016-2-29", date2016229.getDate());
        DateBean date201631 = date2016229.plusDays(1);
        Assert.assertEquals("2016-3-1", date201631.getDate());


    }
    @Test
    public void testAddWeeks() {
        // TODO: 2019/3/28 testAddWeeks
    }
    @Test
    public void testAddMonths() {
        // TODO: 2019/3/28 testAddMonths
    }
    @Test
    public void testAddYears() {
        // TODO: 2019/3/28 testAddYears
    }


    @Test
    public void testMinusDays() {
        // TODO: 2019/3/28 testMinusDays
    }

    @Test
    public void testMinusWeeks() {
        // TODO: 2019/3/28 testMinusWeeks
    }

    @Test
    public void testMinusMonths() {
        // TODO: 2019/3/28 testMinusMonths
    }

    @Test
    public void testMinusYears() {
        // TODO: 2019/3/28 testMinusYears
    }

}