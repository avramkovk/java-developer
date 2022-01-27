import org.junit.Assert;
import org.junit.Test;

public class SortTheOddTest {

    @Test
    public void testSortTheOdd() {
        Assert.assertArrayEquals(array(1,2,3), SortTheOdd.sortTheOdd(array(3,2,1)));
        Assert.assertArrayEquals(array(1, 4, 3, 2, 5),SortTheOdd.sortTheOdd(array(5, 4, 3, 2, 1)));
        Assert.assertArrayEquals(array(0, 2, 1, 7, -4),SortTheOdd.sortTheOdd(array(0, 2, 7, 1, -4)));
        Assert.assertArrayEquals(array(0, -1, 1, 4, 3),SortTheOdd.sortTheOdd(array(0, -1, 3, 4, 1)));
        Assert.assertArrayEquals(array(1, 0, 0, 0, 2),SortTheOdd.sortTheOdd(array(1, 0, 0, 0, 2)));
        Assert.assertArrayEquals(array(1, 3, 2, 8, 5, 4),SortTheOdd.sortTheOdd(array(5, 3, 2, 8, 1, 4)));
    }

    private int[] array(int... arr) {
        return arr;
    }
}