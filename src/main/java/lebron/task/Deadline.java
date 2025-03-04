package lebron.task;

/**
 * Represents a task that needs to be done by a specific time.
 * A {@code Deadline} has a description and a due date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs an {@code Deadline} with the given description and due date.
     *
     * @param description Description of the deadline.
     * @param by Due date of the deadline.
     */
    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    /**
     * Constructs an {@code Deadline} with the given description, due date and marked status.
     *
     * @param description Description of the deadline.
     * @param by Due date of the deadline.
     * @param isDone Marked status of the deadline.
     */
    public Deadline(String description, String by, Boolean isDone){
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return A formatted string with the deadline type, status, description, and due date.
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    /**
     * Returns a string representation of the deadline for saving.
     *
     * @return A formatted string for storage, with the deadline type, status, description, and due date.
     */
    @Override
    public String toTxtFile() {
        return "D | " + super.toTxtFile() + " | " + by;
    }
}
