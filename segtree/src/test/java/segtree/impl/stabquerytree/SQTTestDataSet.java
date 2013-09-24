package segtree.impl.stabquerytree;

import segtree.impl.NodeParameters;
import segtree.impl.util.Interval;

import java.util.Set;

import static segtree.impl.util.Interval.EXCLUSIVE;

/**
 * @author Denis Tyschenko
 *         Date: 9/23/13
 */
public class SQTTestDataSet {
    public static Object[] SQT_ON_ONE_INTERVAL =  {
            //Input Data
            new Integer[]{Integer.MIN_VALUE, Integer.MAX_VALUE},
            // Constructed tree
            new NodeParameters[]{
                    new NodeParameters(0, true, new Interval(Integer.MIN_VALUE, Integer.MAX_VALUE))
            }
    };

    public static Object[] SQT_ON_TREE_INTERVALS =  {
            //Input Data
            new Integer[]{ Integer.MIN_VALUE, 3,  5 },
            // Constructed tree
            new NodeParameters[]{
                    new NodeParameters(0, new Interval(Integer.MIN_VALUE, 5)),
                    new NodeParameters(1, new Interval(Integer.MIN_VALUE, 3)),
                    new NodeParameters(2, true, new Interval(3, EXCLUSIVE, 5)),
                    new NodeParameters(3, true, new Interval(Integer.MIN_VALUE, 3, EXCLUSIVE)),
                    new NodeParameters(4, true, new Interval(3, 3)),
                    null,
                    null
            }
    };

    public static Object[] SQT_ON_FIVE_INTERVALS =  {
            //Input Data
            new Integer[]{ Integer.MIN_VALUE, 3,  5, Integer.MAX_VALUE },
            // Constructed tree
            new NodeParameters[]{
                    new NodeParameters(0, new Interval(Integer.MIN_VALUE, Integer.MAX_VALUE)),
                    new NodeParameters(1, new Interval(Integer.MIN_VALUE, 5, EXCLUSIVE)),
                    new NodeParameters(2, new Interval(5, Integer.MAX_VALUE)),
                    new NodeParameters(3, new Interval(Integer.MIN_VALUE, 3)),
                    new NodeParameters(4, true, new Interval(3, EXCLUSIVE, 5, EXCLUSIVE)),
                    new NodeParameters(5, true, new Interval(5, 5)),
                    new NodeParameters(6, true, new Interval(5, EXCLUSIVE, Integer.MAX_VALUE)),
                    new NodeParameters(7, true, new Interval(Integer.MIN_VALUE, 3, EXCLUSIVE)),
                    new NodeParameters(8, true, new Interval(3,3)),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            }
    };


    public static Object[] SQT_ON_SEVEN_INTERVALS =  {
            //Input Data
            new Integer[]{ Integer.MIN_VALUE, 3, 4, 5, Integer.MAX_VALUE },
            // Need to use "node parameters" - can't instantiate AbstractSegmentTree.Node directly from static context
            // Node will be created by this "node parameters" during construction of this class object
            // Constructed tree
            //0 - node index
            //1 - interval
            new NodeParameters[]{
                    new NodeParameters<Set<Interval>>(0, new Interval(Integer.MIN_VALUE, Integer.MAX_VALUE)),
                    new NodeParameters<Set<Interval>>(1, new Interval(Integer.MIN_VALUE, 4)),
                    new NodeParameters<Set<Interval>>(2, new Interval(4, EXCLUSIVE, Integer.MAX_VALUE)),
                    new NodeParameters<Set<Interval>>(3, new Interval(Integer.MIN_VALUE, 3)),
                    new NodeParameters<Set<Interval>>(4, new Interval(3, EXCLUSIVE, 4)),
                    new NodeParameters<Set<Interval>>(5, new Interval(4, EXCLUSIVE, 5)),
                    new NodeParameters<Set<Interval>>(6, true, new Interval(5, EXCLUSIVE, Integer.MAX_VALUE)),
                    new NodeParameters<Set<Interval>>(7, true, new Interval(Integer.MIN_VALUE, 3, EXCLUSIVE)),
                    new NodeParameters<Set<Interval>>(8, true, new Interval(3, 3)),
                    new NodeParameters<Set<Interval>>(9, true, new Interval(3, EXCLUSIVE, 4, EXCLUSIVE)),
                    new NodeParameters<Set<Interval>>(10, true, new Interval(4, 4)),
                    new NodeParameters<Set<Interval>>(11, true, new Interval(4, EXCLUSIVE, 5, EXCLUSIVE)),
                    new NodeParameters<Set<Interval>>(12, true, new Interval(5, 5)),
                    null, //13th node
                    null //14th node
            }
    };
}
