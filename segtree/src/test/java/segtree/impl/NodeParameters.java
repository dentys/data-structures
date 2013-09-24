package segtree.impl;

import segtree.impl.util.Interval;

/**
* @author Denis Tyschenko
*         Date: 9/23/13
*/
public class NodeParameters<E> {
    int index;
    Interval interval;
    E value;
    boolean isLeaf;

    public NodeParameters(int index, int start, int end, E value) {
        this(index, new Interval(start, end), value);
    }

    public NodeParameters(int index, boolean isLeaf, Interval interval) {
        this(index, isLeaf, interval, null);
    }

    public NodeParameters(int index, Interval interval) {
        this(index, interval, null);
    }

    public NodeParameters(int index, boolean isLeaf, Interval interval, E value) {
        this(index, interval, value);
        this.isLeaf = isLeaf;
    }

    public NodeParameters(int index, Interval interval, E value) {
        this.index = index;
        this.interval = interval;
        this.value = value;
    }
}
