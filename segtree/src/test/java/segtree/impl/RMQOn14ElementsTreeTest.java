package segtree.impl;

import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Denis Tyschenko
 *         Date: 8/26/13
 */
public class RMQOn14ElementsTreeTest extends RMQTestSupport{

    private static Object[][] rmqRangeAndExpected = {
//            Segment tree:
//            {0, 0, 5, 0},
//            {1, 0, 2, 1},
//            {2, 3, 5, 0},
//            {3, 0, 1, 1},
//            {4, 2, 2, 3},
//            {5, 3, 4, 2},
//            {6, 5, 5, 0},
//            {7, 0, 0, 1},
//            {8, 1, 1, 5},
//            null, // 9th node
//            null, //10th node
//            {11, 3, 3, 4},
//            {12, 4, 4, 2},
//            null, // 13th node
//            null  // 14th node}
            {0,5,0}, {0,2,1}, {0,2,1}, {3,5,0},
            {0,1,1}, {2,2,3}, {3,4,2}, {5,5,0},
            {0,0,1}, {1,1,5}, {3,3,4}, {4,4,2}

    };
    @Parameterized.Parameters
    public static Collection<Object[]> inputAndexpectedValueList() {
        return Arrays.asList(rmqRangeAndExpected);
    }

    public RMQOn14ElementsTreeTest(int startRange, int endRange, Integer expectedRmq) {
        super(startRange, endRange, expectedRmq);
        inputArray = new Integer[]{1,5,3,4,2,0};
    }
}
