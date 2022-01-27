import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        System.out.println((isPalindrome(string)));
    }

    public static boolean isPalindrome(String string) {
        String preparedString = string.toLowerCase().replaceAll("[\\W_]+", "");
        String[] letters = preparedString.split("");
        int halfLengthLetters = letters.length / 2;
        for (int i = 0; i < letters.length; i++) {
            if (!letters[i].equals(letters[letters.length - 1 - i])) {
                return false;
            }
            if (i == halfLengthLetters) {
                break;
            }
        }
        return true;
    }
}