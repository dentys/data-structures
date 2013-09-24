package segtree.impl.stabquerytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import segtree.impl.AbstractSegmentTree;
import segtree.impl.StabQueryableSegmentTree;

/**
 * @author Denis Tyschenko
 *         Date: 9/24/13
 */
public class InsertAndRemoveIntoSQTCornerCasesTest {
    private StabQueryableSegmentTree segtree;
    private AbstractSegmentTree.Node[] beforeFailedAction;

    @Before
    public void setUp() {
        segtree = new StabQueryableSegmentTree(new Integer[]{0, 50, 100});
        beforeFailedAction = segtree.getNodesSnapshot();
    }

    @Test
    public void verifyThatInsertThrowsExceptionAndDoNotChangeTreeStateWhenInputIsNotCorrect() {
        try{
            segtree.insert(20,50);
            Assert.fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            AbstractSegmentTree.Node[] afterIllegalInsertion = segtree.getNodesSnapshot();
            Assert.assertArrayEquals("Wrong insert operation must not change the state of a tree",
                    beforeFailedAction, afterIllegalInsertion);
        }
    }

    @Test
    public void verifyThatRemoveThrowsExceptionAndDoNotChangeTreeStateWhenInputIsNotCorrect() {
        try{
            segtree.remove(20, 50);
            Assert.fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            AbstractSegmentTree.Node[] afterIllegalRemoval = segtree.getNodesSnapshot();
            Assert.assertArrayEquals("Wrong remove operation must not change the state of a tree",
                    beforeFailedAction, afterIllegalRemoval);
        }
    }

    @Test
    public void verifyThatInsertReturnsFalseAndDoNotChangeTreeStateWhenAttemptToInsertDuplicateInterval() {
        Assert.assertTrue(segtree.insert(50,100));
        beforeFailedAction = segtree.getNodesSnapshot();
        Assert.assertFalse(segtree.insert(50,100));
        AbstractSegmentTree.Node[] afterFailedInsertion = segtree.getNodesSnapshot();
        Assert.assertArrayEquals("Failed insert operation must not change the state of a tree",
                beforeFailedAction, afterFailedInsertion);
    }

    @Test
    public void verifyThatRemoveReturnsFalseAndDoNotChangeTreeStateWhenAttemptToRemoveNonexistentInterval() {
        segtree.insert(50,100);
        Assert.assertTrue(segtree.remove(50,100));
        beforeFailedAction = segtree.getNodesSnapshot();
        Assert.assertFalse(segtree.remove(50,100));
        AbstractSegmentTree.Node[] afterFailedRemoval = segtree.getNodesSnapshot();
        Assert.assertArrayEquals("Failed remove operation must not change the state of a tree",
                beforeFailedAction, afterFailedRemoval);
    }
}
