package segtree.impl;

import segtree.impl.util.Interval;
import segtree.RangeQueryable;
import segtree.impl.util.SegmentTreeUtils;

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
public class RangeQueryableSegmentTree<T> extends AbstractSegmentTree<T> implements RangeQueryable<T> {

    private final Comparator<T> comparator;

    public RangeQueryableSegmentTree(Comparator<T> comparator, T[] array) {
        super(array);
        this.comparator = comparator;
    }

    public RangeQueryableSegmentTree(T[] array) {
        this(null, array);
    }

    @SuppressWarnings("unchecked")
    public T rangeQuery(int startRange, int endRange) {
        if (startRange > endRange || startRange < 0 )
            throw new IllegalArgumentException("invalid startRange: " + startRange +
                    " must be greater then zero and not greater then range endRange");
        if (endRange > originalLength - 1)
            throw new IllegalArgumentException("invalid endRange: " + endRange +
                    " must be less then original array length : " + originalLength);

        Node<T> root = nodes[0];
        return rangeQuery(startRange, endRange, root);
    }

    @SuppressWarnings("unchecked")
    public void update (T value, int position) {
        if (position >= originalLength || position < 0)
            throw new IllegalArgumentException("specified position:" + position + " is out of range");

        Node<T> currentNode = nodes[0];
        //go down to the leaf
        while(!currentNode.isLeaf) {
            currentNode = (position <= currentNode.interval.calculateMiddle()) ? currentNode.leftChild() : currentNode.rightChild();
        }
        //go up to the root
        currentNode.value = value;
        Node<T> parent;
        while ((parent = currentNode.parent()) != null) {
            parent.value = compare(currentNode.sibling().value, value);
            value = parent.value;
            currentNode = parent;
        }
    }

    @Override
    protected void build(T[] array) {
        originalLength = array.length;
        nodes = new Node[SegmentTreeUtils.treeSize(originalLength)];
        // create root node, which describes the whole interval with size equal to the original array length
        nodes[0] = new Node<T>(0, nodes.length == 1, new Interval(0, originalLength - 1));
        // fill segtree with values in time proportional to O(n*log(n)),
        // where n is a number of elements in a segment tree
        for (int i = 0; i < originalLength; i++) {
            insert(array[i], i);
        }
    }

    @SuppressWarnings("unchecked")
    private void insert(T value, int position) {
        // start from the root node, it is already non null
        Node<T> currentNode = nodes[0];
        // this loop invariant will work since start always less then end,
        // and on every iteration we get half of the previous interval
        while(!currentNode.interval.isPoint()) {
            // update all nodes on the way down to the leaf
            currentNode.value = compare(currentNode.value, value);
            Interval nodeInterval = currentNode.interval;
            int middle = nodeInterval.calculateMiddle();
            if (position <= middle) {
                // get left child node, create if it is not exist
                Interval interval = new Interval(nodeInterval.getBegin(), middle);
                currentNode = (currentNode.leftChild() == null) ?
                        currentNode.createLeftChild(interval.isPoint(), interval) : currentNode.leftChild();
            } else {
                // get right child node, create if it is not exist
                Interval interval = new Interval(middle + 1, nodeInterval.getEnd());
                currentNode = (currentNode.rightChild() == null) ?
                        currentNode.createRightChild(interval.isPoint(), interval) : currentNode.rightChild();
            }
        }
        currentNode.value = value;
    }

    /**
     * Recursive function with maximum one recursive call.
     */
    private T rangeQuery(int startRange, int endRange, Node<T> currentNode) {
        T rightSubTreeRMQ = null;
        Interval range = new Interval(startRange, endRange);
        while (!range.contains(currentNode.interval)) {
            int middle = currentNode.interval.calculateMiddle();
            if (startRange > middle) {
                currentNode = currentNode.rightChild();
            } else if (endRange <= middle) {
                currentNode = currentNode.leftChild();
            } else {//range.contains(middle)
                // visit both nodes (right first)
                // this case is possible only once by algorithm's design
                rightSubTreeRMQ = rangeQuery(startRange, endRange, currentNode.rightChild());
                currentNode = currentNode.leftChild();
            }
        }
        return compare(currentNode.value, rightSubTreeRMQ);
    }

    @SuppressWarnings("unchecked")
    private T compare(T t1,T t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        return comparator == null ?
                ((Comparable<T>)t1).compareTo(t2) < 0 ? t1 : t2:
                (comparator.compare(t1, t2) < 0) ? t1 : t2;
    }

}
