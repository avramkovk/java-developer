import java.util.ArrayList;
import java.util.List;

public class ReverseNumber {

    public static int reverse(int number) {
        List<Integer> numbers = new ArrayList<>();
        boolean negative = number < 0;
        int negativeSign = -1;
        if (negative) {
            number = negativeSign * number;
        }

        int minimalRemainder = 1;
        while (true) {
            if (number / 10 < minimalRemainder) {
                numbers.add(number);
                break;
            }
            int remainder = number % 10;
            numbers.add(remainder);
            number = (number - remainder) / 10;
        }

        int reverseNumber = 0;
        for (int i = 0; i < numbers.size(); i++) {
            reverseNumber = reverseNumber + numbers.get(i) * (int) Math.pow(10, numbers.size() - 1 - i);
        }
        if (negative) {
            return negativeSign * reverseNumber;
        }

        return reverseNumber;
    }
}