package programs;

import java.util.LinkedHashMap;

public class Frequency {

    public static void main(String[] args) {
        charFrequency();
        wordFrequency();
    }

    private static void wordFrequency() {

        String sentence = "This is a sample sentence. This sentence is for testing word frequency.";
        String[] words = sentence.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        LinkedHashMap<String, Integer> wordMap = new LinkedHashMap<>();
        for (String w : words) {
            if (wordMap.get(w) == null) {
                wordMap.put(w, 1);
            } else {
                wordMap.put(w, wordMap.get(w) + 1);
            }
        }
        System.out.println("Word frequency map: " + wordMap);
    }

    private static void charFrequency() {
        String word = "Find the first non-repeating character in a string.";
        String words = word.replaceAll(" ", "").replace("-", "").replace(".", "").toLowerCase();

        LinkedHashMap<Character, Integer> charMap = new LinkedHashMap<>();
        for (int i = 0; i < words.length(); i++) {
            char c = words.charAt(i);
            if (charMap.get(c)== null) {
                charMap.put(c, 1);
            } else {
                charMap.put(c, charMap.get(c) + 1);
            }
        }

        System.out.println("Character frequency map: " + charMap);

        System.out.println("Values in the map: " + charMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .toList());
        for (Character c : charMap.keySet()) {
            if (charMap.get(c) == 1) {
                System.out.println("First non-repeating character: " + c);
                break;
            }
        }
    }
}
