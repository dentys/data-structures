package segtree.impl;

import segtree.impl.util.Interval;

import java.util.Arrays;

/**
 * @author Denis Tyschenko
 *         Date: 8/31/13
 */
public abstract class AbstractSegmentTree<T> {
    protected Node[] nodes;
    protected int originalLength;

    @SuppressWarnings("unchecked")
    protected AbstractSegmentTree(T[] array) {
        if (array == null) throw new IllegalArgumentException("input array parameter must not be null");
        if (array.length == 0) throw new IllegalArgumentException("input array size must be greater than 0");

        build(array);
    }

    public Node[] getNodesSnapshot() {
        return Arrays.copyOf(nodes, nodes.length);
    }

    public class Node<E> {
        final int index;
        final Interval interval;
        final boolean isLeaf;
        E value;

        public Node(int index, boolean isLeaf, Interval interval) {
            this(index, isLeaf, interval, null);
        }

        public Node(int index, boolean isLeaf, Interval interval, E value) {
            this.value = value;
            this.interval = interval;
            this.index = index;
            this.isLeaf = isLeaf;
        }

        Node<E> parent() {
            return index == 0 ? null : index % 2 == 0 ? nodes[index/2 - 1] : nodes[index/2];
        }

        Node<E> sibling() {
            return index == 0 ? null : index % 2 == 0 ? nodes[index-1] : nodes[index+1];
        }

        Node<E> leftChild() {
            return nodes[leftChildIndex()];
        }

        Node<E> rightChild() {
            return nodes[rightChildIndex()];
        }

        Node<E> createLeftChild(boolean isLeaf, Interval interval) {
            int lci = leftChildIndex();
            nodes[lci] = new Node<E>(lci, isLeaf, interval);
            return nodes[lci];
        }

        Node<E> createRightChild(boolean isLeaf, Interval interval) {
            int rci = rightChildIndex();
            nodes[rci] = new Node<E>(rci, isLeaf, interval);
            return nodes[rci];
        }

        private int leftChildIndex() {
            return 2*index + 1;
        }

        private int rightChildIndex() {
            return 2*(index + 1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node node = (Node) o;

            if (index != node.index) return false;
            if (isLeaf != node.isLeaf) return false;
            if (!interval.equals(node.interval)) return false;
            if (value != null ? !value.equals(node.value) : node.value != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = index;
            result = 31 * result + interval.hashCode();
            result = 31 * result + (isLeaf ? 1 : 0);
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", interval=" + interval +
                    ", value=" + value +
                    '}';
        }
    }

    protected abstract void build(T[] array);

}
