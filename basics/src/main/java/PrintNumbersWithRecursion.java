public class PrintNumbersWithRecursion {
    public static void main(String[] args) {
        recursionFromNTo1(5);
        System.out.println();
        recursionFrom1ToN(5);
    }


    public static void recursionFromNTo1(int n) {
        if (n == 0) {
            return;
        }
        System.out.print(n + " ");
        recursionFromNTo1(n - 1);
    }

    public static void recursionFrom1ToN(int n) {
        if (n == 0) {
            return;
        }
        recursionFrom1ToN(n - 1);
        System.out.print(n + " ");
    }
}
