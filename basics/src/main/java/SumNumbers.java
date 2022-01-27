public class SumNumbers {

    public static int findIndexOfEqualSums(int[] array) {
        int indexOfLastElement = array.length - 1;
        int sumLeft = 0;
        int sumRight = 0;
        int count = 0;
        int result = 0;

        for (int i = 0; i < array.length; i++) {
            while (count < i) {
                sumLeft = sumLeft + array[count];
                count++;
            }

            count = i + 1;

            while (count < array.length) {
                sumRight = sumRight + array[count];
                count++;
            }

            if (i == indexOfLastElement && sumLeft != sumRight) {
                return -1;
            }

            if (sumLeft == sumRight) {
                result = i;
                break;
            }
            count = 0;
            sumLeft = 0;
            sumRight = 0;
        }
        return result;
    }
}
