import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ConvertStringToIntTest {
    @Test
    public void testPositiveCheck() {
        Assert.assertEquals(0, ConvertStringToInt.convertStringToInt("0"));
        Assert.assertEquals(123, ConvertStringToInt.convertStringToInt("123"));
        Assert.assertEquals(-1, ConvertStringToInt.convertStringToInt("-1"));
        Assert.assertEquals(-111111, ConvertStringToInt.convertStringToInt("-111111"));
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testNegativeCheck1() {
        exception.expect(IllegalStateException.class);
        ConvertStringToInt.convertStringToInt("");
    }
    @Test
    public void testNegativeCheck2() {
        exception.expect(IllegalStateException.class);
        ConvertStringToInt.convertStringToInt("-");
    }
    @Test
    public void testNegativeCheck3() {
        exception.expect(IllegalStateException.class);
        ConvertStringToInt.convertStringToInt("123q");
    }
    @Test
    public void testNegativeCheck4() {
        exception.expect(IllegalStateException.class);
        ConvertStringToInt.convertStringToInt("q123");
    }
    @Test
    public void testNegativeCheck5() {
        exception.expect(IllegalStateException.class);
        ConvertStringToInt.convertStringToInt("-1-1");
    }
    @Test
    public void testNegativeCheck6() {
        exception.expect(IllegalStateException.class);
        ConvertStringToInt.convertStringToInt("111_111");
    }
}
