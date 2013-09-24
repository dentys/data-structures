package segtree.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import segtree.impl.util.Interval;

import java.util.Arrays;
import java.util.Collection;

import static segtree.impl.util.Interval.EXCLUSIVE;

/**
 * @author Denis Tyschenko
 *         Date: 9/10/13
 */
@RunWith(Parameterized.class)
public class IntervalContainsAndIntersectionTest {
    private final static boolean IS_CONTAINS = true;
    private static Object[][] inputAndExpectedValues = {
            // [5,10]
            {new Interval(5,10), new Interval(5,10), IS_CONTAINS},
            {new Interval(5,10), new Interval(5, EXCLUSIVE, 10), IS_CONTAINS},
            {new Interval(5,10), new Interval(5, 10, EXCLUSIVE), IS_CONTAINS},
            {new Interval(5,10), new Interval(5, EXCLUSIVE, 10, EXCLUSIVE), IS_CONTAINS},
            // (5,10]
            {new Interval(5, EXCLUSIVE, 10), new Interval(5,10), !IS_CONTAINS},
            {new Interval(5, EXCLUSIVE, 10), new Interval(5, EXCLUSIVE, 10), IS_CONTAINS},
            {new Interval(5, EXCLUSIVE, 10), new Interval(5, 10, EXCLUSIVE),!IS_CONTAINS},
            {new Interval(5, EXCLUSIVE, 10), new Interval(5, EXCLUSIVE, 10, EXCLUSIVE),IS_CONTAINS},
            // [5,10)
            {new Interval(5, 10, EXCLUSIVE), new Interval(5,10), !IS_CONTAINS},
            {new Interval(5, 10, EXCLUSIVE), new Interval(5, EXCLUSIVE, 10), !IS_CONTAINS},
            {new Interval(5, 10, EXCLUSIVE), new Interval(5, 10, EXCLUSIVE), IS_CONTAINS},
            {new Interval(5, 10, EXCLUSIVE), new Interval(5, EXCLUSIVE, 10, EXCLUSIVE), IS_CONTAINS},
            // (5,10)
            {new Interval(5, EXCLUSIVE, 10, EXCLUSIVE), new Interval(5,10), !IS_CONTAINS},
            {new Interval(5, EXCLUSIVE, 10, EXCLUSIVE), new Interval(5, EXCLUSIVE, 10), !IS_CONTAINS},
            {new Interval(5, EXCLUSIVE, 10, EXCLUSIVE), new Interval(5, 10, EXCLUSIVE), !IS_CONTAINS},
            {new Interval(5, EXCLUSIVE, 10, EXCLUSIVE), new Interval(5, EXCLUSIVE, 10, EXCLUSIVE), IS_CONTAINS},
            // Some random cases
            {new Interval(5, 10), new Interval(5, 9), IS_CONTAINS},
            {new Interval(Integer.MIN_VALUE, 10, EXCLUSIVE), new Interval(Integer.MIN_VALUE, 9, EXCLUSIVE), IS_CONTAINS}
    };

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValuesList() {
        return Arrays.asList(inputAndExpectedValues);
    }

    private Interval containerInterval, containedInterval;
    private boolean expectedContains;

    public IntervalContainsAndIntersectionTest(Interval containerInterval, Interval containedInterval, boolean expectedContains) {
        this.containerInterval = containerInterval;
        this.containedInterval = containedInterval;
        this.expectedContains = expectedContains;
    }

    @Test
    public void verifyThatContainsIsCorrect() {
        Assert.assertEquals("Interval: "+ containedInterval +" expected to be within interval: " + containerInterval,
                expectedContains, containerInterval.contains(containedInterval));
    }

    @Test
    public void verifyThatIntersectsBehavesCorrectlyOnExplicitAndImplicitEndpoints() {
        boolean expectedIntersects = true;
        Assert.assertEquals("Interval: "+ containerInterval +" expected to intersect interval: " + containedInterval,
                expectedIntersects, containerInterval.intersects(containedInterval));
    }
}
