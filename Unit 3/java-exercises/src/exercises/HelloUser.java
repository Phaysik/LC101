package exercises;
import java.util.Scanner;

/**
 *  \author    Matthew Moore
 *  \date      13-05-2019
 *  \version   1.0.0
 *  \brief     Print hello 'User'.
 *  \details   Based on user input, say 'Hello "Name"'.
 */
public class HelloUser {

    /**
     * Main method of HelloUser Class
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        Scanner name = new Scanner(System.in); /** Scanner name => Declare a scanner to get user input */
        System.out.println("Please enter in your name: ");

        String fullName = name.nextLine(); /** String fullName => User input of their name */
        System.out.println(String.format("Hello %s!", fullName));
    }
}
