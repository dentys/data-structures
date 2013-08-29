package segtree.impl;

import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Denis Tyschenko
 *         Date: 8/28/13
 */
public class RMQOnStringTypeTreeTest extends RMQTestSupport{
//            Segment tree:
//            {0, 0, 1, "list"}
//            {1, 0, 0, "set"}
//            {2, 1, 1, "list"}
    private static Object[][] rmqRangeAndExpected = {
            {0,0,"set"}, {1,1,"list"}, {0,1,"list"}
    };

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndexpectedValueList() {
        return Arrays.asList(rmqRangeAndExpected);
    }

    public RMQOnStringTypeTreeTest(int startRange, int endRange, String expectedRMQ) {
        super(startRange, endRange, expectedRMQ);
        inputArray = new String[]{"set", "list"};
    }
}
