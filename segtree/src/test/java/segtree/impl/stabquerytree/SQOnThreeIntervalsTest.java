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
public class SQOnThreeIntervalsTest extends SQTestSupport {

    public SQOnThreeIntervalsTest(int point, Set<Interval> expectedSQ) {
        super(point, expectedSQ);
        endpoints = (Integer[])SQTTestDataSet.SQT_ON_TREE_INTERVALS[0];
    }

    @Before
    public void insertInterval() {
        segTree.insert(3,3);
        segTree.insert(Integer.MIN_VALUE,3);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValueList() {
        return Arrays.asList(new Object[][]{
                {0, new HashSet<Interval>() {{add(new Interval(Integer.MIN_VALUE, 3));}}},
                {3, new HashSet<Interval>() {{add(new Interval(Integer.MIN_VALUE, 3));
                    add(new Interval(3, 3));
                }}},
                {Integer.MIN_VALUE, new HashSet<Interval>() {{add(new Interval(Integer.MIN_VALUE, 3));}}},
                {4, new HashSet<Interval>()},
                {5, new HashSet<Interval>()}
        });
    }
}
