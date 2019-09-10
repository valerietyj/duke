/**
 * inherited from Task, and specifically for tasks that has deadline to meet
 */
public class Deadline extends Task {

    protected String by;

    /**
     * format of deadline must be e.g. deadline [desc] /by dd/mm/yyyy 0000 (24-hour time format)
     * @param description of deadline task
     * @param by get the date in specific format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     *
     * @return in specific format to be printed for deadline
     */
    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] "+ super.description + " (by: " + by + ")";
    }

    /**
     *
     * @return in specific format to be written to file
     */
    @Override
    public String storeList() {
        return "D | " + super.getStatusInt() + " | "+ super.description + " | " + by;
    }
}
