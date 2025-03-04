package lebron.task;

/**
 * Represents a simple task to be added.
 * A {@code Todo} has a description.
 */
public class Todo extends Task {

    /**
     * Constructs an {@code Todo} with the given description.
     *
     * @param description Description of the todo.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Constructs an {@code Todo} with the given description and marked status.
     *
     * @param description Description of the todo.
     * @param isDone Marked status of the todo.
     */
    public Todo(String description, Boolean isDone){
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return A formatted string with the todo type, status and description.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo for saving.
     *
     * @return A formatted string for storage, with the todo type, status and description.
     */
    @Override
    public String toTxtFile() {
        return "T | " + super.toTxtFile();
    }

}
