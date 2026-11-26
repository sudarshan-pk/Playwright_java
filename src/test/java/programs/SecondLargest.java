package programs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SecondLargest {

    static void main() {
        int[] arr = {1, 2, 3, 4, 5};

        List<Integer> numbers = new ArrayList<>();
        for(int num: arr) {
            numbers.add(num);
        }
        System.out.println("numbers: " + numbers);

        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("sorted numbers: " + numbers);
        System.out.println("second largest: " + numbers.get(1));
    }
}
