/**
 * Represent a task list as a multi-dimensional array containing primitive values, the more natural approach is to use a Task class to represent tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description takes in the task description and store in arrayList
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * gets the status of tasks
     * @return status of tasks
     */
    public String getStatusIcon() {
        return (isDone ? "YES" : "NO"); //return tick or X symbols
    }
    //export

    /**
     * gets status of tasks and return as export format
     * @return status of tasks in int format
     */
    public String getStatusInt() {
        return (isDone ? "1" : "0"); //return tick or X symbols
    }

    /**
     * if user input is "done", mark the tasks as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * gets the description of tasks to be printed in specific format
     * @return description of task
     */
    public String storeList() {
        return description;
    }

}
