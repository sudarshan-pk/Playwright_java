package programs;

public class ReverseWord {

    static void main() {
        String word = "Reverse the words in a sentence";

       reverseWords(word);
       reverseSentence(word);
    }

    private static void reverseSentence(String word) {
        String[] words = word.split(" ");
        StringBuilder reversedSentence = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]).append(" ");
        }
        System.out.println(reversedSentence);
    }

    private static void reverseWords(String word) {

        StringBuilder reversed = new StringBuilder(word);
        reversed.reverse();
        System.out.println(reversed);
    }

}
