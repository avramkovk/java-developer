import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrintNumbersWithRecursionTest {

    @Test
    public void testRecursionFrom1ToN() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        PrintNumbersWithRecursion.recursionFrom1ToN(5);
        Assert.assertEquals("1 2 3 4 5 ", output.toString());
    }

    @Test
    public void testRecursionFromNTo1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        PrintNumbersWithRecursion.recursionFromNTo1(5);
        Assert.assertEquals("5 4 3 2 1 ", output.toString());
    }
}
