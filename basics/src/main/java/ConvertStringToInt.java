import java.util.Scanner;

public class ConvertStringToInt {
    private static final int ZERO_CHAR = 48;
    private static final int NINE_CHAR = 57;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String string = scanner.nextLine();
            if (string.equals("exit")) {
                return;
            }
            System.out.println(convertStringToInt(string));
        }
    }

    public static int convertStringToInt(String string) {
        if (!isNumber(string)) {
            throw new IllegalArgumentException("failed to convert string to int: " + string);
        }
        char[] chars = string.toCharArray();
        char firstChar = chars[0];
        String stringUnsigned = string.substring(1);
        if (firstChar == '-') {
            return -convert(stringUnsigned);
        }
        if (firstChar == '+') {
            return convert(stringUnsigned);
        }
        return convert(string);
    }

    private static boolean isNumber(String string) {
        if (string.isEmpty()) {
            return false;
        }

        boolean hasSign = string.charAt(0) == '+' || string.charAt(0) == '-';

        for (int i = hasSign ? 1 : 0; i < string.length(); i++) {
            if (string.charAt(i) < ZERO_CHAR || string.charAt(i) > NINE_CHAR) {
                return false;
            }
        }
        return true;
    }

    private static int convert(String string) {
        int number = 0;
        int countOfTens = string.length() - 1;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= ZERO_CHAR && string.charAt(i) <= NINE_CHAR) {
                number = (int) (number + (string.charAt(i) - ZERO_CHAR) * (Math.pow(10, countOfTens - i)));
            }
        }
        return number;
    }
}