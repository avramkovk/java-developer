import java.util.ArrayList;
import java.util.List;

public class ExpandTheNumber {
    public static void main(String[] args) {
        System.out.println(expandTheNumber(1));
        System.out.println(expandTheNumber(111));
        System.out.println(expandTheNumber(111_111));
        System.out.println(expandTheNumber(111_111_111));
    }

    public static String expandTheNumber(int num) {
        List<String> listValues = new ArrayList<>();
        while (num > 0) {
            listValues.add(String.valueOf(num - (num / 10)));
            num = num / 10;
        }
        return String.join(" + ", listValues);
    }
}