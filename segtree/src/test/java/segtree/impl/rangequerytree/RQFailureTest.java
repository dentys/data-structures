package segtree.impl.rangequerytree;

import org.junit.Test;
import segtree.impl.RangeQueryableSegmentTree;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
public class RQFailureTest {
    private RangeQueryableSegmentTree<Integer> segTree;

    @Test(expected = IllegalArgumentException.class)
    public void verifyThatRangeQueryThrowsExceptionWhenQueryEndRangeIsGreaterThanIntervalEndOfRootNode() {
        segTree = new RangeQueryableSegmentTree<>(new Integer[]{1,2});
        segTree.rangeQuery(0,100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyThatRangeQueryThrowsExceptionWhenQueryStartRangeIsLessThanIntervalStartOfRootNode() {
        segTree = new RangeQueryableSegmentTree<>(new Integer[]{1,2});
        segTree.rangeQuery(-100,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyThatRangeQueryThrowsExceptionWhenStartQueryRangeIsGreaterThanEndQueryRange() {
        segTree = new RangeQueryableSegmentTree<>(new Integer[]{1,9});
        segTree.rangeQuery(3,1);
    }

}
