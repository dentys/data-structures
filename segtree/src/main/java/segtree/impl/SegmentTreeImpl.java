package segtree.impl;

import segtree.SegmentTree;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class provides basic operations on a segment tree data structure. //TODO : seg tree benefit, basic operations complexity
 * <br/>
 * Segment tree constructed by provided array of elements. Array must not be null and with non zero length.
 * Segment tree permits null values. Elements in a segment tree must implement Comparable interface or Comparator
 * must be provided. In case neither elements implement Comparable nor Comparator is provided ClassCastException
 * will be thrown.
 * Not Thread Safe.
 * @author Denis Tyschenko
 *         Date: 8/16/13
 */
public class SegmentTreeImpl<T> implements SegmentTree<T> {
    private final Node[] nodes;
    private final int originalLength;
    private final Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public SegmentTreeImpl(Comparator<T> comparator, T... array) {
        if (array == null) throw new IllegalArgumentException("input array parameter must not be null");
        if (array.length == 0) throw new IllegalArgumentException("input array size must be greater than 0");

        this.originalLength = array.length;
        this.comparator = comparator;
        this.nodes = new SegmentTreeImpl.Node[treeSize(originalLength)];
        // create root node, which describes the whole interval with size equal to the original array length
        nodes[0] = new Node(0, 0, originalLength - 1);
        // fill segtree with values in time proportional to O(n*log(n)),
        // where n is a number of elements in a segment tree
        for (int i = 0; i < originalLength; i++) {
             init(array[i], i);
        }
    }

    public SegmentTreeImpl(T... array) {
        this(null, array);
    }

    public T rangeQuery(int startRange, int endRange) {
        if (startRange > endRange || startRange < 0 )
            throw new IllegalArgumentException("invalid range startRange: " + startRange +
                    " must be greater then zero and not greater then range endRange");
        if (endRange > originalLength - 1)
            throw new IllegalArgumentException("invalid endRange: " + endRange + " of the range, " +
                    "must be less then original array length : " + originalLength);

        Node root = nodes[0];
        return rangeQuery(startRange, endRange, root);
    }

    public void update (T value, int position) {
        if (position >= originalLength || position < 0)
            throw new IllegalArgumentException("specified position:" + position + " is out of range");

        Node currentNode = getLeafNode(position, nodes[0]);

        currentNode.value = value;
        Node parent;
        while ((parent = currentNode.parent()) != null) {
            parent.value = compare(currentNode.sibling().value, value);
            value = parent.value;
            currentNode = parent;
        }
    }

    private Node getLeafNode(int position, Node currentNode) {
        while(!currentNode.isLeaf()) {
            currentNode = (position <= currentNode.middle) ? currentNode.leftChild() : currentNode.rightChild();
        }
        return currentNode;
    }

    private void init (T value, int position) {
        // start from the root node, it is already non null
        Node currentNode = nodes[0];
        // this loop invariant will work since start always less then end,
        // and on every iteration we get half of the previous interval
        while(!currentNode.isLeaf()) {
            // update all nodes on the way down to the leaf
            currentNode.value = compare(currentNode.value, value);
            if (position <= currentNode.middle) {
                // get left child node, create if it is not exist
                currentNode = (currentNode.leftChild() == null) ?
                        currentNode.createLeftChild() : currentNode.leftChild();
            } else {
                // get right child node, create if it is not exist
                currentNode = (currentNode.rightChild() == null) ?
                        currentNode.createRightChild() : currentNode.rightChild();
            }
        }
        currentNode.value = value;
    }

    /**
     * Recursive function with one max recursive call.
     */
    private T rangeQuery(int startRange, int endRange, Node currentNode) {
        T rightSubTreeRMQ = null;

        while ( !currentNode.isLeaf() && !currentNode.isNodeBoundsInside(startRange, endRange)) {
            if (startRange > currentNode.middle) {
                currentNode = currentNode.rightChild();
            } else if (endRange <= currentNode.middle) {
                currentNode = currentNode.leftChild();
            } else if (startRange <= currentNode.middle && endRange > currentNode.middle) {
                // visit both nodes (right first)
                // this case is possible only once by algorithm's design
                rightSubTreeRMQ = rangeQuery(startRange, endRange, currentNode.rightChild());
                currentNode = currentNode.leftChild();
            }
        }
        return compare(currentNode.value, rightSubTreeRMQ);
    }

    private T compare(T t1,T t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        return comparator == null ?
                ((Comparable<T>)t1).compareTo(t2) < 0 ? t1 : t2:
                (comparator.compare(t1, t2) < 0) ? t1 : t2;
    }

    class Node {
        int start;
        int end;
        int middle;
        int index;
        T value;

        public Node(int index, int start, int end) {
            this(index, start, end, null);
        }

        public Node(int index, int start, int end, T value) {
            this.value = value;
            this.start = start;
            this.end = end;
            this.index = index;
            this.middle = (start + end) / 2;
        }

        boolean isLeaf() {
            return start == end;
        }

        boolean isNodeBoundsInside(int startPos, int endPos) {
            return startPos <= start && endPos >= end;
        }

        Node parent() {
            return index == 0 ? null : index % 2 == 0 ? nodes[index/2 - 1] : nodes[index/2];
        }

        Node sibling() {
            return index == 0 ? null : index % 2 == 0 ? nodes[index-1] : nodes[index+1];
        }

        Node leftChild() {
            return nodes[leftChildIndex()];
        }

        Node rightChild() {
            return nodes[rightChildIndex()];
        }

        Node createLeftChild() {
            int lci = leftChildIndex();
            nodes[lci] = new Node(lci, start, middle);
            return nodes[lci];
        }

        Node createRightChild() {
            int rci = rightChildIndex();
            nodes[rci] =new Node(rci, middle + 1, end);
            return nodes[rci];
        }

        private int leftChildIndex() {
            return 2*index + 1;
        }

        private int rightChildIndex() {
            return 2*(index + 1);
        }
    }

    /**
     *
     * @param n - number of leafs in the segment tree
     * @return
     */
    static int treeSize(int n) {
        //2*n-1 - number of all nodes in a segment tree.
        // height of such tree is log_2_(2*N-1)
        // make log with base two by division, since log_b_x = log_k_x / log_k_b
        double log2N = Math.log10(2*n-1)/Math.log10(2);
        double power = Math.floor(log2N);
        //number of elements in a perfect BT with height log_2_(2*N-1)
        // some of elements may not be used(leafs of a perfect BT), but it they are necessary
        // to keep relation between parent child indexes
        //TODO: Is integer overflow possible here ?
        return (int)(2 * Math.pow(2, power) - 1);
    }

    Node[] getNodesSnapshot() {
        return Arrays.copyOf(nodes, nodes.length);
    }

}
