package exercises;
import java.util.Scanner;

public class RectArea {

    public static void main(String[] args) {
        Scanner dimension = new Scanner(System.in);
        System.out.println("Please enter in the length of the rectangle: ");
        int rectLength = Integer.parseInt(dimension.nextLine());

        System.out.println("Please enter in the height of the rectangle: ");
        int rectHeight = Integer.parseInt(dimension.nextLine());

        System.out.println(String.format("The area of the rectangle is %s.", (rectLength * rectHeight)));
    }
}
