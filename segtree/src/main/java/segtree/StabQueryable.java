package segtree;

import segtree.impl.util.Interval;

import java.util.Set;

/**
 * @author Denis Tyschenko
 *         Date: 9/1/13
 */
public interface StabQueryable {

    public boolean insert(int begin, int end);

    /**
     * It is guaranteed that removal removes interval from all nodes in a tree
     * or removes nothing if there is no such interval.
     *
     * @return true if removal was successful, false otherwise.
     */
    public boolean remove(int begin, int end);

    public Set<Interval> stabbingQuery(int point);


}
