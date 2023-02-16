package tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString() + "(by: " + by + ")";
    }
}