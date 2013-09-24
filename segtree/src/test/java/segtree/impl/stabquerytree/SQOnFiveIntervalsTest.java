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
public class SQOnFiveIntervalsTest extends SQTestSupport {

    public SQOnFiveIntervalsTest(int point, Set<Interval> expectedSQ) {
        super(point, expectedSQ);
        endpoints = (Integer[])SQTTestDataSet.SQT_ON_FIVE_INTERVALS[0];
    }

    @Before
    public void insertInterval() {
        segTree.insert(Integer.MIN_VALUE,3);
        segTree.insert(3,3);
        segTree.insert(3,5);
        segTree.insert(5, Integer.MAX_VALUE);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValueList() {
        return Arrays.asList(new Object[][]{
                {0, new HashSet<Interval>() {{
                    add(new Interval(Integer.MIN_VALUE, 3));
                }}},
                {3, new HashSet<Interval>() {{
                    add(new Interval(Integer.MIN_VALUE, 3));
                    add(new Interval(3, 3));
                    add(new Interval(3, 5));
                }}},
                {Integer.MIN_VALUE, new HashSet<Interval>() {{
                    add(new Interval(Integer.MIN_VALUE, 3));
                }}},
                {4, new HashSet<Interval>() {{
                    add(new Interval(3, 5));
                }}},
                {5, new HashSet<Interval>() {{
                    add(new Interval(3, 5));
                    add(new Interval(5, Integer.MAX_VALUE));
                }}},
                {Integer.MAX_VALUE, new HashSet<Interval>() {{
                    add(new Interval(5, Integer.MAX_VALUE));
                }}}
        });
    }

}
