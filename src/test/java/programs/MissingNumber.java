package programs;

public class MissingNumber {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 6, 7, 9, 10};
        int missingNumber = findMissingNumber(arr);
        System.out.println("missing number: " + missingNumber);
    }

    static int findMissingNumber(int[] arr) {
        int n = arr.length + 1; // since one number is missing
        int expectedSum = n * (n + 1) / 2;

        System.out.println("expectedSum: " + expectedSum);
        int actualSum = 0;
        for (int num : arr) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }
}
