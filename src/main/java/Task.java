public class Task {
    protected String description;
    protected boolean isDone;
    public int key;


    public Task(int key, String description) {
        this.key = key;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "YES" : "NO"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;

    }


}
