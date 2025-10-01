package myg;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws MyGException {
        if (description == null ||  description.trim().isEmpty()) {
            throw new MyGException("Oops, description is empty. Pls type something");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void  unmark() {
        isDone = false;
    }

    /**
     * Returns the status of the task as a file-friendly integer (1 for done, 0 for not done).
     */
    public String getFileStatus() {
        return isDone ? "1" : "0";
    }

    /**
     * Returns the task in the custom file format.
     * The format should be: TYPE | IS_DONE (0/1) | DESCRIPTION [| EXTRA_DATA]
     */
    public abstract String toFileFormat();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
