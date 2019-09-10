/**
 * inherited from Task, and specially for events
 */
public class Events extends Task {

    protected String at;

    /**
     * format of event must be e.g. event [event desc] /at dd/mm/yyyy 0000 (24-hour time format)
     * @param description describes the event
     * @param at is the date of event
     */

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     *
     * @return in specific format to be printed for event
     */
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] "+ super.description + " (at: " + at + ")";
    }
    /**
     *
     * @return in specific format to be written to file
     */
    @Override
    public String storeList() {
        return "E | " + super.getStatusInt() + " | "+ super.description + " | " + at;
    }


}
