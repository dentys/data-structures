package segtree.impl;

import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
public class RMQOnOneElementTreeTest extends RMQTestSupport{

    private static Object[][] rmqRangeAndExpected = {
            {0,0,1}
    };

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndexpectedValueList() {
        return Arrays.asList(rmqRangeAndExpected);
    }

    public RMQOnOneElementTreeTest(int startRange, int endRange, Integer expectedRMQ) {
        super(startRange, endRange, expectedRMQ);
        inputArray = new Integer[]{1};
    }
}
