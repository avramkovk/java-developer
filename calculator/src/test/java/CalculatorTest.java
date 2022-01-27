import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void testCalcOnlyPlusAndMinus() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(4.0, calculator.getSolution("2+2"), 0);
        Assert.assertEquals(4.0, calculator.getSolution("2+2 "), 0);
        Assert.assertEquals(4.0, calculator.getSolution(" 2+2"), 0);
        Assert.assertEquals(0, calculator.getSolution("2-2"), 0);
        Assert.assertEquals(-2.0, calculator.getSolution("-1-1"), 0);
        Assert.assertEquals(-2.0, calculator.getSolution("-(1+1)"), 0);
        Assert.assertEquals(2.0, calculator.getSolution("-(-1-1)"), 0);
        Assert.assertEquals(24.5, calculator.getSolution("22+2.5"), 0);
        Assert.assertEquals(-24.5, calculator.getSolution("-22-2.5"), 0);
        Assert.assertEquals(-5.0, calculator.getSolution("-1-1-1-1-1"), 0);
        Assert.assertEquals(11.0, calculator.getSolution("2.2+2.2+2.2+2.2+2.2"), 0);
        Assert.assertEquals(2.0, calculator.getSolution("(100+1)-(100-1)"), 0);
        Assert.assertEquals(202.0, calculator.getSolution("-(-100-1)-(-100-1)"), 0);
    }

    @Test
    public void testCalcOnlyMultiplyAndDivide() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(4.0, calculator.getSolution("2*2"), 0);
        Assert.assertEquals(4.0, calculator.getSolution("-2*-2"), 0);
        Assert.assertEquals(4.41, calculator.getSolution("2.1*2.1"), 0);
        Assert.assertEquals(4.41, calculator.getSolution("-2.1*-2.1"), 0);
        Assert.assertEquals(1.0, calculator.getSolution("20.5/5/4.1"), 0);
        Assert.assertEquals(-1.0, calculator.getSolution("-20.5/-5/-4.1"), 0);
        Assert.assertEquals(-0.5, calculator.getSolution("1/-2"), 0);
        Assert.assertEquals(-2.0, calculator.getSolution("1/-0.5"), 0);
        Assert.assertEquals(2.0, calculator.getSolution("-4/-2"), 0);
        Assert.assertEquals(3.0, calculator.getSolution("-4.5/-1.5"), 0);
        Assert.assertEquals(-2.0, calculator.getSolution("-4/2"), 0);
    }

    @Test
    public void testCalcAllOperations() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(3.0, calculator.getSolution("1+(2+2)/2"), 0);
        Assert.assertEquals(-1.0, calculator.getSolution("1+(2+2)/-2"), 0);
        Assert.assertEquals(1.0, calculator.getSolution("-1+(2+2)/2"), 0);
        Assert.assertEquals(-2.5, calculator.getSolution("-1-1-0.5"), 0);
        Assert.assertEquals(6.5, calculator.getSolution("-2.5+(3/1+1)*2.5-1"), 0);
        Assert.assertEquals(6.5, calculator.getSolution("-2.5+(3*1+1)*2.5-1"), 0);
        Assert.assertEquals(-18.0, calculator.getSolution("-22+(-1-1)/-0.5"), 0);
        Assert.assertEquals(7.0, calculator.getSolution("-4/-2+0.5/0.1"), 0);
        Assert.assertEquals(-3.0, calculator.getSolution("-4/-2+5/-1"), 0);
        Assert.assertEquals(-2.0, calculator.getSolution("2/-2+4/-4"), 0);
        Assert.assertEquals(1.5, calculator.getSolution("1/(9-7)+1"), 0);
        Assert.assertEquals(3.0, calculator.getSolution("-1*1+(-1-1)/-1*2"), 0);
        Assert.assertEquals(0, calculator.getSolution("-1*1+(-1-1)/(-1*2)"), 0);
        Assert.assertEquals(-1.45, calculator.getSolution("-1.5*1.5+(-1.5-1.5)/(-1.5*2.5)"), 0);
    }
}