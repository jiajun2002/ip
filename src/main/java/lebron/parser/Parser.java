package lebron.parser;

import lebron.exception.*;
import lebron.task.TaskList;
import lebron.ui.Ui;

/**
 * Deals with processing user command and executes the appropriate command.
 */
public class Parser {
    /**
     * Interprets user input and executes the appropriate command.
     *
     * @param line The user input.
     * @param tasks The current task list.
     * @param ui UI for dealing with user interactions.
     * @throws UnknownCommandException If the command is not recognised.
     */
    public static void processCommand(String line, TaskList tasks, Ui ui) throws UnknownCommandException {
        String[] words = line.split(" ");
        switch(words[0]) {
        case "mark":
            try {
                tasks.markTask(words);
            } catch (EmptyTaskException e) {
                ui.printErrorMessage("Try inputting a number after mark!");
            } catch (InvalidMarkingException e) {
                ui.printErrorMessage("That task doesn't exist yet, try inputting another number!");
            } catch (NumberFormatException e) {
                ui.printErrorMessage("That's not even a number man!");
            }
            break;
        case "unmark":
            try {
                tasks.unmarkTask(words);
            } catch (EmptyTaskException e) {
                ui.printErrorMessage("Try inputting a number after unmark!");
            } catch (InvalidMarkingException e) {
                ui.printErrorMessage("That task doesn't exist yet, try inputting another number!");
            } catch (NumberFormatException e) {
            ui.printErrorMessage("That's not even a number man!");
            }
            break;
        case "todo":
            try {
                tasks.addTodo(line);
            } catch (EmptyTaskException e) {
                ui.printErrorMessage("How you gonna have a todo for nothing!");
            }
            break;
        case "deadline":
            try {
                tasks.addDeadline(line);
            } catch (EmptyTaskException e) {
                ui.printErrorMessage("Your deadline can't be empty man!");
            } catch (InvalidTaskFormatException e) {
                ui.printErrorMessage("Your deadline format has to include '/by [DATE]'!");
            }
            break;
        case "event":
            try {
                tasks.addEvent(line);
            } catch (EmptyTaskException e) {
                ui.printErrorMessage("An event where nothing happens? Not on my watch.");
            } catch (InvalidTaskFormatException e) {
                ui.printErrorMessage("Your event format has to include '/from [DATE]' and '/to [DATE]!'");
            }
            break;
        case "delete":
            try {
                tasks.deleteTask(words);
            } catch (EmptyTaskException e) {
                ui.printErrorMessage("You gotta delete something at least!");
            } catch (InvalidDeleteException e) {
                ui.printErrorMessage("Your form's a little off. Try inputting a valid number after 'delete'!");
            } catch (NumberFormatException e) {
                ui.printErrorMessage("That's not even a number man!");
            }
            break;
        case "find":
            try {
                tasks.findTask(words);
            } catch (EmptyTaskException e) {
                ui.printErrorMessage("You gotta find something dude!");
            } catch (InvalidFindingException e) {
                ui.printErrorMessage("Slow down there, keep it to a single keyword.");
            }
            break;
        default:
            throw new UnknownCommandException();
        }
    }
}