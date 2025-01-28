import java.util.Scanner;

public class Lebron {
    public static void main(String[] args) {
        String lineBreak = "____________________________________________________________";
        System.out.println("Hello! I'm Lebron, just a little kid from Akron");
        System.out.println("What we doing today coach?");
        System.out.println(lineBreak);

        String line;
        while(true) {
            Scanner input = new Scanner(System.in);
            line = input.nextLine();
            if (line.equals("bye")) {
                System.out.println("Aight catch you later, see you at the game!");
                break;
            }
            System.out.println(line);
            System.out.println(lineBreak);
        }
    }
}
