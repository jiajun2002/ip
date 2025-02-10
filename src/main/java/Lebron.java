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

    public static void checkEmptyTask(String task) throws EmptyTaskException {
        String[] words = task.split(" ");
        if (words.length < 2 || words[1].equals("/by") || words[1].equals("/from")) {
            throw new EmptyTaskException();
        }
    }

    public static void addTodo(String todo)
            throws EmptyTaskException {
        checkEmptyTask(todo);
        String task = todo.substring(todo.indexOf(" ") + 1);
        tasks[tasksSize] = new Todo(task);
        tasksSize++;
        printAddingMessage(tasks, tasksSize);
    }

    public static void addDeadline(String deadline)
            throws EmptyTaskException, InvalidTaskFormatException {
        checkEmptyTask(deadline);
        int split = deadline.indexOf("/by");
        if (split == -1) {
            throw new InvalidTaskFormatException();
        }

        int taskIdx = "deadline ".length();
        int dateIdx = split + "/by ".length();
        String task = deadline.substring(taskIdx, split);
        String date = deadline.substring(dateIdx);

        tasks[tasksSize] = new Deadline(task, date);
        tasksSize++;
        printAddingMessage(tasks, tasksSize);
    }

    public static void addEvent(String event)
            throws EmptyTaskException, InvalidTaskFormatException {
        checkEmptyTask(event);
        int firstSplit = event.indexOf("/from");
        int secondSplit = event.indexOf("/to");
        if ((firstSplit == -1) || (secondSplit == -1)) {
            throw new InvalidTaskFormatException();
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

    public static void markTask(String[] taskInput)
            throws EmptyTaskException, InvalidMarkingException {
        if (taskInput.length < 2) {
            throw new EmptyTaskException();
        }
        int idx = Integer.parseInt(taskInput[1]);
        if (idx <= 0 || idx > tasksSize || taskInput.length > 2) {
            throw new InvalidMarkingException();
        }
        tasks[idx - 1].setStatus(true);
        System.out.println("Done and dusted, you're gonna shatter my records in no time!");
        System.out.println(tasks[idx - 1]);
        printLinebreak();
    }

    public static void unmarkTask(String[] taskInput)
            throws EmptyTaskException, InvalidMarkingException {
        if (taskInput.length < 2) {
            throw new EmptyTaskException();
        }
        int idx = Integer.parseInt(taskInput[1]);
        if (idx <= 0 || idx > tasksSize || taskInput.length > 2) {
            throw new InvalidMarkingException();
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
        default:
            throw new UnknownCommandException();
        }
    }

    public static void main(String[] args) {
        printGreetingMessage();
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
