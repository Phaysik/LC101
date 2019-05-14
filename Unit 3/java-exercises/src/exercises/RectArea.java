package exercises;
import java.util.Scanner;

/**
 *  \author    Matthew Moore
 *  \date      13-05-2019
 *  \version   1.0.0
 *  \brief     Get area of Rectangle.
 *  \details   Based on user input, get the height and width of a rectangle, then print out the area.
 */
public class RectArea {

    /**
     * Main method of RectArea Class
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        Scanner dimension = new Scanner(System.in); /** Scanner dimension => Declare a scanner to get user input */
        System.out.println("Please enter in the length of the rectangle: ");
        int rectLength = Integer.parseInt(dimension.nextLine()); /** int rectLength => From user input, get the length of the rectangle */

        System.out.println("Please enter in the height of the rectangle: ");
        int rectHeight = Integer.parseInt(dimension.nextLine()); /** int rectHeight => From user input, get the height of the rectangle */

        System.out.println(String.format("The area of the rectangle is %s.", (rectLength * rectHeight)));
    }
}
