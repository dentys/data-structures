package segtree.impl.rangequerytree;

import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
public class RMQOn14ElementsTreeTest extends RMQTestSupport<Integer> {

    private static Object[][] rmqRangeAndExpected = {
            {0,5,0}, {0,2,1}, {0,2,1}, {3,5,0},
            {0,1,1}, {2,2,3}, {3,4,2}, {5,5,0},
            {0,0,1}, {1,1,5}, {3,3,4}, {4,4,2}

    };
    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValueList() {
        return Arrays.asList(rmqRangeAndExpected);
    }

    public RMQOn14ElementsTreeTest(int startRange, int endRange, Integer expectedRmq) {
        super(startRange, endRange, expectedRmq);
        inputArray = (Integer[])RQTTestDataSet.RQT_ON_SIX_ELEMENTS[0];
    }
}
