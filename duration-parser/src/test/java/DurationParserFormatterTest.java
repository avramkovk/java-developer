import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DurationParserFormatterTest {
    DurationParserFormatter pf = new DurationParserFormatter();

    @org.junit.Test
    public void testPositive() {
        Assert.assertEquals("0ms", pf.format(pf.parse((""))));

        Assert.assertEquals("0ms", pf.format(pf.parse(("0w"))));
        Assert.assertEquals("0ms", pf.format(pf.parse(("0d"))));
        Assert.assertEquals("0ms", pf.format(pf.parse(("0h"))));
        Assert.assertEquals("0ms", pf.format(pf.parse(("0m"))));
        Assert.assertEquals("0ms", pf.format(pf.parse(("0s"))));
        Assert.assertEquals("0ms", pf.format(pf.parse(("0ms"))));

        Assert.assertEquals("1w", pf.format(pf.parse(("1w"))));
        Assert.assertEquals("1d", pf.format(pf.parse(("1d"))));
        Assert.assertEquals("1h", pf.format(pf.parse(("1h"))));
        Assert.assertEquals("1m", pf.format(pf.parse(("1m"))));
        Assert.assertEquals("1s", pf.format(pf.parse(("1s"))));
        Assert.assertEquals("1ms", pf.format(pf.parse(("1ms"))));

        Assert.assertEquals("1w1d1h1m1s1ms", pf.format(pf.parse(("1w1d1h1m1s1ms"))));
        Assert.assertEquals("1w1d1h1m1s", pf.format(pf.parse(("1w1d1h1m1s"))));
        Assert.assertEquals("1w1d1h1m", pf.format(pf.parse(("1w1d1h1m"))));
        Assert.assertEquals("1w1d1h", pf.format(pf.parse(("1w1d1h"))));
        Assert.assertEquals("1w1d", pf.format(pf.parse(("1w1d"))));

        Assert.assertEquals("1d1h1m1s1ms", pf.format(pf.parse(("1d1h1m1s1ms"))));
        Assert.assertEquals("1d1h1m1s", pf.format(pf.parse(("1d1h1m1s"))));
        Assert.assertEquals("1d1h1m", pf.format(pf.parse(("1d1h1m"))));
        Assert.assertEquals("1d1h", pf.format(pf.parse(("1d1h"))));
        Assert.assertEquals("1d1h", pf.format(pf.parse(("1d1h"))));

        Assert.assertEquals("1h1m1s1ms", pf.format(pf.parse(("1h1m1s1ms"))));
        Assert.assertEquals("1h1m1s", pf.format(pf.parse(("1h1m1s"))));
        Assert.assertEquals("1h1m", pf.format(pf.parse(("1h1m"))));
        Assert.assertEquals("1h1m", pf.format(pf.parse(("1h1m"))));

        Assert.assertEquals("1m1s1ms", pf.format(pf.parse(("1m1s1ms"))));
        Assert.assertEquals("1m1s", pf.format(pf.parse(("1m1s"))));

        Assert.assertEquals("1s1ms", pf.format(pf.parse(("1s1ms"))));

        Assert.assertEquals("1w1h1m1s1ms", pf.format(pf.parse(("1w1h1m1s1ms"))));
        Assert.assertEquals("1w1m1s1ms", pf.format(pf.parse(("1w1m1s1ms"))));
        Assert.assertEquals("1w1s1ms", pf.format(pf.parse(("1w1s1ms"))));
        Assert.assertEquals("1w1ms", pf.format(pf.parse(("1w1ms"))));

        Assert.assertEquals("1w1d1m1s1ms", pf.format(pf.parse(("1w1d1m1s1ms"))));
        Assert.assertEquals("1w1d1s1ms", pf.format(pf.parse(("1w1d1s1ms"))));
        Assert.assertEquals("1w1d1ms", pf.format(pf.parse(("1w1d1ms"))));
        Assert.assertEquals("1w1d", pf.format(pf.parse(("1w1d"))));

        Assert.assertEquals("3w1h", pf.format(pf.parse(("2w6d25h"))));
        Assert.assertEquals("15m", pf.format(pf.parse(("900s"))));
        Assert.assertEquals("1d2s", pf.format(pf.parse(("1d2000ms"))));
        Assert.assertEquals("1s400ms", pf.format(pf.parse(("1s400ms"))));
        Assert.assertEquals("1m29s100ms", pf.format(pf.parse(("1m29100ms"))));

        Assert.assertEquals("1h20m10s", pf.format(pf.parse(("1h20m10s"))));
        Assert.assertEquals("5m10s111ms", pf.format(pf.parse(("5m10s111ms"))));
        Assert.assertEquals("1d30m", pf.format(pf.parse(("1d30m"))));
        Assert.assertEquals("2w1d", pf.format(pf.parse(("15d"))));

        Assert.assertEquals("14w2d", pf.format(pf.parse(("2400h"))));
        Assert.assertEquals("15w2d", pf.format(pf.parse(("1w2400h"))));
        Assert.assertEquals("1d3m20s", pf.format(pf.parse(("1d200s"))));

    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testNegative1() {
        exception.expect(IllegalArgumentException.class);
        pf.format(pf.parse(("1d1w")));
    }

    @Test
    public void testNegative2() {
        exception.expect(IllegalArgumentException.class);
        pf.format(pf.parse(("abc")));
    }

    @Test
    public void testNegative3() {
        exception.expect(IllegalArgumentException.class);
        pf.format(pf.parse(("1d1d")));
    }
}
