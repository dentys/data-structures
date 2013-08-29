package segtree.impl;

import org.junit.Test;
import segtree.impl.SegmentTreeImpl;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
public class SegTreeBuildFailureTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSegTreeBuild_zeroLengthArray() {
        new SegmentTreeImpl<Object>(new Object[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSegTreeBuild_nullArray() {
        new SegmentTreeImpl<Object>(null);
    }
}
