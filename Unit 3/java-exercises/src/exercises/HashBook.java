package exercises;

import java.util.HashMap;
import java.util.Scanner;

/**
 *  \author    Matthew Moore
 *  \date      15-05-2019
 *  \version   1.0.4
 *  \brief     Print roster from user input
 *  \details   Based on user input, take in a String for the student's name, an Integer for their ID, and then print out the roster.
 */
public class HashBook {

    /**
     * Main method of HashBook Class
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); /** Scanner input => Declare a scanner to get user input */
        HashMap<Integer, String> students = new HashMap<>(); /** HashMap<Integer, String> students => A hashmap that contains the student's names and their ids */
        String name; /** String name => String to hold the student's name */
        String test; /** String test => String to take placeholder input */
        int id; /** int id => Integer to hold the student's id */

        do {
            System.out.print("Student (Hit ENTER to stop adding students): ");
            name = input.nextLine();
            if (!name.equals("")) {
                while (true) {
                    try {
                        System.out.print("ID: ");
                        test = input.nextLine().trim();
                        id = Integer.parseInt(test);
                        if (id < 0) {
                            System.out.print("Error. Your input was not positive, re-enter ");
                            continue;
                        } else {
                            students.put(id, name);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Error. Your input was not a number, re-enter: ");
                    }
                }
            }
        } while(!name.equals(""));

        students.entrySet().forEach(n -> System.out.println(String.format("Student: %s\tId: %s", n.getValue(), n.getKey())));
    }
}
