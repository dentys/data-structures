package segtree.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
@RunWith(Parameterized.class)
public class UpdateSegTreeTest<T> extends SegTreeTestSupport<T> {

    private static Object[][] inputAndExpectedValues = {
            {//seg tree will be created by this array
             new Integer[]{1,5,3,4,2,0},
             5,// update position
             1,// update value
             // expected segment tree after update operation
             new NodeParameters[]{
                     new NodeParameters<Integer>(0, 0, 5, 1),
                     new NodeParameters<Integer>(1, 0, 2, 1),
                     new NodeParameters<Integer>(2, 3, 5, 1),
                     new NodeParameters<Integer>(3, 0, 1, 1),
                     new NodeParameters<Integer>(4, 2, 2, 3),
                     new NodeParameters<Integer>(5, 3, 4, 2),
                     new NodeParameters<Integer>(6, 5, 5, 1),
                     new NodeParameters<Integer>(7, 0, 0, 1),
                     new NodeParameters<Integer>(8, 1, 1, 5),
                     null, // 9th node
                     null, //10th node
                     new NodeParameters<Integer>(11, 3, 3, 4),
                     new NodeParameters<Integer>(12, 4, 4, 2),
                     null, // 13th node
                     null  // 14th node}
            }},
            {   //seg tree will be created by this array
                new Integer[]{1,5,3,4,2,0},
                // update position 0 element, with value - 1 element
                2,
                0,
                // expected segment tree after update operation
                new NodeParameters[]{
                        new NodeParameters<Integer>(0, 0, 5, 0),
                        new NodeParameters<Integer>(1, 0, 2, 0),
                        new NodeParameters<Integer>(2, 3, 5, 0),
                        new NodeParameters<Integer>(3, 0, 1, 1),
                        new NodeParameters<Integer>(4, 2, 2, 0),
                        new NodeParameters<Integer>(5, 3, 4, 2),
                        new NodeParameters<Integer>(6, 5, 5, 0),
                        new NodeParameters<Integer>(7, 0, 0, 1),
                        new NodeParameters<Integer>(8, 1, 1, 5),
                        null, // 9th node
                        null, //10th node
                        new NodeParameters<Integer>(11, 3, 3, 4),
                        new NodeParameters<Integer>(12, 4, 4, 2),
                        null, // 13th node
                        null  // 14th node}
                }
            },
            {   //seg tree will be created by this array
                new Integer[]{1,5,3,4,2,0},
                // update position 0 element, with value - 1 element
                3,
                1,
                // expected segment tree after update operation
                new NodeParameters[]{
                        new NodeParameters<Integer>(0, 0, 5, 0),
                        new NodeParameters<Integer>(1, 0, 2, 1),
                        new NodeParameters<Integer>(2, 3, 5, 0),
                        new NodeParameters<Integer>(3, 0, 1, 1),
                        new NodeParameters<Integer>(4, 2, 2, 3),
                        new NodeParameters<Integer>(5, 3, 4, 1),
                        new NodeParameters<Integer>(6, 5, 5, 0),
                        new NodeParameters<Integer>(7, 0, 0, 1),
                        new NodeParameters<Integer>(8, 1, 1, 5),
                        null, // 9th node
                        null, //10th node
                        new NodeParameters<Integer>(11, 3, 3, 1),
                        new NodeParameters<Integer>(12, 4, 4, 2),
                        null, // 13th node
                        null  // 14th node}
                }
            }
    };

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValueList() {
        return Arrays.asList(inputAndExpectedValues);
    }
//            Segment tree:
//            {0, 0, 5, 0},
//            {1, 0, 2, 1},
//            {2, 3, 5, 0},
//            {3, 0, 1, 1},
//            {4, 2, 2, 3},
//            {5, 3, 4, 2},
//            {6, 5, 5, 0},
//            {7, 0, 0, 1},
//            {8, 1, 1, 5},
//            null, // 9th node
//            null, //10th node
//            {11, 3, 3, 4},
//            {12, 4, 4, 2},
//            null, // 13th node
//            null  // 14th node}
    private int position;
    private T value;
    public UpdateSegTreeTest(T[] array, int position, T value, NodeParameters<T>[] segtreeNodeParams) {
        this.position = position;
        this.value = value;
        segTree = new SegmentTreeImpl<T>(array);
        expectedNodes = buildExpectedNodes(segtreeNodeParams);
    }

    @Test
    public void test() {
      segTree.update(value, position);
      SegmentTreeImpl.Node[] actualNodes = segTree.getNodesSnapshot();
      assertNodeArraysAreEqual("Update operation of position : "+position+", with value : "+value+" was wrong", actualNodes, expectedNodes);
    }


}
