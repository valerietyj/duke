public class Events extends Task {

    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] "+ super.description + " (at: " + at + ")";
    }

    @Override
    public String storeList() {
        return "E | " + super.getStatusInt() + " | "+ super.description + " | " + at;
    }


}
