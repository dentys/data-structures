package segtree.impl.rangequerytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import segtree.impl.RangeQueryableSegmentTree;

/**
 * Parametrized test support class to check range minimum queries.
 * Descendants must specify input end expected test data.
 *
 * @author Denis Tyschenko
 *         Date: 8/25/13
 */
@RunWith(Parameterized.class)
public abstract class RMQTestSupport<T> {

    protected RangeQueryableSegmentTree<T> segTree;
    protected T[] inputArray;
    private int startRange;
    private int endRange;
    T expectedRmq;

    @Before
    public void setUp() {
        this.segTree = new RangeQueryableSegmentTree<>(inputArray);
    }

    public RMQTestSupport(int startRange, int endRange, T expectedRMQ) {
        this.startRange = startRange;
        this.endRange = endRange;
        this.expectedRmq = expectedRMQ;
    }

    @Test
    public void verifyThatRangeMinimumQueryIsCorrect() {
        Assert.assertEquals("Expected RMQ result does not match actual",
                expectedRmq, segTree.rangeQuery(startRange, endRange));
    }

}
