package segtree.impl.rangequerytree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import segtree.impl.NodeParameters;
import segtree.impl.RangeQueryableSegmentTree;
import segtree.impl.SegmentTreeTestSupport;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
@RunWith(Parameterized.class)
public class UpdateRQTTest<T> extends SegmentTreeTestSupport<T, RangeQueryableSegmentTree<T>, T> {

    private static Object[][] inputAndExpectedValues = {
            {//seg tree will be created by this array
             RQTTestDataSet.RQT_ON_SIX_ELEMENTS[0],
             5,// update position
             1,// update value
             // expected segment tree after update operation
             new NodeParameters[]{
                     new NodeParameters<>(0, 0, 5, 1),
                     new NodeParameters<>(1, 0, 2, 1),
                     new NodeParameters<>(2, 3, 5, 1),
                     new NodeParameters<>(3, 0, 1, 1),
                     new NodeParameters<>(4, 2, 2, 3),
                     new NodeParameters<>(5, 3, 4, 2),
                     new NodeParameters<>(6, 5, 5, 1),
                     new NodeParameters<>(7, 0, 0, 1),
                     new NodeParameters<>(8, 1, 1, 5),
                     null, // 9th node
                     null, //10th node
                     new NodeParameters<>(11, 3, 3, 4),
                     new NodeParameters<>(12, 4, 4, 2),
                     null, // 13th node
                     null  // 14th node}
            }},
            {   //seg tree will be created by this array
                RQTTestDataSet.RQT_ON_SIX_ELEMENTS[0],
                // update position 0 element, with value - 1 element
                2,
                0,
                // expected segment tree after update operation
                new NodeParameters[]{
                        new NodeParameters<>(0, 0, 5, 0),
                        new NodeParameters<>(1, 0, 2, 0),
                        new NodeParameters<>(2, 3, 5, 0),
                        new NodeParameters<>(3, 0, 1, 1),
                        new NodeParameters<>(4, 2, 2, 0),
                        new NodeParameters<>(5, 3, 4, 2),
                        new NodeParameters<>(6, 5, 5, 0),
                        new NodeParameters<>(7, 0, 0, 1),
                        new NodeParameters<>(8, 1, 1, 5),
                        null, // 9th node
                        null, //10th node
                        new NodeParameters<>(11, 3, 3, 4),
                        new NodeParameters<>(12, 4, 4, 2),
                        null, // 13th node
                        null  // 14th node}
                }
            },
            {   //seg tree will be created by this array
                RQTTestDataSet.RQT_ON_SIX_ELEMENTS[0],
                // update position 0 element, with value - 1 element
                3,
                1,
                // expected segment tree after update operation
                new NodeParameters[]{
                        new NodeParameters<>(0, 0, 5, 0),
                        new NodeParameters<>(1, 0, 2, 1),
                        new NodeParameters<>(2, 3, 5, 0),
                        new NodeParameters<>(3, 0, 1, 1),
                        new NodeParameters<>(4, 2, 2, 3),
                        new NodeParameters<>(5, 3, 4, 1),
                        new NodeParameters<>(6, 5, 5, 0),
                        new NodeParameters<>(7, 0, 0, 1),
                        new NodeParameters<>(8, 1, 1, 5),
                        null, // 9th node
                        null, //10th node
                        new NodeParameters<>(11, 3, 3, 1),
                        new NodeParameters<>(12, 4, 4, 2),
                        null, // 13th node
                        null  // 14th node}
                }
            }
    };

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValueList() {
        return Arrays.asList(inputAndExpectedValues);
    }

    private int position;
    private T value;

    public UpdateRQTTest(T[] array, int position, T value, NodeParameters<T>[] segtreeNodeParams) {
        this.position = position;
        this.value = value;
        segTree = new RangeQueryableSegmentTree<>(array);
        expectedNodes = buildRQExpectedNodes(segtreeNodeParams);
    }

    @Test
    public void verifyThatUpdateOnRQTIsCorrect() {
      segTree.update(value, position);
      RangeQueryableSegmentTree.Node[] actualNodes = segTree.getNodesSnapshot();
      Assert.assertArrayEquals("Update operation of position : " + position + ", with value : " + value + " was wrong",
                expectedNodes, actualNodes);
    }


}
