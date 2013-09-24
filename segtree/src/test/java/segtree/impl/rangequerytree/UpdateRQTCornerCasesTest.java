package segtree.impl.rangequerytree;

import org.junit.Assert;
import org.junit.Test;
import segtree.impl.RangeQueryableSegmentTree;
import segtree.impl.util.Interval;

/**
 * @author Denis Tyschenko
 *         Date: 8/27/13
 */
public class UpdateRQTCornerCasesTest {
    private RangeQueryableSegmentTree<Integer> segTree;

    @Test
    public void verifyThatUpdateOnOneElementTreeIsCorrect() {
        segTree = new RangeQueryableSegmentTree<>(new Integer[]{1});
        segTree.update(0,0);
        Assert.assertArrayEquals("Invalid update operation with position : 0, value : 0 on one element segment tree",
                new RangeQueryableSegmentTree.Node[]{segTree.new Node<Integer>(0, true, new Interval(0, 0), 0)},
                segTree.getNodesSnapshot());
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyThatUpdateOnZeroElementTreeThrowsException() {
        segTree = new RangeQueryableSegmentTree<>(new Integer[]{});
        segTree.update(0,0);

    }
}
