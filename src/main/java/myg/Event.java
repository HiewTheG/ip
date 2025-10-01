package myg;

/**
 * Represents an Event task with a description and a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task.
     *
     * @param description The description of the event.
     * @param from The start date and/or time of the event.
     * @param to The end date and/or time of the event.
     * @throws MyGException If the description, 'from' time, or 'to' time is empty.
     */
    public Event(String description, String from, String to) throws MyGException {
        super(description);
        if (from == null || from.trim().isEmpty() || to == null ||  to.trim().isEmpty()) {
            throw new MyGException("Oops, myg.Event must have /from and /to dates/times.");
        }
        this.from = from.trim();
        this.to = to.trim();
    }

    /**
     * Returns the task in the custom file format for saving.
     * Format: E | IS_DONE (0/1) | DESCRIPTION | FROM - TO
     *
     * @return The task represented as a string for file storage.
     */
    @Override
    public String toFileFormat() {
        // Using two extra pipes for from and to to allow full reconstruction
        return "E | " + getFileStatus() + " | " + description + " | " + from + " - " + to;
    }

    /**
     * Returns the string representation of the Event task, including its status and time range.
     *
     * @return The formatted string of the Event task.
     */
    @Override
    public String toString()
    {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}