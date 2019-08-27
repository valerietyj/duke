public class Task {
    protected String description;
    protected boolean isDone;
    protected char code;
    protected String by;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "YES" : "NO"); //return tick or X symbols
    }
    public String getStatusInt() {
        return (isDone ? "1" : "0"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String storeList() {
        return description;
    }

}
