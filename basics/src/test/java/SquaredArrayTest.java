import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class SquaredArrayTest {
    @Test
    public void testNegativeCompareArray() {
        int[] a = {11, 11, 11};
        int[] b = {121, 144, 169};
        Assert.assertFalse(SquaredArray.compareArray(array(11,11,11),array(121,144,169)));
        a = new int[]{-1, 4, -3};
        b = new int[]{4, 9, 1};
        Assert.assertTrue("Squared array a ["+ Arrays.toString(a)+"] is not b"+ Arrays.toString(b),SquaredArray.compareArray(a, b));
    }

    @Test
    public void testCompareArray() {
        int[] a = {121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = {121, 14641, 20736, 361, 25921, 361, 20736, 361};
        Assert.assertTrue(SquaredArray.compareArray(a, b));
    }

    private int[] array(int ... arr){
        return arr;
    }
}

