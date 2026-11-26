package programs;

public class Questions {


    static void main() {
//        addTwoNumbers();
        sumArray();
    }

    static void addTwoNumbers() {
        int a = 10;
        int b = 20;
        int c;
        System.out.println("Before swapping: a = " + a + ", b = " + b);
        c = a;
        a = b;
        b = c;
        System.out.println("After swapping: a = " + a + ", b = " + b);
    }

    static void sumArray(){
        int[] arr = {1, 2, 3, 4, 5, 6};
       int len = arr.length;
       int sum =0;
       for (int i =0;i<len;i++){
           sum+=arr[i];
       }
         System.out.println("Sum of array elements: " + sum);
    }

    static void largestValue(){

        int[] arr = {5, 2, 7, 1, 4} ;
        int len = arr.length;
        int largest = arr[0];

        for(int i=1; i<len; i++){
            if(arr[i]>largest){
                largest = arr[i];
            }
        }
        System.out.println("Largest value in array: " + largest);

    }
}
