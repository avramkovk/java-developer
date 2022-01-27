

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*Write the method so that it splits the string into pairs of two characters.
 If the string contains an odd (1, 3, 5…) number of characters then it should replace the missing second character
 of the final pair with an underscore ('_').

 Examples:

 solution("abc") // should return {"ab", "c_"}
 solution("abcdef") // should return {"ab", "cd", "ef"}*/

public class SplitStringByPairs {

    public static void main(String[] args) {
        String inputString;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите строку: ");
            inputString = scanner.nextLine();
            if (inputString.isEmpty()) {
                break;
            }
            System.out.println(solution(inputString));
        }
        scanner.close();
    }

    public static List<String> solution(String s) {
        List<String> list = new ArrayList<>();
        final int LIST_LENGTH = s.length();

        for (int i = 0; i <= LIST_LENGTH - 2; i = i + 2) {
            list.add(s.substring(i, i + 2));
        }

        if (LIST_LENGTH % 2 != 0) {
            list.add(s.substring(LIST_LENGTH - 1) + "_");
        }
        return list;
    }
}