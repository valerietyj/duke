public class Task {
    protected String description;
    protected boolean isDone;
    public int key;


    public Task(int id, String description) {
        this.key = id;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "YES" : "NO"); //return tick or X symbols
    }

    public void markAsDone(boolean value) {
        this.isDone = value;

    }

}
