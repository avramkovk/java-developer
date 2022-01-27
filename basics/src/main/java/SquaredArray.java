/*Valid arrays
        a = [121, 144, 19, 161, 19, 144, 19, 11]
        b = [121, 14641, 20736, 361, 25921, 361, 20736, 361]

        comp(a, b) returns true because in b 121 is the square of 11, 14641 is the square of 121, 20736
        the square of 144, 361 the square of 19, 25921 the square of 161, and so on.
        It gets obvious if we write b's elements in terms of squares:

        a = [121, 144, 19, 161, 19, 144, 19, 11]
        b = [11*11, 121*121, 144*144, 19*19, 161*161, 19*19, 144*144, 19*19]

        Invalid arrays
        If we change the first number to something else, comp may not return true anymore:

        a = [121, 144, 19, 161, 19, 144, 19, 11]
        b = [132, 14641, 20736, 361, 25921, 361, 20736, 361]

        comp(a,b) returns false because in b 132 is not the square of any number of a.

        a = [121, 144, 19, 161, 19, 144, 19, 11]
        b = [121, 14641, 20736, 36100, 25921, 361, 20736, 361]

        comp(a,b) returns false because in b 36100 is not the square of any number of a.

        Remarks
        Numbers in array a might be negative.
        a or b might be []
        a or b might be null

        If a or b are null, the problem doesn't make sense so return false.*/


public class SquaredArray {

    public static void main(String[] args) {
        int[] array1 = {121, 144, 19, 161, 19, 144, 19, 11}; //8 элементов
        int[] array2 = {121, 14641, 20736, 361, 25921, 361, 20736, 361}; //8 элементов
        int[] array3 = {-121, 14641, 20736, 361, 25921, 3601, 20736, 361}; //8 элементов
        int[] array4 = {-121, 14641, 20736, 361, 25921, 3601, 20736, 361, 1}; //9 элементов
        int[] array5 = null;

        System.out.println("Сравнение с положительными числами: " + compareArray(array1, array2));
        System.out.println("Сравнение с отрицательными числами: " + compareArray(array1, array3));
        System.out.println("Сравнение с разным количеством элементов: " + compareArray(array1, array4));
        System.out.println("Сравнение с null: " + compareArray(array4, array5));
    }

    public static boolean compareArray(int[] a, int[] b) {
        if (a == null || b == null) { //1. если массивы пустые -> false
            return false;
        }
        if (a.length != b.length) { //2. если длины разные -> false
            return false;
        }
        for (int i = 0; i < b.length; i++) {
            if (!arrayContains(a, b[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean arrayContains(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] * arr[i] == value) {
                return true;
            }
        }
        return false;
    }
}
