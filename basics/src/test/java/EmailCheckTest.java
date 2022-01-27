import org.junit.Assert;
import org.junit.Test;

public class EmailCheckTest {

    @Test
    public void testPositiveCheck() {
        Assert.assertTrue(EmailCheck.validateEmail("email@asd.com"));
        Assert.assertTrue(EmailCheck.validateEmail("email@asd.by"));
        Assert.assertTrue(EmailCheck.validateEmail("email5@asd.com"));
        Assert.assertTrue(EmailCheck.validateEmail("e77777@asd.com"));
        Assert.assertTrue(EmailCheck.validateEmail("e77777@a8sd.com"));
        Assert.assertTrue(EmailCheck.validateEmail("email5-email5@a8sd.com"));
        Assert.assertTrue(EmailCheck.validateEmail("email5.email5@a8sd.com"));
    }

    @Test
    public void testNegativeCheck() {
        Assert.assertFalse(EmailCheck.validateEmail("email@.asd.com"));
        Assert.assertFalse(EmailCheck.validateEmail("email@asd.com."));
        Assert.assertFalse(EmailCheck.validateEmail("ema@il@asd.com"));
        Assert.assertFalse(EmailCheck.validateEmail("em@ail@asd.co@m"));
        Assert.assertFalse(EmailCheck.validateEmail("email-email-email@asd.co@m"));
        Assert.assertFalse(EmailCheck.validateEmail("email.email-email@asd.co@m"));
        Assert.assertFalse(EmailCheck.validateEmail("email-email.email@asd.co@m"));
        Assert.assertFalse(EmailCheck.validateEmail("email.email.email@asd.co@m"));
    }
}