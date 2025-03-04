package lebron.task;

import lebron.exception.*;
import lebron.storage.Storage;
import lebron.ui.Ui;

import java.util.ArrayList;
import java.io.IOException;

/**
 * Manages the task list and provides methods to modify it.
 * Provides methods such as addition, deleting, marking, unmarking.
 */

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new {@code TaskList} with the given list of tasks, storage, and UI.
     *
     * @param tasks The list of tasks.
     * @param storage Storage handler for saving and loading tasks.
     * @param ui UI handler for managing user interactions.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param todo The todo to be added.
     * @throws EmptyTaskException If the description is empty.
     */
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

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param deadline The deadline to be added.
     * @throws EmptyTaskException If the deadline description is empty.
     * @throws InvalidTaskFormatException If the deadline format is invalid.
     */
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

    /**
     * Adds a new Event task to the task list.
     *
     * @param event The event to be added.
     * @throws EmptyTaskException If the event description is empty.
     * @throws InvalidTaskFormatException If the event format is invalid.
     */
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

    /**
     * Deletes a task from the task list.
     *
     * @param taskInput The input containing the task index.
     * @throws EmptyTaskException If no task index is provided.
     * @throws InvalidDeleteException If the index is out of range.
     */
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

    /**
     * Marks a task in the task list.
     *
     * @param taskInput The input containing the task index.
     * @throws EmptyTaskException If no task index is provided.
     * @throws InvalidMarkingException If the index is out of range.
     */
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

    /**
     * Unmarks a task in the task list.
     *
     * @param taskInput The input containing the task index.
     * @throws EmptyTaskException If no task index is provided.
     * @throws InvalidMarkingException If the index is out of range.
     */
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

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Retrieves a task at a given index.
     *
     * @param index Index of the task to be retrieved
     * @return The task at the index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }


    /**
     * Checks whether a task description is empty.
     *
     * @param task The task description.
     * @throws EmptyTaskException If the task description is empty or missing other details.
     */
    private void checkEmptyTask(String task) throws EmptyTaskException {
        String[] words = task.split(" ");
        if (words.length < 2 || words[1].equals("/by") || words[1].equals("/from")) {
            throw new EmptyTaskException();
        }
    }

    /**
     * Checks whether a command input is valid/contains a valid index.
     *
     * @param taskInput The command input array.
     * @throws EmptyTaskException If no task index is provided.
     */
    private void checkEmptyTaskArr(String[] taskInput) throws EmptyTaskException {
        if (taskInput.length < 2) {
            throw new EmptyTaskException();
        }
    }
}