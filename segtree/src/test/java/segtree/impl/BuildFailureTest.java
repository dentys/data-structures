package segtree.impl;

import org.junit.Test;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
public class BuildFailureTest {

    @Test(expected = IllegalArgumentException.class)
    public void verifyThatRQTBuildThrowExceptionWhenInputIsZeroLengthArray() {
        new RangeQueryableSegmentTree<>(new Object[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyThatRQTBuildThrowExceptionWhenInputIsNull() {
        new RangeQueryableSegmentTree<>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyThatSQTTBuildThrowExceptionWhenInputIsZeroLengthArray() {
        new StabQueryableSegmentTree(new Integer[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyThatSQTTBuildThrowExceptionWhenInputIsNull() {
        new StabQueryableSegmentTree(null);
    }
}
