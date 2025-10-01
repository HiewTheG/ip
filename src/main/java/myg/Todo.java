package myg;

/**
 * Represents a Todo task, which is a simple task with no due date/time.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task.
     *
     * @param description The description of the task.
     * @throws MyGException If the description is empty.
     */
    public Todo(String description) throws MyGException {
        super(description);
    }

    /**
     * Returns the task in the custom file format for saving.
     * Format: T | IS_DONE (0/1) | DESCRIPTION
     *
     * @return The task represented as a string for file storage.
     */
    @Override
    public String toFileFormat() {
        return "T | " + getFileStatus() + " | " + description;
    }

    /**
     * Returns the string representation of the Todo task, including its status.
     *
     * @return The formatted string of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}