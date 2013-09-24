package segtree.impl.rangequerytree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import segtree.impl.util.SegmentTreeUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 * //TODO: Add more cases
 * @author Denis Tyschenko
 *         Date: 8/22/13
 */
@RunWith(Parameterized.class)
public class RQTSizeTest {

    private static Object[][] inputAndExpectedValues = {
            //0 - input array size
            //1 - expected segment tree size
            {0,0},
            {1,1},
            {2,3},
            {6,15}
    };

    private int arraySize;
    private int expectedSegTreeSize;

    @Parameterized.Parameters
    public static Collection<Object[]> inputAndExpectedValueList() {
        return Arrays.asList(inputAndExpectedValues);
    }

    public RQTSizeTest(int arraySize, int expectedSegTreeSize) {
        this.arraySize = arraySize;
        this.expectedSegTreeSize = expectedSegTreeSize;
    }

    @Test
    public void verifyThatSegmentTreeSizeCalculationIsCorrect() {
        Assert.assertEquals("Segment tree size is not properly calculated by original array size : " + arraySize,
                expectedSegTreeSize, SegmentTreeUtils.treeSize(arraySize));
    }

}
