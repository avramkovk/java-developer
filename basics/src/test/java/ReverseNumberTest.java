import org.junit.Assert;
import org.junit.Test;

public class ReverseNumberTest {
    @Test
    public void testReverseNumber() {
        Assert.assertEquals(123, ReverseNumber.reverse(321));
        Assert.assertEquals(-123, ReverseNumber.reverse(-321));
        Assert.assertEquals(1, ReverseNumber.reverse(1));
    }
}
