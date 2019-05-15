package exercises;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  \author    Matthew Moore
 *  \date      15-05-2019
 *  \version   1.0.4
 *  \brief     Print five letter words.
 *  \details   Based on a pre-determined ArrayList, call a static method that will print the words whose length equals five.
 */
public class FiveLetters {

    /**
     * Main method of FiveLetters Class
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        ArrayList<String> values = new ArrayList<>(Arrays.asList("John", "Wilbanks", "Doughnut", "Taco", "Hands", "Meat", "Chips")); /** ArrayList<Integer> values => Holds a group of words */
        PrintWords(values);
    }

    /**
     * Will print all words that have the length of five
     * \param[array] An ArrayList of type String
     */
    private static void PrintWords(ArrayList<String> array) {
        array.removeIf(s -> s.length() != 5);
        System.out.println("The words with five letters are: ");
        array.forEach(n -> System.out.println(n));
    }
}
