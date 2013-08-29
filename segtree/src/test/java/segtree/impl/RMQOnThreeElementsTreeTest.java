package segtree.impl;

import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
public class RMQOnThreeElementsTreeTest extends RMQTestSupport{
//            Segment tree:
//            {0, 0, 1, 1}
//            {1, 0, 0, 1}
//            {2, 1, 1, 2}
    private static Object[][] rmqRangeAndExpected = {
            {0,0,1}, {1,1,2}, {0,1,1}
    };

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndexpectedValueList() {
        return Arrays.asList(rmqRangeAndExpected);
    }

    public RMQOnThreeElementsTreeTest(int startRange, int endRange, Integer expectedRMQ) {
        super(startRange, endRange, expectedRMQ);
        inputArray = new Integer[]{1,2};
    }
}
