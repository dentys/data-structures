package segtree.impl;

import segtree.StabQueryable;
import segtree.impl.util.Interval;
import segtree.impl.util.SegmentTreeUtils;

import java.util.*;

/**
 *
 * @author Denis Tyschenko
 *         Date: 8/31/13
 */
public class StabQueryableSegmentTree extends AbstractSegmentTree<Integer> implements StabQueryable {

    public StabQueryableSegmentTree(Integer[] endpoints){
        super(endpoints);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void build(Integer[] endpoints) {
        Interval[] intervals = SegmentTreeUtils.toElementaryIntervals(endpoints);
        originalLength = intervals.length;
        nodes = new Node[SegmentTreeUtils.treeSize(originalLength)];
        // create root node, which describes the whole interval with size equal to the original intervals length
        Interval firstInterval = intervals[0];
        Interval lastInterval = intervals[originalLength - 1];
        nodes[0] = new Node<Set<Interval>>(0, nodes.length == 1, new Interval(Interval.stickFromFirstBeginToSecondEnd(firstInterval, lastInterval)));
        for(int i = 0; i < originalLength; i++ ) {
            insertOnBuild(intervals[i], intervals, nodes[0]);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean insert(int begin, int end) {
        Interval interval = new Interval(begin, end);
        Node<Set<Interval>> root = nodes[0];
        assertIntervalIsNotOutOfRange(interval, root);
        return performActionOnTree(interval, root, new InsertInterval());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(int begin, int end) {
        Interval interval = new Interval(begin, end);
        Node<Set<Interval>> root = nodes[0];
        assertIntervalIsNotOutOfRange(interval, root);
        return performActionOnTree(interval, root, new RemoveInterval());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Interval> stabbingQuery(int point) {
        Set<Interval> stabbingQueryResult = new LinkedHashSet<>();
        Node<Set<Interval>> root = nodes[0];
        if (!root.interval.contains(point)) throw new IllegalArgumentException("Specified query point: " + point +
                " is out of range: " + root.interval);
        if (root.value != null) {
            stabbingQueryResult.addAll(root.value);
        }
        Node<Set<Interval>> currentNode = root;
        while(!currentNode.isLeaf) {
            currentNode = (currentNode.leftChild().interval.contains(point)) ? currentNode.leftChild() :
                    currentNode.rightChild();
            if (currentNode.value != null) {
                stabbingQueryResult.addAll(currentNode.value);
            }
        }
        return stabbingQueryResult;
    }

    private boolean performActionOnTree(Interval interval, Node<Set<Interval>> root, ActionOnTreeNodes action) {
        // to simulate recursive stack we need a stack structure
        Deque<Node<Set<Interval>>> stack = new LinkedList<>();
        // structure for nodes on which action is to be performed
        List<Node<Set<Interval>>> markedNodes = new LinkedList<>();
        stack.push(root); // start from root
        while (!stack.isEmpty()) {
            Node<Set<Interval>> currentNode = stack.pop();
            while (!interval.contains(currentNode.interval)) {
                if (currentNode.isLeaf) throw new IllegalStateException("Begin:" + interval.getBegin() + " and End:"+
                        interval.getEnd() + " must match specified input endpoints");

                if (currentNode.rightChild().interval.intersects(interval)){
                    //push right subtree on stack to visit it later
                    stack.push(currentNode.rightChild());
                }
                if (currentNode.leftChild().interval.intersects(interval)){
                    currentNode = currentNode.leftChild();
                } else {
                    currentNode = stack.pop();
                }
            }
            markedNodes.add(currentNode);
        }
        return action.perform(markedNodes, interval);
    }

    private void insertOnBuild(Interval elementaryInterval, Interval[] elementaryIntervals, Node<Set<Interval>> currentNode) {
        int startIndex = 0;
        int endIndex = originalLength - 1;
        while(startIndex != endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;
            Node<Set<Interval>> leftChild = (currentNode.leftChild() == null) ?
                currentNode.createLeftChild(startIndex == middleIndex,
                        Interval.stickFromFirstBeginToSecondEnd(elementaryIntervals[startIndex], elementaryIntervals[middleIndex])) :
                currentNode.leftChild();
            if (leftChild.interval.contains(elementaryInterval)) {
                currentNode = leftChild;
                endIndex = middleIndex;
            } else { // if left subtree does not contain interval to insert, interval must belong to the right subtree
               startIndex = middleIndex + 1;
                Node<Set<Interval>> rightChild = (currentNode.rightChild() == null) ?
                    currentNode.createRightChild(startIndex == endIndex,
                            Interval.stickFromFirstBeginToSecondEnd(elementaryIntervals[startIndex], elementaryIntervals[endIndex])) :
                    currentNode.rightChild();
               if (!rightChild.interval.contains(elementaryInterval))
                   throw new IllegalArgumentException("Attempt to insert illegal interval : " + elementaryInterval +
                           " It cannot be inserted neither into the left subtree : " + leftChild +
                           " nor to the right subtree : " + rightChild +
                           " start index : " + startIndex + " end index : " + endIndex);
               currentNode = rightChild;
            }
        }
    }

    private static void assertIntervalIsNotOutOfRange(Interval interval, Node<Set<Interval>> root) {
        if (!root.interval.contains(interval))
            throw new IllegalArgumentException("Specified interval: " + interval + " is out of range: " + root.interval);
    }

    private interface ActionOnTreeNodes {
        /**
         * Performs an action on every node in a list of tree nodes.
         * Aggregated result of actions is the result of action on the last node in a list.
         * Action expected to have the same result for each element in a list which means that
         * the input list of nodes must be appropriate, that is guaranteed by algorithm design.
         *
         * @return true if action was successful for the last node in the list, false otherwise.
         */
        public boolean perform(List<StabQueryableSegmentTree.Node<Set<Interval>>> nodes, Interval interval);
    }

    private class InsertInterval implements ActionOnTreeNodes {
        @Override
        public boolean perform(List<StabQueryableSegmentTree.Node<Set<Interval>>> nodes, Interval interval) {
            boolean result = false;
            for (Node<Set<Interval>> node : nodes) {
                // Need to iterate through the set later thus linked structure would be a better implementation
                node.value = (node.value == null) ? new LinkedHashSet<Interval>() : node.value;
                result = node.value.add(interval);
            }
            return result;
        }
    }

    private class RemoveInterval implements ActionOnTreeNodes {
        @Override
        public boolean perform(List<StabQueryableSegmentTree.Node<Set<Interval>>> nodes, Interval interval) {
            boolean result = false;
            for (Node<Set<Interval>> node : nodes) {
                result = (node.value != null) && node.value.remove(interval);
            }
            return result;
        }
    }
}