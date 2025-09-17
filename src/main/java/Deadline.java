public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws MyGException {
        super(description);
        if (by == null) {
            throw new MyGException("Oops, Deadline must have a /by date/time");
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
