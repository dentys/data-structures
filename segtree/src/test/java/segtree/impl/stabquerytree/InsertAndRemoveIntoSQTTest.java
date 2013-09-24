package segtree.impl.stabquerytree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import segtree.impl.AbstractSegmentTree;
import segtree.impl.NodeParameters;
import segtree.impl.SegmentTreeTestSupport;
import segtree.impl.StabQueryableSegmentTree;
import segtree.impl.util.Interval;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static segtree.impl.util.Interval.EXCLUSIVE;
import static segtree.impl.stabquerytree.SQTTestDataSet.*;

/**
 * Tests insertion alongside with removal of intervals on a segment tree.
 * @author Denis Tyschenko
 *         Date: 9/16/13
 */
@RunWith(Parameterized.class)
public class InsertAndRemoveIntoSQTTest extends SegmentTreeTestSupport<Integer, StabQueryableSegmentTree, Set<Interval>> {

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValueList() {
        return Arrays.asList(new Object[][]{
                { SQT_ON_ONE_INTERVAL[0],
                  new Insert[] {
                          // inserted interval begins and ends at points
                          new Insert(Integer.MIN_VALUE, Integer.MAX_VALUE)},
                  //Seg Tree after interval insertion
                  new NodeParameters[]{
                     new NodeParameters<Set<Interval>>(0, true, new Interval(Integer.MIN_VALUE, Integer.MAX_VALUE),
                             new LinkedHashSet<Interval>(){
                                { add(new Interval(Integer.MIN_VALUE, Integer.MAX_VALUE)); }
                             })
                  }
                },

                { SQT_ON_TREE_INTERVALS[0], new Insert[] {new Insert(Integer.MIN_VALUE, 3)},
                  new NodeParameters[]{
                       new NodeParameters(0, new Interval(Integer.MIN_VALUE, 5)),
                       new NodeParameters<Set<Interval>>(1, new Interval(Integer.MIN_VALUE, 3),
                           new LinkedHashSet<Interval>(){
                               { add(new Interval(Integer.MIN_VALUE, 3)); }
                           }),
                       new NodeParameters(2, true, new Interval(3, EXCLUSIVE, 5)),
                       new NodeParameters(3, true, new Interval(Integer.MIN_VALUE, 3, EXCLUSIVE)),
                       new NodeParameters(4, true, new Interval(3, 3)),
                       null,
                       null
                  }
                },
                { SQT_ON_TREE_INTERVALS[0], new Insert[] {
                        new Insert(Integer.MIN_VALUE, 3),
                        new Insert(3,5)},
                        new NodeParameters[]{
                                new NodeParameters(0, new Interval(Integer.MIN_VALUE, 5)),
                                new NodeParameters<Set<Interval>>(1, new Interval(Integer.MIN_VALUE, 3),
                                        new LinkedHashSet<Interval>(){
                                            { add(new Interval(Integer.MIN_VALUE, 3)); }
                                        }),
                                new NodeParameters<Set<Interval>>(2, true, new Interval(3, EXCLUSIVE, 5),
                                        new LinkedHashSet<Interval>(){
                                            { add(new Interval(3,5)); }
                                        }),
                                new NodeParameters(3, true, new Interval(Integer.MIN_VALUE, 3, EXCLUSIVE)),
                                new NodeParameters<Set<Interval>>(4, true, new Interval(3, 3),
                                        new LinkedHashSet<Interval>(){
                                            { add(new Interval(3,5)); }
                                        }),
                                null,
                                null
                        }
                },
                { SQT_ON_TREE_INTERVALS[0], new Insert[] {
                        new Insert(Integer.MIN_VALUE, 3),
                        new Insert(3,5),
                        new Insert(Integer.MIN_VALUE, 5)},
                        new NodeParameters[]{
                                new NodeParameters<Set<Interval>>(0, new Interval(Integer.MIN_VALUE, 5),
                                        new LinkedHashSet<Interval>(){
                                            { add(new Interval(Integer.MIN_VALUE, 5)); }
                                        }),
                                new NodeParameters<Set<Interval>>(1, new Interval(Integer.MIN_VALUE, 3),
                                        new LinkedHashSet<Interval>(){
                                            { add(new Interval(Integer.MIN_VALUE, 3)); }
                                        }),
                                new NodeParameters<Set<Interval>>(2, true, new Interval(3, EXCLUSIVE, 5),
                                        new LinkedHashSet<Interval>(){
                                            { add(new Interval(3,5)); }
                                        }),
                                new NodeParameters(3, true, new Interval(Integer.MIN_VALUE, 3, EXCLUSIVE)),
                                new NodeParameters<Set<Interval>>(4, true, new Interval(3, 3),
                                        new LinkedHashSet<Interval>(){
                                            { add(new Interval(3,5)); }
                                        }),
                                null,
                                null
                        }
                },

                { SQT_ON_TREE_INTERVALS[0], new Insert[] {
                        new Insert(Integer.MIN_VALUE, 3),
                        new Insert(3,5),
                        new Insert(Integer.MIN_VALUE, 5),
                        new Insert(3,3),},
                        new NodeParameters[]{
                                new NodeParameters<Set<Interval>>(0, new Interval(Integer.MIN_VALUE, 5),
                                        new LinkedHashSet<Interval>(){
                                            { add(new Interval(Integer.MIN_VALUE, 5)); }
                                        }),
                                new NodeParameters<Set<Interval>>(1, new Interval(Integer.MIN_VALUE, 3),
                                        new LinkedHashSet<Interval>(){
                                            { add(new Interval(Integer.MIN_VALUE, 3)); }
                                        }),
                                new NodeParameters<Set<Interval>>(2, true, new Interval(3, EXCLUSIVE, 5),
                                        new LinkedHashSet<Interval>(){
                                            { add(new Interval(3,5)); }
                                        }),
                                new NodeParameters(3, true, new Interval(Integer.MIN_VALUE, 3, EXCLUSIVE)),
                                new NodeParameters<Set<Interval>>(4, true, new Interval(3, 3),
                                        new LinkedHashSet<Interval>(){
                                            { add(new Interval(3,3)); }
                                            { add(new Interval(3,5)); }
                                        }),
                                null,
                                null
                        }
                },

                {  SQT_ON_FIVE_INTERVALS[0], new Insert[]{ new Insert(3, 5)},
                   new NodeParameters[]{
                        new NodeParameters(0, new Interval(Integer.MIN_VALUE, Integer.MAX_VALUE)),
                        new NodeParameters(1, new Interval(Integer.MIN_VALUE, 5, EXCLUSIVE)),
                        new NodeParameters(2, new Interval(5, Integer.MAX_VALUE)),
                        new NodeParameters(3, new Interval(Integer.MIN_VALUE, 3)),
                        new NodeParameters<Set<Interval>>(4, true, new Interval(3, EXCLUSIVE, 5, EXCLUSIVE), new LinkedHashSet<Interval>(){
                            { add(new Interval(3,5)); }
                        }),
                        new NodeParameters<Set<Interval>>(5, true, new Interval(5, 5), new LinkedHashSet<Interval>(){
                            { add(new Interval(3,5)); }
                        }),
                        new NodeParameters(6, true, new Interval(5, EXCLUSIVE, Integer.MAX_VALUE)),
                        new NodeParameters(7, true, new Interval(Integer.MIN_VALUE, 3, EXCLUSIVE)),
                        new NodeParameters<Set<Interval>>(8, true, new Interval(3,3), new LinkedHashSet<Interval>(){
                            { add(new Interval(3,5)); }
                        }),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                   }
                },

                {  SQT_ON_SEVEN_INTERVALS[0], new Insert[]{new Insert(3,5)},
                   new NodeParameters[]{
                         new NodeParameters<Set<Interval>>(0, new Interval(Integer.MIN_VALUE, Integer.MAX_VALUE)),
                         new NodeParameters<Set<Interval>>(1, new Interval(Integer.MIN_VALUE, 4)),
                         new NodeParameters<Set<Interval>>(2, new Interval(4, EXCLUSIVE, Integer.MAX_VALUE)),
                         new NodeParameters<Set<Interval>>(3, new Interval(Integer.MIN_VALUE, 3)),
                         new NodeParameters<Set<Interval>>(4, new Interval(3, EXCLUSIVE, 4), new LinkedHashSet<Interval>(){
                             { add(new Interval(3,5)); }
                         }),
                         new NodeParameters<Set<Interval>>(5, new Interval(4, EXCLUSIVE, 5), new LinkedHashSet<Interval>(){
                             { add(new Interval(3,5)); }
                         }),
                         new NodeParameters<Set<Interval>>(6, true, new Interval(5, EXCLUSIVE, Integer.MAX_VALUE)),
                         new NodeParameters<Set<Interval>>(7, true, new Interval(Integer.MIN_VALUE, 3, EXCLUSIVE)),
                         new NodeParameters<Set<Interval>>(8, true, new Interval(3, 3), new LinkedHashSet<Interval>(){
                             { add(new Interval(3,5)); }
                         }),
                         new NodeParameters<Set<Interval>>(9, true, new Interval(3, EXCLUSIVE, 4, EXCLUSIVE)),
                         new NodeParameters<Set<Interval>>(10, true, new Interval(4, 4)),
                         new NodeParameters<Set<Interval>>(11, true, new Interval(4, EXCLUSIVE, 5, EXCLUSIVE)),
                         new NodeParameters<Set<Interval>>(12, true, new Interval(5, 5)),
                         null, //13th node
                         null //14th node
                   }
                },
        });
    }

    private Insert[] insertions;

    public InsertAndRemoveIntoSQTTest(Integer[] endpoints, Insert[] insertions, NodeParameters<Set<Interval>>[] nodeParameters) {
        segTree = new StabQueryableSegmentTree(endpoints);
        expectedNodes = super.buildSQExpectedNodes(nodeParameters);
        this.insertions = insertions;
    }

    @Test
    public void verifyThatInsertionIntoSegTreeIsCorrect() {
        for(Insert insertion : insertions) {
            segTree.insert(insertion.getBegin(), insertion.getEnd());
        }
        Assert.assertArrayEquals(expectedNodes, segTree.getNodesSnapshot());
    }

    @Test
    public void verifyThatRemovalFromSegTreeIsCorrect() {
        AbstractSegmentTree.Node[] snapshot = segTree.getNodesSnapshot();
        AbstractSegmentTree.Node[] initialTreeCopy = Arrays.copyOf(snapshot, snapshot.length);
        for(Insert insertion : insertions) {
            segTree.insert(insertion.getBegin(), insertion.getEnd());
        }
        for(Insert insertion : insertions) {
            segTree.remove(insertion.getBegin(), insertion.getEnd());
        }
        Assert.assertArrayEquals(initialTreeCopy, segTree.getNodesSnapshot());
    }

    static class Insert {
        private final int begin;
        private final int end;

        private Insert(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        int getBegin() {
            return begin;
        }

        int getEnd() {
            return end;
        }
    }

}
