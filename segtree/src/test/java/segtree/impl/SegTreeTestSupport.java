package segtree.impl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Assert;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
public class SegTreeTestSupport<T> {

    protected SegmentTreeImpl<T> segTree;

    protected SegmentTreeImpl<T>.Node[] expectedNodes;

    public static class NodeParameters<E> {
        int index;
        int start;
        int end;
        E value;

        public NodeParameters(int index, int start, int end, E value) {
            this.index = index;
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }

    protected SegmentTreeImpl<T>.Node[] buildExpectedNodes(NodeParameters<T>[] expectedNodeParameters) {
        SegmentTreeImpl<T>.Node[] nodes = new SegmentTreeImpl.Node[expectedNodeParameters.length];
        for(int i = 0; i < expectedNodeParameters.length; i++) {
            NodeParameters<T> nodeParams = expectedNodeParameters[i];
            if (nodeParams != null) {
                T nodeValue = nodeParams.value;
                int nodeIntervalStart = nodeParams.start;
                int nodeIntervalEnd = nodeParams.end;
                int nodeIndex = nodeParams.index;
                nodes[i] = segTree.new Node(nodeIndex, nodeIntervalStart, nodeIntervalEnd, nodeValue);
            }
        }
        return nodes;
    }

    public static void assertNodeArraysAreEqual(String message, SegmentTreeImpl.Node[] actualNodes, SegmentTreeImpl.Node[] expectedNodes) {
        Assert.assertEquals(message + " Expected segtree size do not match actual", expectedNodes.length, actualNodes.length);
        for(int i=0; i < expectedNodes.length; i++) {
            Assert.assertTrue(message + ". Invalid segtree node with index : "+i+
                    ", expected segtree node : "+
                    ToStringBuilder.reflectionToString(expectedNodes[i], ToStringStyle.SHORT_PREFIX_STYLE)+" " +
                    "does not match actual : " +
                    ToStringBuilder.reflectionToString(actualNodes[i], ToStringStyle.SHORT_PREFIX_STYLE),
                    EqualsBuilder.reflectionEquals(expectedNodes[i], actualNodes[i]));
        }
    }

    public static void assertNodeArraysAreEqual(SegmentTreeImpl.Node[] actualNodes, SegmentTreeImpl.Node[] expectedNodes) {
        assertNodeArraysAreEqual("", actualNodes, expectedNodes);
    }
}
