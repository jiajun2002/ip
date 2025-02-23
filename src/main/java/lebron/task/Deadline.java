package lebron.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, Boolean isDone){
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String toTxtFile() {
        return "D | " + super.toTxtFile() + " | " + by;
    }
}
