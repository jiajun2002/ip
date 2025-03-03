package lebron.ui;

import lebron.task.Task;
import lebron.task.TaskList;
import java.util.Scanner;

public class Ui {
    private static final String LINE_BREAK = "____________________________________________________________";

    public void printLinebreak() {
        System.out.println(LINE_BREAK);
    }

    public void printGreetingMessage() {
        System.out.println("Hello! I'm Lebron, just a little kid from Akron");
        System.out.println("What we doing today?");
        printLinebreak();
    }

    public void printAddingMessage(String task, int size) {
        System.out.println("Alright! Get ready to do this: ");
        System.out.println("--> " + task);
        System.out.println("Be confident in your ability! Current tasks: " + size);
        printLinebreak();
    }

    public void printMarkedMessage(Task task) {
        System.out.println("Done and dusted, you're gonna shatter my records in no time!");
        System.out.println(task);
        printLinebreak();
    }

    public void printUnmarkedMessage(Task task) {
        System.out.println("I ain't worried, we'll clear that soon!");
        System.out.println(task);
        printLinebreak();
    }

    public void printDeletedMessage(Task task, int size) {
        System.out.println("Traded! We won't be seeing him ever again: ");
        System.out.println(task);
        System.out.println("Remaining tasks: " + size);
        printLinebreak();
    }

    public void printErrorMessage(String error){
        System.out.println("Sorry that's a foul! " + error);
        printLinebreak();
    }

    public void printTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks here, try adding a todo/deadline/event!");
            printLinebreak();
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i+1 + ". ");
            System.out.println(tasks.get(i));
        }
        printLinebreak();
    }

    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}