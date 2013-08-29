package segtree.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Denis Tyschenko
 *         Date: 8/22/13
 */
@RunWith(value = Parameterized.class)
public class BuildSegTreeTest<T> extends SegTreeTestSupport<T>{

    private static Object[][] inputAndExpectedValues = {
            {       //Input array to build a segment tree
                    //Element type must be equal to value type that is used in NodeParameters below
                    new Integer[]{1},
                    // Need to use "node parameters" - can't instantiate SegmentTreeImpl.Node directly from static context
                    // SegmentTreeImpl.Node will be created by this "node parameters" during construction of this class object
                    // Expected node parameters
                    //0 - node index
                    //1 - interval start
                    //2 - interval end
                    //3 - value (type of the value must be equal to the generic type)
                    new NodeParameters[]{new NodeParameters<Integer>(0, 0, 0, 1)}
            },
            {
                    new Integer[]{1,2},
                    new NodeParameters[]{
                        new NodeParameters<Integer>(0, 0, 1, 1),
                        new NodeParameters<Integer>(1, 0, 0, 1),
                        new NodeParameters<Integer>(2, 1, 1, 2),
                    }
            },

            {
                new Integer[]{1,5,3,4,2,0},
                new NodeParameters[]{
                        new NodeParameters<Integer>(0, 0, 5, 0),
                        new NodeParameters<Integer>(1, 0, 2, 1),
                        new NodeParameters<Integer>(2, 3, 5, 0),
                        new NodeParameters<Integer>(3, 0, 1, 1),
                        new NodeParameters<Integer>(4, 2, 2, 3),
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
            {
                    new String[]{"set", "list"},
                    new NodeParameters[]{
                            new NodeParameters<String>(0, 0, 1, "list"),
                            new NodeParameters<String>(1, 0, 0, "set"),
                            new NodeParameters<String>(2, 1, 1, "list")
                    }
            }

    };

    @Parameters
    public static Collection<Object[]> inputAndexpectedValueList() {
        return Arrays.asList(inputAndExpectedValues);
    }

    public BuildSegTreeTest(T[] originalArray, NodeParameters[] expectedNodeParameters) {
        segTree = new SegmentTreeImpl<T>(originalArray);
        expectedNodes = buildExpectedNodes(expectedNodeParameters);
    }

    @Test
    public void checkSegTree_isCorrect() {
        SegmentTreeImpl.Node[] actualNodes = segTree.getNodesSnapshot();
        assertNodeArraysAreEqual(actualNodes, expectedNodes);
    }

}
