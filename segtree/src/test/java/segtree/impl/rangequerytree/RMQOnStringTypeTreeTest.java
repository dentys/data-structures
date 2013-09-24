package segtree.impl.rangequerytree;

import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Denis Tyschenko
 *         Date: 8/28/13
 */
public class RMQOnStringTypeTreeTest extends RMQTestSupport<String> {
    private static Object[][] rmqRangeAndExpected = {
            {0,0,"set"}, {1,1,"list"}, {0,1,"list"}
    };

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValueList() {
        return Arrays.asList(rmqRangeAndExpected);
    }

    public RMQOnStringTypeTreeTest(int startRange, int endRange, String expectedRMQ) {
        super(startRange, endRange, expectedRMQ);
        inputArray = (String[])RQTTestDataSet.RQT_ON_TWO_STRING_TYPE_ELEMENTS[0];
    }
}
