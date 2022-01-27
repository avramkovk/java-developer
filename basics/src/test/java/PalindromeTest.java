import org.junit.Assert;
import org.junit.Test;

public class PalindromeTest {
    @Test
    public void testPositiveIsPalindrome() {
        Assert.assertTrue(Palindrome.isPalindrome("Ada"));
        Assert.assertTrue(Palindrome.isPalindrome("ABCba"));
        Assert.assertTrue(Palindrome.isPalindrome("12321"));
        Assert.assertTrue(Palindrome.isPalindrome("Ada;da"));
        Assert.assertTrue(Palindrome.isPalindrome(" A da; da "));
        Assert.assertTrue(Palindrome.isPalindrome("Able was I ere I saw Elba"));
        Assert.assertTrue(Palindrome.isPalindrome("A man, a plan, a canal – Panama"));
        Assert.assertTrue(Palindrome.isPalindrome("Madam, I'm Adam"));
        Assert.assertTrue(Palindrome.isPalindrome("Never odd or even"));
        Assert.assertTrue(Palindrome.isPalindrome("Doc, note: I dissent. A fast never prevents a fatness. I diet on cod"));
        Assert.assertTrue(Palindrome.isPalindrome("T. Eliot, top bard, notes putrid tang emanating, is sad; I'd assign it a name: gnat dirt upset on drab pot toilet."));

    }

    @Test
    public void testNegativeIsPalindrome() {
        Assert.assertFalse(Palindrome.isPalindrome("book"));
        Assert.assertFalse(Palindrome.isPalindrome("12324"));
    }
}
