package segtree.impl.stabquerytree;

import org.junit.Before;
import org.junit.runners.Parameterized;
import segtree.impl.util.Interval;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Denis Tyschenko
 *         Date: 9/24/13
 */
public class SQOnOneIntervalTreeTest extends SQTestSupport{

    public SQOnOneIntervalTreeTest(int point, Set<Interval> expectedSQ) {
        super(point, expectedSQ);
        endpoints = (Integer[])SQTTestDataSet.SQT_ON_ONE_INTERVAL[0];
    }

    @Before
    public void insertInterval() {
        segTree.insert(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValueList() {
        return Arrays.asList(new Object[][] {
                {5, new HashSet<Interval>() {{add(new Interval(Integer.MIN_VALUE, Integer.MAX_VALUE));}}},
                {Integer.MIN_VALUE, new HashSet<Interval>() {{add(new Interval(Integer.MIN_VALUE, Integer.MAX_VALUE));}}},
                {Integer.MAX_VALUE, new HashSet<Interval>() {{add(new Interval(Integer.MIN_VALUE, Integer.MAX_VALUE));}}}
        });
    }
}
