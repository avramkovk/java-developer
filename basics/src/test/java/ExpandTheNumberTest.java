import org.junit.Assert;
import org.junit.Test;

public class ExpandTheNumberTest {
    @Test
    public void testExpandNumber() {
        Assert.assertEquals("9", ExpandTheNumber.expandTheNumber(9));
        Assert.assertEquals("90 + 9", ExpandTheNumber.expandTheNumber(99));
        Assert.assertEquals("900 + 90 + 9", ExpandTheNumber.expandTheNumber(999));
        Assert.assertEquals("9000000 + 900000 + 90000 + 9000 + 900 + 90 + 9", ExpandTheNumber.expandTheNumber(9_999_999));
    }
}
