package myg;

/**
 * The abstract base class representing a task in the MyG application.
 * Contains common properties and methods for all task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description The description of the task.
     * @throws MyGException If the description is null or empty.
     */
    public Task(String description) throws MyGException {
        if (description == null ||  description.trim().isEmpty()) {
            throw new MyGException("Oops, description is empty. Pls type something");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon (X for done, space for not done).
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void  unmark() {
        isDone = false;
    }

    /**
     * Returns the status of the task as a file-friendly integer (1 for done, 0 for not done).
     *
     * @return "1" if done, "0" if not done.
     */
    public String getFileStatus() {
        return isDone ? "1" : "0";
    }

    /**
     * Returns the task in the custom file format.
     * The format should be: TYPE | IS_DONE (0/1) | DESCRIPTION [| EXTRA_DATA]
     *
     * @return The task represented as a string for file storage.
     */
    public abstract String toFileFormat();

    /**
     * Returns the string representation of the Task, including its status icon and description.
     *
     * @return The formatted string of the Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}