package exercises;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *  \author    Matthew Moore
 *  \date      15-05-2019
 *  \version   1.0.4
 *  \brief     Print values from array
 *  \details   Based on a pre-determined Array, print out all values of the array.
 */
public class PrintArray {

    /**
     * Main method of PrintArray Class
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        int values[] = new int[] {1, 1, 2, 3, 5, 8}; /** int Array values => Holds a set of numbers */

        Arrays.stream(values).boxed().collect(Collectors.toList()).forEach(n -> System.out.println(n));
    }
}
