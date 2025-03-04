package lebron.ui;

import lebron.task.Task;
import lebron.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Manages user interactions with the user.
 * Provides methods for printing various messages and receiving user input.
 */
public class Ui {
    private static final String LINE_BREAK = "____________________________________________________________";

    public void printLinebreak() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a greeting message when program starts.
     */
    public void printGreetingMessage() {
        System.out.println("Hello! I'm Lebron, just a little kid from Akron");
        System.out.println("What we doing today?");
        printLinebreak();
    }

    /**
     * Prints a message when a task is successfully added.
     *
     * @param task Task that was added.
     * @param size Number of tasks in the list after addition.
     */
    public void printAddingMessage(String task, int size) {
        System.out.println("Alright! Get ready to do this: ");
        System.out.println("--> " + task);
        System.out.println("Be confident in your ability! Current tasks: " + size);
        printLinebreak();
    }

    /**
     * Prints a message when a task is successfully marked.
     *
     * @param task Task that is being marked.
     */
    public void printMarkedMessage(Task task) {
        System.out.println("Done and dusted, you're gonna shatter my records in no time!");
        System.out.println(task);
        printLinebreak();
    }

    /**
     * Prints a message when a task is successfully unmarked.
     *
     * @param task Task that is being unmarked.
     */
    public void printUnmarkedMessage(Task task) {
        System.out.println("I ain't worried, we'll clear that soon!");
        System.out.println(task);
        printLinebreak();
    }

    /**
     * Prints a message when a task is successfully deleted.
     *
     * @param task Task that is being deleted.
     * @param size Number of tasks in the list after deletion.
     */
    public void printDeletedMessage(Task task, int size) {
        System.out.println("Traded! We won't be seeing him ever again: ");
        System.out.println(task);
        System.out.println("Remaining tasks: " + size);
        printLinebreak();
    }

    public void printFoundMessage(ArrayList<Task> foundTasks) {
        System.out.println("Got em! Here are the matching tasks!");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.print(i+1 + ". ");
            System.out.println(foundTasks.get(i));
        }
        printLinebreak();
    }

    /**
     * Prints a message when an error is encountered.
     *
     * @param error Description of the error.
     */
    public void printErrorMessage(String error){
        System.out.println("Sorry that's a foul! " + error);
        printLinebreak();
    }

    /**
     * Prints all the tasks in the current list.
     *
     * @param tasks The list of tasks to be displayed.
     */
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

    /**
     * Reads the user's command input.
     *
     * @return The input as a string.
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}