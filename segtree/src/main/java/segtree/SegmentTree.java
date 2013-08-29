package segtree;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
public interface SegmentTree<T> {

    public void update(T value, int position);
    public T rangeQuery(int startRange, int endRange);
}
