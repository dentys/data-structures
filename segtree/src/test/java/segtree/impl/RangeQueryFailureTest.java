package segtree.impl;

import org.junit.Test;
import segtree.impl.SegmentTreeImpl;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
public class RangeQueryFailureTest {
    private SegmentTreeImpl<Integer> segTree;

    @Test(expected = IllegalArgumentException.class)
    public void testRangeQuery_endRangeGreaterThanEntireSegTreeInterval() {
        segTree = new SegmentTreeImpl<Integer>(new Integer[]{1,2});
        segTree.rangeQuery(0,100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeQuery_startRangeLessThanStartOfSegTreeInterval() {
        segTree = new SegmentTreeImpl<Integer>(new Integer[]{1,2});
        segTree.rangeQuery(-100,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeQuery_startRangeBiggerThanEndRange() {
        segTree = new SegmentTreeImpl<Integer>(new Integer[]{1,2});
        segTree.rangeQuery(3,1);
    }

}
