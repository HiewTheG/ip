package myg;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws MyGException {
        super(description);
        if (from == null || from.trim().isEmpty() || to == null ||  to.trim().isEmpty()) {
            throw new MyGException("Oops, myg.Event must have /from and /to dates/times.");
        }
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toFileFormat() {
        // Using two extra pipes for from and to to allow full reconstruction
        return "E | " + getFileStatus() + " | " + description + " | " + from + " - " + to;
    }

    @Override
    public String toString()
    {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
