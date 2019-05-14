package studios;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 *  \author    Matthew Moore
 *  \date      13-05-2019
 *  \version   1.0.0
 *  \brief     Print area of circle.
 *  \details   Based on user input, validate if incorrect value inputted, and then print out area of a circle.
 */
public class Area {

    /**
     * Main method of Area Class.
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        Scanner rad = new Scanner(System.in); /** Scanner rad => Declare a scanner to get user input */
        System.out.println("Please enter in the radius of the circle: ");
        String input = rad.nextLine().trim(); /** String input => Get the user input */
        double radius; /** double radius => For converting the value of the input String into a double type */
        while (true) {
            try {
                radius = Double.parseDouble(input);
                if (radius < 0) {
                    System.out.println("Error. Your input was not positive, re-enter: ");
                    input = rad.nextLine().trim();
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error. Your input was not a number, re-enter: ");
                input = rad.nextLine().trim();
            }
        }

        NumberFormat nf = new DecimalFormat("##.###"); /** NumberFormat nf => Variable to format later numeric types */

        System.out.println(String.format("The area of a circle of radius %s is %s.", nf.format(radius), nf.format(Math.PI * Math.pow(radius, 2.0))));
    }
}
