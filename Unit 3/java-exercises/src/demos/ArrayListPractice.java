package demos;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  \author    Matthew Moore
 *  \date      16-05-2019
 *  \version   1.1.0
 *  \brief     Print sum of two ArrayLists
 *  \details   Based on pre-determined ArrayLists, call a function that will add the values of the same position and then print out the sum of those positions.
 */
public class ArrayListPractice {

    /**
     * Main method of ArrayListPractice Class
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        ArrayList<Integer> values2 = new ArrayList<>(Arrays.asList(3, 4, 5, 1));
        ArrayList<Integer> values1 = new ArrayList<>(Arrays.asList(1, 6));
        addLists(values1, values2).forEach(n -> System.out.print(n + " "));
    }

    /**
     * Will return a summed value of all the even numbers in an ArrayList
     * \param[list1] An ArrayList of type Integer
     * \param[list2] A second ArrayList of type Integer
     * \retval ArrayList<Integer> Returns the sum of all even numbers
     */
    public static ArrayList<Integer> addLists(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> results = new ArrayList<>();

        ArrayList<Integer> smaller = list1.size() > list2.size() ? list2 : list1;
        ArrayList<Integer> larger = list1.size() > list2.size() ? list1 : list2;

        for(int i = 0; i < smaller.size(); i++) {
            results.add(smaller.get(i) + larger.get(i));
        }

        for(int i = smaller.size(); i < larger.size(); i++) {
            results.add(larger.get(i));
        }

        return results;
    }
}
