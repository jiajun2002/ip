package lebron.task;

/**
 * Represents an abstract task to be subclassed by other classes.
 * Provides methods to check and mark if a task is done and
 * get the task description.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public String getDescription(){
        return description;
    }

    public void setStatus(boolean status) {
        isDone = status;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toTxtFile() {
        return (isDone? "1" : "0") + " | " + description;
    }
}
