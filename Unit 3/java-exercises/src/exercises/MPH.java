package exercises;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 *  \author    Matthew Moore
 *  \date      13-05-2019
 *  \version   1.0.0
 *  \brief     Determine miles per gallon.
 *  \details   Based on user input, get the miles traveled, gallons consumed, and print out their mph.
 */
public class MPH {

    /**
     * Main method of MPH Class
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {
        Scanner mph = new Scanner(System.in); /** Scanner mph => Declare a scanner to get user input */
        System.out.println("Please enter in your miles driven: ");
        int miles = Integer.parseInt(mph.nextLine()); /** int miles => From user input, get their miles traveled */

        System.out.println("Please enter in the amount of gas consumed (in gallons): ");
        int gallons = Integer.parseInt(mph.nextLine()); /** int gallons => From user input, get their gallons consumed */

        NumberFormat nf = new DecimalFormat("##.##"); /** NumberFormat nf => Variable to format later numeric types */

        System.out.println(String.format("Your miles-per-gallon ratio is %s.", nf.format((double) miles / (double) gallons)));
    }
}