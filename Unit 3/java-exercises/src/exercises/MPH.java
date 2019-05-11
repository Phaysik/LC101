package exercises;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class MPH {

    public static void main(String[] args) {
        Scanner mph = new Scanner(System.in);
        System.out.println("Please enter in your miles driven: ");
        int miles = Integer.parseInt(mph.nextLine());

        System.out.println("Please enter in the amount of gas consumed (in gallons): ");
        int gallons = Integer.parseInt(mph.nextLine());

        NumberFormat nf = new DecimalFormat("##.##");

        System.out.println(String.format("Your miles-per-gallon ratio is %s.", nf.format((double) miles / (double) gallons)));
    }
}
