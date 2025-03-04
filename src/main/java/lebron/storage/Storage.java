package lebron.storage;

import lebron.task.Task;
import lebron.task.Todo;
import lebron.task.Deadline;
import lebron.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks into the disk.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new {@code Storage} class with a given file path.
     * @param filePath The file path of the stored tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the text file into a new Task ArrayList.
     * @return The loaded ArrayList
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            new File("./data").mkdirs();
            return tasks;
        }
        Scanner s;
        try {
            s = new Scanner(f);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return tasks;
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
                System.out.println("Not too sure what this is: " + taskData[0]);
                break;
            }
        }
        return tasks;
    }

    /**
     * Saves the task list into the text file.
     * @param tasks The tasks to be saved.
     * @throws IOException If an I/O exception has occurred.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toTxtFile() + System.lineSeparator());
        }
        writer.close();
    }
}