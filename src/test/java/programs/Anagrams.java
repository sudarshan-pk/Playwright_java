package programs;

import java.util.HashMap;
import java.util.Map;

public class Anagrams {



    static void main() {
        String word1 = "Listens";
        String word2 = "Silents";
        isAnagram(word1, word2);
    }


    static void isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()) {
            System.out.println(word1 + " and " + word2 + " are not anagrams.");
            return;
        }
        // Additional logic for checking anagrams can be added here

        Map<Character, Integer> charCountMap = new HashMap<>();
        Map<Character, Integer> charCountMap2 = new HashMap<>();


        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        System.out.println("Word 1: " + word1);
        System.out.println("Word 2: " + word2);

        for (char c : word1.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        for (char c : word2.toCharArray()) {
            charCountMap2.put(c, charCountMap2.getOrDefault(c, 0) + 1);
        }

        System.out.println(charCountMap);
        System.out.println(charCountMap2);

        if (charCountMap.equals(charCountMap2)) {
            System.out.println(word1 + " and " + word2 + " are anagrams.");
        } else {
            System.out.println(word1 + " and " + word2 + " are not anagrams.");
        }
    }

}