package studios;

import java.util.HashMap;
import java.util.Scanner;

/**
 *  \author    Matthew Moore
 *  \date      16-05-2019
 *  \version   1.0.5
 *  \brief     Count characters from input
 *  \details   Based on user input, count all the characters from the string.
 */
public class CountingCharacters {

    /**
     * Main method of CountingCharacters Class
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); /** Scanner input => Declare a scanner to get user input */
        HashMap<Character, Integer> counts = new HashMap<>(); /** HashMap<Character, Integer> counts=> A hashmap that contains the characters and the amount of times they appear in the user's input */

        System.out.print("Please enter in a sentence: ");
        String sentence = input.nextLine(); /** String setence => String to hold user input */

        for (char character : sentence.toCharArray()) {
            if (Character.isLetter(character)) {
                Integer counter = counts.get(Character.toLowerCase(character));
                if (counter == null) {
                    counts.putIfAbsent(Character.toLowerCase(character), 1);
                } else {
                    counts.put(Character.toLowerCase(character), counter + 1);
                }
            }
        }

        System.out.println("The amount of times each character appears: ");
        counts.entrySet().forEach(n -> System.out.println(String.format("%s:\t%s", n.getKey(), n.getValue())));
    }
}
