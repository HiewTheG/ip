package myg;

/**
 * Represents a Deadline task with a description and a due date/time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task.
     *
     * @param description The description of the task.
     * @param by The deadline date and/or time.
     * @throws MyGException If the description or the 'by' time is empty.
     */
    public Deadline(String description, String by) throws MyGException {
        super(description);
        if (by == null || by.trim().isEmpty()) {
            throw new MyGException("Oops, myg.Deadline must have a /by date/time");
        }
        this.by = by.trim();
    }

    /**
     * Returns the task in the custom file format for saving.
     * Format: D | IS_DONE (0/1) | DESCRIPTION | BY
     *
     * @return The task represented as a string for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + getFileStatus() + " | " + description + " | " + by;
    }

    /**
     * Returns the string representation of the Deadline task, including its status and due time.
     *
     * @return The formatted string of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}