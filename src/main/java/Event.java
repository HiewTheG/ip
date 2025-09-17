public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws MyGException {
        super(description);
        if (from == null || to == null) {
            throw new MyGException("Oops, Event must have /from and /to dates/times.");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString()
    {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
