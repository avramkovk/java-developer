public class PrintFibonacciNumbersWithRecursion {
    public static void main(String[] args) {
        printAllNFibonacci(10, 0, 1);
        System.out.println();
        printOnlyValueOfNElementFibonacci(10, 0, 1);
    }

    private static void printAllNFibonacci(int num, int a, int b) {
        if (num == 0) {
            return;
        }
        System.out.printf("%s ", a);
        num--;
        printAllNFibonacci(num, b, b + a);
    }

    private static void printOnlyValueOfNElementFibonacci(int num, int a, int b) {
        if (num == 1) {
            System.out.println(a);
            return;
        }
        num--;
        printOnlyValueOfNElementFibonacci(num, b, b + a);
    }
}
