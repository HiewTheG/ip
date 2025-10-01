package myg;

public class Todo extends Task {
    public Todo(String description) throws MyGException {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "T | " + getFileStatus() + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
