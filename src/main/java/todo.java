/**
 * inherited from Task, and specifically for tasks to be done
 */
public class todo extends Task {

    /**
     *
     * @param description takes in from Task class
     */
    public todo(String description) {
        super(description);
    }

    /**
     *
     * @return specific format to be printed
     */
    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] "+ super.description;
    }

    /**
     *
     * @return specific format to be written to file
     */
    @Override
    public String storeList() {
        return "T | " + super.getStatusInt() + " | " + super.description;
    }
}
