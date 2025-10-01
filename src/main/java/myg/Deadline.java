package myg;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws MyGException {
        super(description);
        if (by == null || by.trim().isEmpty()) {
            throw new MyGException("Oops, myg.Deadline must have a /by date/time");
        }
        this.by = by.trim();
    }

    @Override
    public String toFileFormat() {
        return "D | " + getFileStatus() + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
