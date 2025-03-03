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

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toTxtFile() + System.lineSeparator());
        }
        writer.close();
    }
}