import org.junit.Assert;
import org.junit.Test;

public class IsogramsTest {

    @Test
    public void testPositiveIsIsogram() {
        Assert.assertTrue(Isograms.isIsogram(""));
        Assert.assertTrue(Isograms.isIsogram("H"));
        Assert.assertTrue(Isograms.isIsogram("h"));
        Assert.assertTrue(Isograms.isIsogram("mouse"));
        Assert.assertTrue(Isograms.isIsogram("Push"));
        Assert.assertTrue(Isograms.isIsogram("trOws"));
        Assert.assertTrue(Isograms.isIsogram("milK"));
        Assert.assertTrue(Isograms.isIsogram("стол"));
        Assert.assertTrue(Isograms.isIsogram("Михась"));
        Assert.assertTrue(Isograms.isIsogram("партия"));
        Assert.assertTrue(Isograms.isIsogram("картоН"));
        Assert.assertTrue(Isograms.isIsogram("Я"));
        Assert.assertTrue(Isograms.isIsogram("я"));
    }

    @Test
    public void testNegativeIsIsogram() {
        Assert.assertFalse(Isograms.isIsogram(" "));
        Assert.assertFalse(Isograms.isIsogram("aa"));
        Assert.assertFalse(Isograms.isIsogram("Aaron"));
        Assert.assertFalse(Isograms.isIsogram("mOOn"));
        Assert.assertFalse(Isograms.isIsogram("9milk"));
        Assert.assertFalse(Isograms.isIsogram("mile*"));
        Assert.assertFalse(Isograms.isIsogram(";[\\<>?:}{|+_)(*&^%$#@!~"));
    }
}