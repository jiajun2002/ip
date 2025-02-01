import java.util.Scanner;

public class Lebron {
    private static final String LINEBREAK = "____________________________________________________________";
    private static String[] tasks = new String[100];
    private static int tasksSize = 0;

    public static void greetingMessage() {
        System.out.println("Hello! I'm Lebron, just a little kid from Akron");
        System.out.println("What we doing today coach?");
        System.out.println(LINEBREAK);
    }

    public static void addTask(String task) {
        tasks[tasksSize] = task;
        tasksSize++;
        System.out.println("Alright! Added " + task);
        System.out.println(LINEBREAK);
    }

    public static void printTasks() {
        for (int i = 0; i < tasksSize; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println(LINEBREAK);
    }

    public static void main(String[] args) {
        greetingMessage();
        while(true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if (line.equals("bye")) {
                System.out.println("Aight catch you later, see you at the game!");
                break;
            } else if (line.equals("list")) {
                printTasks();
            } else {
                addTask(line);
            }
        }
    }
}
