package demos;

/**
 *  \author    Matthew Moore
 *  \date      13-05-2019
 *  \version   1.0.0
 *  \brief     Class 1 of Unit 3.
 *  \details   Practice with integer arrays.
 */
public class Practice {

    /**
     * Main method of Practice Class.
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 5, 6}; /** int[] a1 => Array with an initialized set of values */
        int[] a2 = new int[2]; /** int[] a2 => Array size initialized (all values are 0) */
        a2[0] = 7;
        a2[1] = 19;

        int result = firstAndLastSum(a1); /** int result => Value to hold the returned value from the function \link firstAndLastSum \endlink */

        System.out.println(result);
    }

    /**
     * Sum the first and last element of an int array.
     * @param[array] A populated integer array.
     * \retval <int> Returns the sum of the first and last element on the array.
     */
    public static int firstAndLastSum(int[] array) {
        int first = array[0]; /** int first => First element of the passed array */
        int last = array[array.length - 1]; /** int last => Last element of the passed array */
        return first + last;
    }
}
