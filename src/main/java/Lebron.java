import java.util.Scanner;

public class Lebron {
    private static final String LINE_BREAK = "____________________________________________________________";
    private static final int MAX_TASK_SIZE = 100;
    private static Task[] tasks = new Task[MAX_TASK_SIZE];
    private static int tasksSize = 0;

    public static void printLinebreak() {
        System.out.println(LINE_BREAK);
    }

    public static void printGreetingMessage() {
        System.out.println("Hello! I'm Lebron, just a little kid from Akron");
        System.out.println("What we doing today?");
        printLinebreak();
    }

    public static void printAddingMessage(Task[] tasks, int size) {
        System.out.println("Alright! Get ready to do this: ");
        System.out.println("--> " + tasks[size - 1]);
        System.out.println("Be confident in your ability! Current tasks: " + tasksSize);
        printLinebreak();
    }

    public static void printErrorMessage(String error){
        System.out.println("Sorry that's a foul! " + error);
        printLinebreak();
    }

    public static void addTodo(String todo) {
        String task = todo.substring(todo.indexOf(" ") + 1);
        tasks[tasksSize] = new Todo(task);
        tasksSize++;
        printAddingMessage(tasks, tasksSize);
    }

    public static void addDeadline(String deadline) {
        int split = deadline.indexOf("/by");
        if (split == -1) {
            printErrorMessage("Your deadline has to include '/by'");
            return;
        }
        int taskIdx = "deadline ".length();
        int dateIdx = split + "/by ".length();

        String task = deadline.substring(taskIdx, split);
        String date = deadline.substring(dateIdx);

        tasks[tasksSize] = new Deadline(task, date);
        tasksSize++;
        printAddingMessage(tasks, tasksSize);
    }

    public static void addEvent(String event) {
        int firstSplit = event.indexOf("/from");
        int secondSplit = event.indexOf("/to");
        if ((firstSplit == -1) || (secondSplit == -1)) {
            printErrorMessage("Your event has to include '/from' and '/to'");
            return;
        }
        int taskIdx = "event ".length();
        int fromIdx = firstSplit + "/from ".length();
        int toIdx = secondSplit + "/to ".length();

        String task = event.substring(taskIdx, firstSplit);
        String from = event.substring(fromIdx, secondSplit);
        String to = event.substring(toIdx);

        tasks[tasksSize] = new Event(task, from, to);
        tasksSize++;
        printAddingMessage(tasks, tasksSize);
    }

    public static void markTask(int idx) {
        if (tasks[idx - 1] == null) {
            printErrorMessage("That task doesn't even exist!");
            return;
        }
        tasks[idx - 1].setStatus(true);
        System.out.println("Done and dusted, you're gonna shatter my records in no time!");
        System.out.println(tasks[idx - 1]);
        printLinebreak();
    }

    public static void unmarkTask(int idx) {
        if (tasks[idx - 1] == null) {
            printErrorMessage("That task doesn't even exist!");
            return;
        }
        tasks[idx - 1].setStatus(false);
        System.out.println("I ain't worried, we'll clear that soon!");
        System.out.println(tasks[idx - 1]);
        printLinebreak();
    }

    public static void printTasks() {
        for (int i = 0; i < tasksSize; i++) {
            System.out.print(i+1 + ". ");
            System.out.println(tasks[i]);
        }
        printLinebreak();
    }

    public static void main(String[] args) {
        printGreetingMessage();
        Scanner input = new Scanner(System.in);
        while(true) {
            String line = input.nextLine();
            if (line.equals("bye")) {
                System.out.println("Aight catch you later, see you at the game!");
                printLinebreak();
                break;
            }
            if (line.equals("list")) {
                printTasks();
                continue;
            }

            String[] words = line.split(" ");
            int idx;
            switch(words[0]) {
            case "mark":
                idx = Integer.parseInt(words[1]);
                markTask(idx);
                break;
            case "unmark":
                idx = Integer.parseInt(words[1]);
                unmarkTask(idx);
                break;
            case "todo":
                addTodo(line);
                break;
            case "deadline":
                addDeadline(line);
                break;
            case "event":
                addEvent(line);
                break;
            default:
                System.out.println("Personally, I think that's a bad play. Try starting with 'todo', 'deadline', or 'event'!");
                printLinebreak();
                break;
            }
        }
    }
}
