import java.util.Scanner;

public class Lebron {
    private static final String LINEBREAK = "____________________________________________________________";
    private static Task[] tasks = new Task[100];
    private static int tasksSize = 0;

    public static void greetingMessage() {
        System.out.println("Hello! I'm Lebron, just a little kid from Akron");
        System.out.println("What we doing today coach?");
        System.out.println(LINEBREAK);
    }

    public static void addTask(String task) {
        tasks[tasksSize] = new Task(task);
        tasksSize++;
        System.out.println("Alright! Added " + task);
        System.out.println(LINEBREAK);
    }

    public static void printIcon(String icon) {
        System.out.print("[" + icon + "]");
    }

    public static void markTask(int idx) {
        if (tasks[idx - 1] == null) {
            System.out.println("Sorry! That's a foul move, you gotta pick another one");
            System.out.println(LINEBREAK);
            return;
        }
        tasks[idx - 1].setStatus(true);
        System.out.println("Done and dusted, you're gonna shatter my records in no time!");
        String status = tasks[idx - 1].getStatusIcon();
        printIcon(status);
        System.out.println(" " + tasks[idx - 1].getDescription());
        System.out.println(LINEBREAK);
    }

    public static void unmarkTask(int idx) {
        if (tasks[idx - 1] == null) {
            System.out.println("Sorry! That's a foul move, you gotta pick another one");
            System.out.println(LINEBREAK);
            return;
        }
        tasks[idx - 1].setStatus(false);
        System.out.println("I ain't worried, we'll clear that soon!");
        String status = tasks[idx - 1].getStatusIcon();
        printIcon(status);
        System.out.println(" " + tasks[idx - 1].getDescription());
        System.out.println(LINEBREAK);
    }

    public static void printTasks() {
        for (int i = 0; i < tasksSize; i++) {
            String status = tasks[i].getStatusIcon();
            System.out.print(i+1 + ". ");
            printIcon(status);
            System.out.println(" " + tasks[i].getDescription());
        }
        System.out.println(LINEBREAK);
    }

    public static void main(String[] args) {
        greetingMessage();
        Scanner input = new Scanner(System.in);
        while(true) {
            String line = input.nextLine();
            String[] words = line.split(" ");
            if (line.equals("bye")) {
                System.out.println("Aight catch you later, see you at the game!");
                System.out.println(LINEBREAK);
                break;
            } else if (line.equals("list")) {
                printTasks();
            } else if (words[0].equals("mark")) {
                int idx = Integer.parseInt(words[1]);
                markTask(idx);
            } else if (words[0].equals("unmark")) {
                int idx = Integer.parseInt(words[1]);
                unmarkTask(idx);
            } else {
                addTask(line);
            }
        }
    }
}
