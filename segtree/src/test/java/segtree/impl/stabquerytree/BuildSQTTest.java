package segtree.impl.stabquerytree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import segtree.impl.NodeParameters;
import segtree.impl.SegmentTreeTestSupport;
import segtree.impl.StabQueryableSegmentTree;
import segtree.impl.util.Interval;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static segtree.impl.stabquerytree.SQTTestDataSet.*;
/**
 * @author Denis Tyschenko
 *         Date: 9/4/13
 */
@RunWith(Parameterized.class)
public class BuildSQTTest extends SegmentTreeTestSupport<Integer, StabQueryableSegmentTree, Set<Interval>> {

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValueList() {
        return Arrays.asList(new Object[][] {
                {SQT_ON_ONE_INTERVAL[0],
                 SQT_ON_ONE_INTERVAL[1]},
                {SQT_ON_TREE_INTERVALS[0],
                 SQT_ON_TREE_INTERVALS[1]},
                {SQT_ON_FIVE_INTERVALS[0],
                 SQT_ON_FIVE_INTERVALS[1]},
                {SQT_ON_SEVEN_INTERVALS[0],
                 SQT_ON_SEVEN_INTERVALS[1]}
        });
    }

    public BuildSQTTest(Integer[] endpoints, NodeParameters<Set<Interval>>[] nodeParameters) {
        segTree = new StabQueryableSegmentTree(endpoints);
        expectedNodes = super.buildSQExpectedNodes(nodeParameters);
    }

    @Test
    public void verifyThatSQTreeBuildIsCorrect() {
        Assert.assertArrayEquals(segTree.getNodesSnapshot(), expectedNodes);
    }
}