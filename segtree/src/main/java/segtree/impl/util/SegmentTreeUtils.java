package segtree.impl.util;

import static segtree.impl.util.Interval.EXCLUSIVE;

/**
 * @author Denis Tyschenko
 *         Date: 9/23/13
 */
public class SegmentTreeUtils {

    /**
     *
     * @param n - number of leafs in the segment tree
     * @return number of nodes in a segment tree
     */
    public static int treeSize(int n) {
        //2*n-1 - number of all nodes in a segment tree.
        // height of such tree is log_2_(2*N-1)
        // make log with base two by division, since log_b_x = log_k_x / log_k_b
        double log2N = Math.log10(2*n-1)/Math.log10(2);
        double power = Math.floor(log2N);
        //number of elements in a perfect BT with height log_2_(2*N-1)
        // some of elements may not be used(leafs of a perfect BT), but they are necessary
        // to keep relation between parent child indexes
        //TODO: Is integer overflow possible here ?
        return (int)(2 * Math.pow(2, power) - 1);
    }

    public static Interval[] toElementaryIntervals(Integer[] endpoints) {
        if (endpoints == null || endpoints.length < 2)
            throw new IllegalArgumentException("At least two endpoints must be specified");

        Interval[] elementaryIntervals = initElementaryIntervalsArray(endpoints);
        assertEndpointsAreCorrect(endpoints[0], endpoints[1]);
        int index = 0;
        if (elementaryIntervals.length == 1 ) {
            //only two endpoints in input
            elementaryIntervals[index] = new Interval(endpoints[0], endpoints[1]);
            return elementaryIntervals;
        }
        elementaryIntervals[index] = new Interval(endpoints[0], endpoints[1], EXCLUSIVE);

        for (int i = 1; i < endpoints.length-1; i++) {
            assertEndpointsAreCorrect(endpoints[i], endpoints[i + 1]);
            elementaryIntervals[++index] = new Interval(endpoints[i], endpoints[i]);
            elementaryIntervals[++index] = new Interval(endpoints[i], EXCLUSIVE, endpoints[i+1], EXCLUSIVE);
        }

        Integer last = endpoints[endpoints.length - 1];
        elementaryIntervals[index] = new Interval(elementaryIntervals[index].getBegin(), EXCLUSIVE, last);
        return elementaryIntervals;
    }

    private static void assertEndpointsAreCorrect(Integer endpointA, Integer endpointB) {
        if (endpointA == null || endpointB == null) throw new IllegalArgumentException("Endpoint must not be null");
        if (endpointA >= endpointB)
            throw new IllegalArgumentException("Endpoints must be specified in ascending order, without repetition");
    }

    private static Interval[] initElementaryIntervalsArray(Integer[] endpoints) {
        int n = endpoints.length - 1;
        int intervalLength = 1 + 2*(n-1);
        return new Interval[intervalLength];
    }
}
