package segtree.impl.util;

/**
 * @author Denis Tyschenko
 *         Date: 9/6/13
 */
public class Interval {
    public final static boolean EXCLUSIVE = false;

    private final int begin;
    private final int end;

    private final boolean isBeginInclusive;
    private final boolean isEndInclusive;

    /**
     * By default interval bounds are inclusive.
     * @param begin - begin of the interval
     * @param end - end of the interval
     */
    public Interval(int begin, int end) {
       this(begin, true, end, true);
    }

    public Interval(int begin, boolean isBeginInclusive, int end) {
       this(begin, isBeginInclusive, end, true);
    }

    public Interval(int begin, int end, boolean isEndInclusive) {
       this(begin, true, end, isEndInclusive);
    }

    public Interval(Interval interval) {
        this(interval.getBegin(), interval.isBeginInclusive, interval.getEnd(), interval.isEndInclusive);
    }

    public Interval(int begin, boolean isBeginInclusive, int end, boolean isEndInclusive) {
        assertEndpointsAreCorrect(begin, isBeginInclusive, end, isEndInclusive);
        this.begin = begin;
        this.isBeginInclusive = isBeginInclusive;
        this.end = end;
        this.isEndInclusive = isEndInclusive;
    }

    private static void assertEndpointsAreCorrect(int begin, boolean isBeginInclusive, int end, boolean isEndInclusive) {
        if (begin > end)
            throw new IllegalArgumentException("Interval's begin: " + begin +
                    " must be not greater than interval's end: " + end);
        if ((!isBeginInclusive || !isEndInclusive) && begin == end)
            throw new IllegalArgumentException("Interval is a point, but exclusive bounds specified");
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public int calculateMiddle() {
        return (begin + end) / 2;
    }

    public boolean isPoint() {
        return this.getBegin() == this.getEnd();
    }

    public boolean contains(int point) {
        return (isBeginInclusive && begin == point) ||
                begin < point && end > point ||
                (isEndInclusive && end == point);
    }

    public boolean contains(Interval other) {
      if (other == null) throw new IllegalArgumentException("Interval must not be null");

      boolean compareBeginPointsImplicitly = this.isBeginInclusive || !other.isBeginInclusive;
      boolean compareEndPointsImplicitly = this.isEndInclusive || !other.isEndInclusive;

      return compare(compareBeginPointsImplicitly, this.begin, other.begin) &&
            compare(compareEndPointsImplicitly, other.end, this.end);
    }

    public boolean intersects(Interval other) {
      if (other == null) throw new IllegalArgumentException("Interval must not be null");
      boolean compareAImplicitly = this.isBeginInclusive && other.isEndInclusive;
      boolean compareBImplicitly = this.isEndInclusive && other.isBeginInclusive;

      return compare(compareAImplicitly, this.begin, other.end) &&
              compare(compareBImplicitly, other.begin, this.end);
    }

    private boolean compare(boolean isImplicitComparison, int a, int b){
        return isImplicitComparison ? a <= b : a < b;
    }

    /**
     * @return interval which has begin point at the beginning of the first interval
     * and ends at the end of the second one.
     */
    public static Interval stickFromFirstBeginToSecondEnd(Interval first, Interval second) {
        if (first.begin > second.end) throw new IllegalArgumentException("End point of the second interval " +
                "must be greater than start point of the first one");
        return new Interval(first.begin, first.isBeginInclusive, second.end, second.isEndInclusive);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (begin != interval.begin) return false;
        if (end != interval.end) return false;
        if (isBeginInclusive != interval.isBeginInclusive) return false;
        if (isEndInclusive != interval.isEndInclusive) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = begin;
        result = 31 * result + end;
        result = 31 * result + (isBeginInclusive ? 1 : 0);
        result = 31 * result + (isEndInclusive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        String beginBound = "(";
        String endBound = ")";
        if (isBeginInclusive) {
            beginBound = "[";
        }
        if (isEndInclusive) {
            endBound = "]";
        }
        return beginBound + begin + " , " + end + endBound;
    }
}
