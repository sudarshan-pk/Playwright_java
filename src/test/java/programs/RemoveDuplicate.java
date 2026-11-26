package programs;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class RemoveDuplicate {

    public static void main(String[] args) {

        removeDuplicateChar();
        removeDuplicateInt();
    }

    private static void removeDuplicateInt() {

        List<Integer> numberList = Arrays.asList(2, 1, 2, 3);
        LinkedHashSet<Integer> numberSet = new LinkedHashSet<>(numberList);
        System.out.println("numberSet: " + numberSet);
    }

    private static void removeDuplicateChar() {
        String str = "banana";
        char[] chars = str.toCharArray();

        LinkedHashSet<Character> charSet = new LinkedHashSet<>();

        for (char c : chars) {
            charSet.add(c);
        }

        System.out.println("charSet: " + charSet);
        String distinctStr = "";
        for (char c : charSet) {
            distinctStr += c;
        }
        System.out.println("distinctStr: " + distinctStr);
    }
}
