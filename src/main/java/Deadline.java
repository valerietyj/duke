public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] "+ super.description + " (by: " + by + ")";
    }

    @Override
    public String storeList() {
        return "D | " + super.getStatusInt() + " | "+ super.description + " | " + by;
    }
}
