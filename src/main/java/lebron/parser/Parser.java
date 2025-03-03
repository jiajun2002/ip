package lebron.parser;

import lebron.exception.*;
import lebron.task.TaskList;
import lebron.ui.Ui;

public class Parser {
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
            }
            break;
        case "unmark":
            try {
                tasks.unmarkTask(words);
            } catch (EmptyTaskException e) {
                ui.printErrorMessage("Try inputting a number after unmark!");
            } catch (InvalidMarkingException e) {
                ui.printErrorMessage("That task doesn't exist yet, try inputting another number!");
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
                ui.printErrorMessage("Your deadline format has to include '/by'!");
            }
            break;
        case "event":
            try {
                tasks.addEvent(line);
            } catch (EmptyTaskException e) {
                ui.printErrorMessage("An event where nothing happens? Not on my watch.");
            } catch (InvalidTaskFormatException e) {
                ui.printErrorMessage("Your event format has to include '/from' and '/to!");
            }
            break;
        case "delete":
            try {
                tasks.deleteTask(words);
            } catch (EmptyTaskException e) {
                ui.printErrorMessage("You gotta delete something at least!");
            } catch (InvalidDeleteException e) {
                ui.printErrorMessage("Your form's a little off. Try inputting a valid number after 'delete'!");
            }
            break;
        default:
            throw new UnknownCommandException();
        }
    }
}