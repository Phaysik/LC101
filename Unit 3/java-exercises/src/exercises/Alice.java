package exercises;
import java.util.Scanner;

public class Alice {

    public static void main(String[] args) {
        String sentence = "Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do: once or twice she had peeped into the book her sister was reading, but it had no pictures or conversations in it, 'and what is the use of a book,' thought Alice 'without pictures or conversation?'";
        System.out.println(sentence);

        Scanner mph = new Scanner(System.in);
        System.out.println("Please enter a term to search within the sentence above: ");
        String term = mph.nextLine();

        System.out.println(String.format("If the sentence contains the search term: %s.", sentence.toLowerCase().contains(term.toLowerCase())));
    }
}
