import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitStringByPairsTest {

    @Test
    public void testSolution() {
        String s1  = "a";
        String s2  = "abc";
        String s3  = "abcd";
        List<String> listS1 = new ArrayList<>(Arrays.asList("a_"));
        List<String> listS2 = new ArrayList<>(Arrays.asList("ab", "c_"));
        List<String> listS3 = new ArrayList<>(Arrays.asList("ab","cd"));

        Assert.assertEquals(listS1,SplitStringByPairs.solution(s1));
        Assert.assertEquals(listS2,SplitStringByPairs.solution(s2));
        Assert.assertEquals(listS3,SplitStringByPairs.solution(s3));
    }
}