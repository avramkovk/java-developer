import org.junit.Assert;
import org.junit.Test;

public class SumNumbersTest {

    @Test
    public void testFindIndex() {
        Assert.assertEquals(2, SumNumbers.findIndexOfEqualSums(new int[]{1, 1, 2, 2}));
        Assert.assertEquals(3, SumNumbers.findIndexOfEqualSums(new int[]{1, 2, 3, 4, 3, 2, 1}));
        Assert.assertEquals(1, SumNumbers.findIndexOfEqualSums(new int[]{1, 100, 50, -51, 1, 1}));
        Assert.assertEquals(0, SumNumbers.findIndexOfEqualSums(new int[]{20, 10, -80, 10, 10, 15, 35}));
        Assert.assertEquals(-1, SumNumbers.findIndexOfEqualSums(new int[]{4, -1, -1, -1, -1}));
        Assert.assertEquals(0, SumNumbers.findIndexOfEqualSums(new int[]{4, 1, 1, -3, 1}));
        Assert.assertEquals(3,SumNumbers.findIndexOfEqualSums(new int[] {1,2,3,4,3,2,1}));
        Assert.assertEquals(1,SumNumbers.findIndexOfEqualSums(new int[] {1,100,50,-51,1,1}));
        Assert.assertEquals(-1,SumNumbers.findIndexOfEqualSums(new int[] {1,2,3,4,5,6}));
        Assert.assertEquals(3,SumNumbers.findIndexOfEqualSums(new int[] {1,2,3,4,3,2,1}));
        Assert.assertEquals(1,SumNumbers.findIndexOfEqualSums(new int[] {1,100,50,-51,1,1}));
        Assert.assertEquals(-1,SumNumbers.findIndexOfEqualSums(new int[] {1,2,3,4,5,6}));
        Assert.assertEquals(3,SumNumbers.findIndexOfEqualSums(new int[] {20,10,30,10,10,15,35}));
        Assert.assertEquals(-1,SumNumbers.findIndexOfEqualSums(new int[] {-8505, -5130, 1926, -9026}));
        Assert.assertEquals(1,SumNumbers.findIndexOfEqualSums(new int[] {2824, 1774, -1490, -9084, -9696, 23094}));
        Assert.assertEquals(6,SumNumbers.findIndexOfEqualSums(new int[] {4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4}));
        Assert.assertEquals(-1,SumNumbers.findIndexOfEqualSums(new int[] {-8505, -5130, 1926, -9026}));
        Assert.assertEquals(1,SumNumbers.findIndexOfEqualSums(new int[] {2824, 1774, -1490, -9084, -9696, 23094}));
        Assert.assertEquals(6,SumNumbers.findIndexOfEqualSums(new int[] {4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4}));
    }
}
