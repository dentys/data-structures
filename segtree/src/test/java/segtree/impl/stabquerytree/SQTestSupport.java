package segtree.impl.stabquerytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import segtree.impl.StabQueryableSegmentTree;
import segtree.impl.util.Interval;

import java.util.Set;

/**
 * Parametrized test support class to check stabbing queries.
 * Descendants must specify input end expected test data.
 *
 * @author Denis Tyschenko
 *         Date: 9/16/13
 */
@RunWith(Parameterized.class)
public abstract class SQTestSupport {
    protected StabQueryableSegmentTree segTree;
    protected Integer[] endpoints;
    private final int point;
    private final Set<Interval> expectedSQ;

    @Before
    public void buildTree() {
        this.segTree = new StabQueryableSegmentTree(endpoints);
    }

    public SQTestSupport(int point, Set<Interval> expectedSQ) {
        this.point = point;
        this.expectedSQ = expectedSQ;
    }

    @Test
    public void verifyThatStabbingQueryIsCorrect() {
        Assert.assertEquals("Expected Stabbing Query result does not match actual",
                expectedSQ, segTree.stabbingQuery(point));
    }
}
