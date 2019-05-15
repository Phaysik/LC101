package exercises;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  \author    Matthew Moore
 *  \date      15-05-2019
 *  \version   1.0.4
 *  \brief     Get sum of even numbers from list.
 *  \details   Based on a pre-determined ArrayList, call a static method that will return the sum of all the even numbers in the list.
 */
public class SumList {

    /**
     * Main method of SumList Class
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)); /** ArrayList<Integer> values => Holds all the numbers from 1-10 */
        System.out.println(String.format("The sum of all values in the list is: %s", sumVals(values)));
    }

    /**
     * Will return a summed value of all the even numbers in an ArrayList
     * \param[array] An ArrayList of type Integer
     * \retval <int> Returns the sum of all even numbers
     */
    private static int sumVals(ArrayList<Integer> array) {
        array.removeIf(n -> !(n % 2 == 0));
        return array.stream().mapToInt(a -> a).sum();
    }
}
