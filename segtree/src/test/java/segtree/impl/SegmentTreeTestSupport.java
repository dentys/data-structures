package segtree.impl;

import segtree.impl.util.Interval;

import java.util.Set;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
public class SegmentTreeTestSupport<T, S extends AbstractSegmentTree<T>, N> {

    protected S segTree;

    protected AbstractSegmentTree<T>.Node<N>[] expectedNodes;

    @SuppressWarnings("unchecked")
    protected RangeQueryableSegmentTree<T>.Node<T>[] buildRQExpectedNodes(NodeParameters<T>[] expectedNodeParameters) {
        RangeQueryableSegmentTree<T>.Node<T>[] nodes = new RangeQueryableSegmentTree.Node[expectedNodeParameters.length];
        for(int i = 0; i < expectedNodeParameters.length; i++) {
            NodeParameters<T> nodeParams = expectedNodeParameters[i];
            if (nodeParams != null) {
                T nodeValue = nodeParams.value;
                Interval interval = nodeParams.interval;
                int nodeIndex = nodeParams.index;
                boolean isLeaf = interval.getBegin() == interval.getEnd();
                nodes[i] = segTree.new Node<T>(nodeIndex, isLeaf, interval, nodeValue);
            }
        }
        return nodes;
    }
    @SuppressWarnings("unchecked")
    protected StabQueryableSegmentTree.Node<Set<Interval>>[] buildSQExpectedNodes(NodeParameters<Set<Interval>>[] expectedNodeParameters) {
        StabQueryableSegmentTree.Node<Set<Interval>>[] nodes = new StabQueryableSegmentTree.Node[expectedNodeParameters.length];
        for(int i = 0; i < expectedNodeParameters.length; i++) {
            NodeParameters<Set<Interval>> nodeParams = expectedNodeParameters[i];
            if (nodeParams != null) {
                Set<Interval> nodeValue = nodeParams.value;
                Interval interval = nodeParams.interval;
                int nodeIndex = nodeParams.index;
                boolean isLeaf = nodeParams.isLeaf;
                nodes[i] = segTree.new Node(nodeIndex, isLeaf, interval, nodeValue);
            }
        }
        return nodes;
    }
}
