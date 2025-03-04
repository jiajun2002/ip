package lebron.task;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 * A {@code Event} has a description, a start time, and an end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an {@code Event} with the given description, start date, and end date.
     *
     * @param description Description of the event.
     * @param from Start date of the event.
     * @param to End date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an {@code Event} with the given description, start and end date, and marked status.
     *
     * @param description Description of the event.
     * @param from Start date of the event.
     * @param to End date of the event.
     * @param isDone Marked status of the event.
     */
    public Event(String description, String from, String to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A formatted string with the event type, status, description, and dates.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }

    /**
     * Returns a string representation of the event for saving.
     *
     * @return A formatted string for storage, with the event type, status, description, and dates.
     */
    @Override
    public String toTxtFile() {
        return "E | " + super.toTxtFile() + " | " + from + " | " + to;
    }
}
