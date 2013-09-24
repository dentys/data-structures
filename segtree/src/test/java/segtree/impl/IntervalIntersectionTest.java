package segtree.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import segtree.impl.util.Interval;
import static segtree.impl.util.Interval.EXCLUSIVE;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Denis Tyschenko
 *         Date: 9/16/13
 */
@RunWith(Parameterized.class)
public class IntervalIntersectionTest {

    private final static boolean IS_INTERSECTS = true;

    private static Object[][] inputAndExpectedValues = {
            {new Interval(5,10), new Interval(1,5), IS_INTERSECTS},
            {new Interval(5,10), new Interval(10,100), IS_INTERSECTS},
            {new Interval(5, EXCLUSIVE, 10), new Interval(1,5), !IS_INTERSECTS},
            {new Interval(5, 10, EXCLUSIVE), new Interval(10, 100), !IS_INTERSECTS}
    };

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValuesList() {
        return Arrays.asList(inputAndExpectedValues);
    }

    private Interval intervalA, intervalB;
    private boolean expectedIntersects;

    public IntervalIntersectionTest(Interval intervalA, Interval intervalB, boolean expectedIntersects) {
        this.intervalA = intervalA;
        this.intervalB = intervalB;
        this.expectedIntersects = expectedIntersects;
    }

    @Test
    public void verifyThatIntersectionIsCorrect() {
       Assert.assertEquals("Interval: " + intervalA + " expected to intersect interval: " + intervalB,
               expectedIntersects, intervalA.intersects(intervalB));
    }
}
