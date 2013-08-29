package segtree.impl;

import org.junit.Test;

/**
 * @author Denis Tyschenko
 *         Date: 8/27/13
 */
public class UpdateSegTreeCornerCasesTest {
    private SegmentTreeImpl<Integer> segTree;

    @Test
    public void testUpdateOnOneElementTree() {
        segTree = new SegmentTreeImpl<Integer>(new Integer[]{1});
        segTree.update(0,0);
        SegTreeTestSupport.assertNodeArraysAreEqual("Invalid update operation with position : 0, value : 0 " +
                "on one element segment tree",
                segTree.getNodesSnapshot(), new SegmentTreeImpl.Node[]{segTree.new Node(0,0,0,0)});
    }

    @Test (expected = IllegalArgumentException.class)
    public void testUpdateOnZeroElementTree() {
        segTree = new SegmentTreeImpl<Integer>(new Integer[]{});
        segTree.update(0,0);

    }
}
