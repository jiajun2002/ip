package lebron.task;

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
