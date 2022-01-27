import java.util.Arrays;

public class SortTheOdd {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(sortTheOdd(arr)));
    }

    public static int[] sortTheOdd(int[] array) {
        int countOdd = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                countOdd++;
            }
        }

        int[] values = new int[countOdd];
        int[] indexes = new int[countOdd];

        int i = 0;
        for (int j = 0; j < array.length; j++) {
            if (array[j] % 2 == 1) {
                values[i] = array[j];
                indexes[i] = j;
                i++;
            }
        }

        Arrays.sort(values);

        for (int j = 0; j < countOdd; j++) {
            array[indexes[j]] = values[j];
        }

        return array;
    }
}