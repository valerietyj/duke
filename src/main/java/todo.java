public class todo extends Task {


    public todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] "+ super.description;
    }

    @Override
    public String storeList() {
        return "T | " + super.getStatusInt() + " | " + super.description;
    }
}
