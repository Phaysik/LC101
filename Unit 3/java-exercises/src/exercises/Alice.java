package exercises;
import java.util.Scanner;

/**
 *  \author    Matthew Moore
 *  \date      13-05-2019
 *  \version   1.0.0
 *  \brief     Determine case insensitive substring.
 *  \details   Based on user input, determine if their search term(s) are contained within the first sentence of Alice in Wonderland.
 */
public class Alice {

    /**
     * Main method of Alice Class
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        String sentence = "Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do: once or twice she had peeped into the book her sister was reading, but it had no pictures or conversations in it, 'and what is the use of a book,' thought Alice 'without pictures or conversation?'"; /** String sentece => The first sentence of Alice in Wonderland */
        System.out.println(sentence);

        Scanner search = new Scanner(System.in); /** Scanner search => Declare a scanner to get user input */
        System.out.println("Please enter a term to search within the sentence above: ");
        String term = search.nextLine(); /** String term => The user's search term(s) */

        System.out.println(String.format("If the sentence contains the search term: %s.", sentence.toLowerCase().contains(term.toLowerCase())));
    }
}
