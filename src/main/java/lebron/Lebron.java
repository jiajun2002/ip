package lebron;

import lebron.exception.*;
import lebron.storage.Storage;
import lebron.parser.Parser;
import lebron.ui.Ui;
import lebron.task.*;

/**
 * The main class for the Lebron task management application.
 * Responsible for initialisation, processing user input, and user interaction.
 *
 * @author Tong Jia Jun
 */
public class Lebron {

    private static final String FILE_PATH = "./data/Lebron.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a {@code Lebron} instance with the specified file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Lebron(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks(), storage, ui);
    }

    /**
     * Runs the main application loop and handles user commands.
     * Terminated when user input is "bye".
     */
    public void run() {
        ui.printGreetingMessage();
        while(true) {
            String line = ui.readCommand();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Aight catch you later, see you at the game!");
                ui.printLinebreak();
                break;
            }
            if (line.equalsIgnoreCase("list")) {
                ui.printTaskList(tasks);
                continue;
            }
            try {
                Parser.processCommand(line, tasks, ui);
            } catch (UnknownCommandException e) {
                ui.printErrorMessage("Not sure what you're trying to make me do, start off with todo/deadline/event instead!");
            }
        }
    }

    /**
     * Entry point of the application.
     */
    public static void main(String[] args) {
        new Lebron(FILE_PATH).run();
    }
}
