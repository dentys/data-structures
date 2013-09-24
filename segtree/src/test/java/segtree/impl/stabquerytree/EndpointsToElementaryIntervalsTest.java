package segtree.impl.stabquerytree;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import segtree.impl.util.Interval;
import segtree.impl.util.SegmentTreeUtils;

import java.util.Arrays;
import java.util.Collection;
import static segtree.impl.util.Interval.EXCLUSIVE;

/**
 * @author Denis Tyschenko
 *         Date: 9/18/13
 */
@RunWith(Parameterized.class)
public class EndpointsToElementaryIntervalsTest {

    public static Object[][] inputAndExpectedValues = {
            { new Integer[]{1, 2}, new Interval[]{new Interval(1,2)}},
            { new Integer[]{0, 50, 100}, new Interval[]{new Interval(0, 50, EXCLUSIVE),
                    new Interval(50, 50), new Interval(50, EXCLUSIVE, 100) }},
            { new Integer[]{0, 20, 50, 100}, new Interval[]{new Interval(0, 20, EXCLUSIVE),
                    new Interval(20, 20), new Interval(20, EXCLUSIVE, 50, EXCLUSIVE),
                    new Interval(50, 50), new Interval(50, EXCLUSIVE, 100) }},
            { new Integer[]{0, 20, 50, 70, 100}, new Interval[]{new Interval(0, 20, EXCLUSIVE),
                    new Interval(20, 20), new Interval(20, EXCLUSIVE, 50, EXCLUSIVE),
                    new Interval(50, 50), new Interval(50, EXCLUSIVE, 70, EXCLUSIVE),
                    new Interval(70, 70), new Interval(70, EXCLUSIVE, 100) }},

    };

    @Parameterized.Parameters
    public static Collection<Object[]> inputValues() {
        return Arrays.asList(inputAndExpectedValues);
    }

    private Integer[] endpoints;
    private Interval[] expectedIntervals;

    public EndpointsToElementaryIntervalsTest(Integer[] endpoints, Interval[] expectedIntervals) {
        this.endpoints = endpoints;
        this.expectedIntervals = expectedIntervals;
    }

    @Test
    public void verifyThatConversionIsCorrect() {
        Interval[] actualIntervals = SegmentTreeUtils.toElementaryIntervals(endpoints);
        Assert.assertArrayEquals("Conversion from endpoints to elementary intervals is not correct",
                expectedIntervals, actualIntervals);

    }
}
