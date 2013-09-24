package segtree.impl.rangequerytree;

import segtree.impl.NodeParameters;

/**
 * @author Denis Tyschenko
 *         Date: 9/24/13
 */
public class RQTTestDataSet {
    public final static Object[] RQT_ON_ONE_ELEMENT =
    {    //Input array to build a segment tree
        //Element type must be equal to value type that is used in NodeParameters below
        new Integer[]{1},
        // Need to use "node parameters" - can't instantiate RangeQueryableSegmentTree.Node directly from static context
        // RangeQueryableSegmentTree.Node will be created by "node parameters" during construction of this class object
        // Expected node parameters
        //0 - node index
        //1 - interval start
        //2 - interval end
        //3 - value (type of the value must be equal to the generic type)
        new NodeParameters[]{new NodeParameters<>(0, 0, 0, 1)}
    };
    public final static Object[] RQT_ON_TWO_ELEMENTS =
    {
        new Integer[]{1,2},
        new NodeParameters[]{
                new NodeParameters<>(0, 0, 1, 1),
                new NodeParameters<>(1, 0, 0, 1),
                new NodeParameters<>(2, 1, 1, 2),
        }
    };

    public final static Object[] RQT_ON_SIX_ELEMENTS =
    {
        new Integer[]{1,5,3,4,2,0},
        new NodeParameters[]{
                new NodeParameters<>(0, 0, 5, 0),
                new NodeParameters<>(1, 0, 2, 1),
                new NodeParameters<>(2, 3, 5, 0),
                new NodeParameters<>(3, 0, 1, 1),
                new NodeParameters<>(4, 2, 2, 3),
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
    };

    public final static Object[] RQT_ON_TWO_STRING_TYPE_ELEMENTS =
    {
        new String[]{"set", "list"},
        new NodeParameters[]{
                new NodeParameters<>(0, 0, 1, "list"),
                new NodeParameters<>(1, 0, 0, "set"),
                new NodeParameters<>(2, 1, 1, "list")
        }
    };
}
