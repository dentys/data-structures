package segtree.impl.rangequerytree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import segtree.impl.NodeParameters;
import segtree.impl.RangeQueryableSegmentTree;
import segtree.impl.SegmentTreeTestSupport;

import java.util.Arrays;
import java.util.Collection;

import static segtree.impl.rangequerytree.RQTTestDataSet.*;

/**
 *
 * @author Denis Tyschenko
 *         Date: 8/22/13
 */
@RunWith(value = Parameterized.class)
public class BuildRQTTest<T> extends SegmentTreeTestSupport<T, RangeQueryableSegmentTree<T>, T> {

    @Parameters
    public static Collection<Object[]> inputAndExpectedValueList() {
        return Arrays.asList(new Object[][] {
                {RQT_ON_ONE_ELEMENT[0],
                 RQT_ON_ONE_ELEMENT[1]},
                {RQT_ON_TWO_ELEMENTS[0],
                 RQT_ON_TWO_ELEMENTS[1]},
                {RQT_ON_SIX_ELEMENTS[0],
                 RQT_ON_SIX_ELEMENTS[1]},
                {RQT_ON_TWO_STRING_TYPE_ELEMENTS[0],
                 RQT_ON_TWO_STRING_TYPE_ELEMENTS[1]}});
        }

    @SuppressWarnings("unchecked")
    public BuildRQTTest(T[] originalArray, NodeParameters[] expectedNodeParameters) {
        segTree = new RangeQueryableSegmentTree<>(originalArray);
        expectedNodes = buildRQExpectedNodes(expectedNodeParameters);
    }

    @Test
    public void verifyThatSegmentTreeBuildIsCorrect() {
        RangeQueryableSegmentTree.Node[] actualNodes = segTree.getNodesSnapshot();
        Assert.assertArrayEquals(expectedNodes, actualNodes);
    }

}
