public class todo extends Task {



    public todo(String description) {
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "]"+ super.description;
    }
}
