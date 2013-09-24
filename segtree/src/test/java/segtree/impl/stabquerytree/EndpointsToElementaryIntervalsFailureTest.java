package segtree.impl.stabquerytree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import segtree.impl.util.SegmentTreeUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Denis Tyschenko
 *         Date: 9/18/13
 */
@RunWith(Parameterized.class)
public class EndpointsToElementaryIntervalsFailureTest {
    @Parameterized.Parameters
    public static Collection<Object[]> inputValues() {
        return Arrays.asList(new Object[][]{
                {null}, {new Integer[]{}}, {new Integer[]{1}},
                {new Integer[]{1,2,3,3,5,6}}, {new Integer[]{1,2,4,3,5,6}}
        });
    }

    private Integer[] endpoints;

    public EndpointsToElementaryIntervalsFailureTest(Integer[] endpoints) {
        this.endpoints = endpoints;
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyThatConversionThrowsExceptionWhenInputIsInvalid() {
        SegmentTreeUtils.toElementaryIntervals(endpoints);
    }

}
