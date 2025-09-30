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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
