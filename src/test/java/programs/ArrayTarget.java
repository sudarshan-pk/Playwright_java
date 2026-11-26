package programs;

public class ArrayTarget {


    static void main() {

        int[] arr = {1, 2, 3, 4, 5};
        int target = 15;
        isTargetSum(arr, target);

    }

    static void isTargetSum(int[] arr, int target) {

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        System.out.println("sum : " + sum);
        if (sum == target) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    }
}
