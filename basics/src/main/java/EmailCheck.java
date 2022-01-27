import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String email;

        while (true) {
            email = scanner.next();
            if (email.isEmpty()) {
                break;
            }
            if (validateEmail(email)) {
                System.out.println("Email is valid");
            } else {
                System.out.println("Email is invalid");
            }
        }
        scanner.close();
    }

    public static boolean validateEmail(String email) {
        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]+[-.]?[a-zA-Z0-9]+@{1}[a-zA-Z0-9]+[.]{1}[a-zA-Z]{2,3}");
        Matcher m = p.matcher(email);
        return m.matches();
    }
}