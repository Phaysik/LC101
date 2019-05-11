package exercises;
import java.util.Scanner;

public class HelloUser {

    public static void main(String[] args) {
        Scanner name = new Scanner(System.in);
        System.out.println("Please enter in your name: ");

        String fullName = name.nextLine();
        System.out.println(String.format("Hello %s!", fullName));
    }
}
