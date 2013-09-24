package segtree.impl.stabquerytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import segtree.impl.StabQueryableSegmentTree;
import segtree.impl.util.Interval;

import java.util.Set;

/**
 * @author Denis Tyschenko
 *         Date: 9/24/13
 */
public class SQCornerCasesTest {

    private StabQueryableSegmentTree segtree;

    @Before
    public void setUp() {
        segtree = new StabQueryableSegmentTree(new Integer[]{0, 50, 100});
    }

    @Test
    public void verifyThatQueryReturnsEmptySetWhenThereIsNoAnyIntervalInTree() {
        Set<Interval> intervals = segtree.stabbingQuery(50);
        Assert.assertTrue("There are no intervals in a tree", intervals.isEmpty());
    }

    @Test
    public void verifyThatQueryReturnsEmptySetWhenThereIsNoCorrespondingIntervalInTree() {
        segtree.insert(0,50);
        Set<Interval> intervals = segtree.stabbingQuery(100);
        Assert.assertTrue("There is no such interval that contains requested point", intervals.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyThatQueryThrowsExceptionWhenRequestedPointIsOutOfRange() {
        segtree.stabbingQuery(500);
    }
}
