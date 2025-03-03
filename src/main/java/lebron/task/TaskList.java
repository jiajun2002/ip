package lebron.task;

import lebron.exception.*;
import lebron.storage.Storage;
import lebron.ui.Ui;

import java.util.ArrayList;
import java.io.IOException;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    private Ui ui;

    public TaskList(ArrayList<Task> tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public void addTodo(String todo) throws EmptyTaskException {
        checkEmptyTask(todo);
        String task = todo.substring(todo.indexOf(" ") + 1);
        tasks.add(new Todo(task));
        ui.printAddingMessage(task, tasks.size());
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void addDeadline(String deadline) throws EmptyTaskException, InvalidTaskFormatException {
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
        ui.printAddingMessage(task, tasks.size());
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Hmmmm can't seem to save this: " + e.getMessage());
        }
    }

    public void addEvent(String event) throws EmptyTaskException, InvalidTaskFormatException {
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
        ui.printAddingMessage(task, tasks.size());
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Hmmmm can't seem to save this: " + e.getMessage());
        }
    }

    public void deleteTask(String[] taskInput) throws EmptyTaskException, InvalidDeleteException {
        checkEmptyTaskArr(taskInput);
        int idx = Integer.parseInt(taskInput[1]) - 1;
        if (idx < 0 || idx >= tasks.size() || taskInput.length > 2) {
            throw new InvalidDeleteException();
        }
        Task toPrint = tasks.get(idx);
        tasks.remove(idx);
        ui.printDeletedMessage(toPrint, tasks.size());
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Hmmmm can't seem to save this: " + e.getMessage());
        }
    }

    public void markTask(String[] taskInput) throws EmptyTaskException, InvalidMarkingException {
        checkEmptyTaskArr(taskInput);
        int idx = Integer.parseInt(taskInput[1]) - 1;
        if (idx < 0 || idx >= tasks.size() || taskInput.length > 2) {
            throw new InvalidMarkingException();
        }
        tasks.get(idx).setStatus(true);
        ui.printMarkedMessage(tasks.get(idx));
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Hmmmm can't seem to save this: " + e.getMessage());
        }
    }

    public void unmarkTask(String[] taskInput) throws EmptyTaskException, InvalidMarkingException {
        checkEmptyTaskArr(taskInput);
        int idx = Integer.parseInt(taskInput[1]) - 1;
        if (idx < 0 || idx >= tasks.size() || taskInput.length > 2) {
            throw new InvalidMarkingException();
        }
        tasks.get(idx).setStatus(false);
        ui.printUnmarkedMessage(tasks.get(idx));
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Hmmmm can't seem to save this: " + e.getMessage());
        }
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    private void checkEmptyTask(String task) throws EmptyTaskException {
        String[] words = task.split(" ");
        if (words.length < 2 || words[1].equals("/by") || words[1].equals("/from")) {
            throw new EmptyTaskException();
        }
    }

    private void checkEmptyTaskArr(String[] taskInput) throws EmptyTaskException {
        if (taskInput.length < 2) {
            throw new EmptyTaskException();
        }
    }
}