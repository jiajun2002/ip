package lebron.ui;

import lebron.exception.*;
import lebron.task.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Lebron {
    private static final String LINE_BREAK = "____________________________________________________________";
    private static final String FILE_PATH = "./data/Lebron.txt";
    private static int tasksSize = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void loadTasksFromFile() {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            new File("./data").mkdirs();
            return;
        }
        Scanner s;
        try {
            s = new Scanner(f);
        } catch(FileNotFoundException e) {
            printErrorMessage(e.getMessage());
            return;
        }
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] taskData = line.split(" \\| ");
            boolean isDone = taskData[1].equals("1");
            switch (taskData[0]) {
            case "T":
                tasks.add(new Todo(taskData[2], isDone));
                break;
            case "D":
                tasks.add(new Deadline(taskData[2], taskData[3], isDone));
                break;
            case "E":
                tasks.add(new Event(taskData[2], taskData[3], taskData[4], isDone));
                break;
            default:
                System.out.println("No clue what this is: " + taskData[0]);
                break;
            }
        }
    }

    private static void saveTasksToFile() throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toTxtFile() + System.lineSeparator());
            }
            writer.close();
    }

    public static void printLinebreak() {
        System.out.println(LINE_BREAK);
    }

    public static void printGreetingMessage() {
        System.out.println("Hello! I'm Lebron, just a little kid from Akron");
        System.out.println("What we doing today?");
        printLinebreak();
    }

    public static void printAddingMessage(String task) {
        System.out.println("Alright! Get ready to do this: ");
        System.out.println("--> " + task);
        System.out.println("Be confident in your ability! Current tasks: " + tasks.size());
        printLinebreak();
    }

    public static void printErrorMessage(String error){
        System.out.println("Sorry that's a foul! " + error);
        printLinebreak();
    }

    public static void checkEmptyTask(String task) throws EmptyTaskException {
        String[] words = task.split(" ");
        if (words.length < 2 || words[1].equals("/by") || words[1].equals("/from")) {
            throw new EmptyTaskException();
        }
    }

    public static void checkEmptyTaskArr(String[] taskInput) throws EmptyTaskException {
        if (taskInput.length < 2) {
            throw new EmptyTaskException();
        }
    }

    public static void addTodo(String todo)
            throws EmptyTaskException {
        checkEmptyTask(todo);
        String task = todo.substring(todo.indexOf(" ") + 1);
        tasks.add(new Todo(task));
        printAddingMessage(task);
        try {
            saveTasksToFile();
        } catch (IOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    public static void addDeadline(String deadline)
            throws EmptyTaskException, InvalidTaskFormatException {
        checkEmptyTask(deadline);
        int split = deadline.indexOf("/by");
        if (split == -1) {
            throw new InvalidTaskFormatException();
        }

        int taskStartingPos = "deadline ".length();
        int dateStartingPos = split + "/by ".length();
        String task = deadline.substring(taskStartingPos, split);
        String date = deadline.substring(dateStartingPos);

        tasks.add(new Deadline(task, date));
        printAddingMessage(task);
        try {
            saveTasksToFile();
        } catch (IOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    public static void addEvent(String event)
            throws EmptyTaskException, InvalidTaskFormatException {
        checkEmptyTask(event);
        int firstSplit = event.indexOf("/from");
        int secondSplit = event.indexOf("/to");
        if ((firstSplit == -1) || (secondSplit == -1)) {
            throw new InvalidTaskFormatException();
        }
        int taskStartingPos = "event ".length();
        int fromStartingPos = firstSplit + "/from ".length();
        int toStartingPos = secondSplit + "/to ".length();

        String task = event.substring(taskStartingPos, firstSplit);
        String from = event.substring(fromStartingPos, secondSplit);
        String to = event.substring(toStartingPos);

        tasks.add(new Event(task, from, to));
        printAddingMessage(task);
        try {
            saveTasksToFile();
        } catch (IOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    public static void deleteTask(String[] taskInput)
            throws EmptyTaskException, InvalidDeleteException {
        checkEmptyTaskArr(taskInput);
        int idx = Integer.parseInt(taskInput[1]) - 1;
        if (idx < 0 || idx >= tasks.size() || taskInput.length > 2) {
            throw new InvalidDeleteException();
        }
        Task toPrint = tasks.get(idx);
        tasks.remove(idx);
        System.out.println("Traded! We won't be seeing him ever again: ");
        System.out.println(toPrint);
        System.out.println("Remaining tasks: " + tasks.size());
        printLinebreak();
        try {
            saveTasksToFile();
        } catch (IOException e) {
            printErrorMessage("Error saving tasks after deletion: " + e.getMessage());
        }
    }

    public static void markTask(String[] taskInput)
            throws EmptyTaskException, InvalidMarkingException {
        checkEmptyTaskArr(taskInput);
        int idx = Integer.parseInt(taskInput[1]) - 1;
        if (idx < 0 || idx >= tasks.size() || taskInput.length > 2) {
            throw new InvalidMarkingException();
        }
        tasks.get(idx).setStatus(true);
        System.out.println("Done and dusted, you're gonna shatter my records in no time!");
        System.out.println(tasks.get(idx));
        printLinebreak();
        try {
            saveTasksToFile();
        } catch (IOException e) {
            printErrorMessage("Error saving tasks after deletion: " + e.getMessage());
        }
    }

    public static void unmarkTask(String[] taskInput)
            throws EmptyTaskException, InvalidMarkingException {
        checkEmptyTaskArr(taskInput);
        int idx = Integer.parseInt(taskInput[1]) - 1;
        if (idx < 0 || idx >= tasks.size() || taskInput.length > 2) {
            throw new InvalidMarkingException();
        }
        tasks.get(idx).setStatus(false);
        System.out.println("I ain't worried, we'll clear that soon!");
        System.out.println(tasks.get(idx));
        printLinebreak();
        try {
            saveTasksToFile();
        } catch (IOException e) {
            printErrorMessage("Error saving tasks after deletion: " + e.getMessage());
        }
    }

    public static void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks here, trying adding a todo/deadline/event!");
            printLinebreak();
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i+1 + ". ");
            System.out.println(tasks.get(i));
        }
        printLinebreak();
    }

    public static void processCommand(String line)
            throws UnknownCommandException {
        String[] words = line.split(" ");
        switch(words[0]) {
        case "mark":
            try {
                markTask(words);
            } catch (EmptyTaskException e) {
                printErrorMessage("Try inputting a number after mark!");
            } catch (InvalidMarkingException e) {
                printErrorMessage("That task doesn't exist yet, try inputting another number!");
            }
            break;
        case "unmark":
            try {
                unmarkTask(words);
            } catch (EmptyTaskException e) {
                printErrorMessage("Try inputting a number after unmark!");
            } catch (InvalidMarkingException e) {
                printErrorMessage("That task doesn't exist yet, try inputting another number!");
            }
            break;
        case "todo":
            try {
                addTodo(line);
            } catch (EmptyTaskException e) {
                printErrorMessage("How you gonna have a todo for nothing!");
            }
            break;
        case "deadline":
            try {
                addDeadline(line);
            } catch (EmptyTaskException e) {
                printErrorMessage("Your deadline can't be empty man!");
            } catch (InvalidTaskFormatException e) {
                printErrorMessage("Your deadline format has to include '/by'!");
            }
            break;
        case "event":
            try {
                addEvent(line);
            } catch (EmptyTaskException e) {
                printErrorMessage("An event where nothing happens? Not on my watch.");
            } catch (InvalidTaskFormatException e) {
                printErrorMessage("Your event format has to include '/from' and '/to!");
            }
            break;
        case "delete":
            try {
                deleteTask(words);
            } catch (EmptyTaskException e) {
                printErrorMessage("You gotta delete something at least!");
            } catch (InvalidDeleteException e) {
                printErrorMessage("Your form's a little off. Try inputting a valid number after 'delete'!");
            }
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    public static void main(String[] args) {
        printGreetingMessage();
        loadTasksFromFile();
        Scanner input = new Scanner(System.in);
        while(true) {
            String line = input.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Aight catch you later, see you at the game!");
                printLinebreak();
                break;
            }
            if (line.equalsIgnoreCase("list")) {
                printTasks();
                continue;
            }
            try {
                processCommand(line);
            } catch (UnknownCommandException e) {
                printErrorMessage("Not sure what you're trying to make me do, start off with todo/deadline/event instead!");
            }
        }
    }
}
