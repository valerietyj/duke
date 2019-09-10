/**
 * calls TaskList to list all tasks
 */
public class List extends Command {
    /**
     *
     * @param tasks is an object from TaskList that have operations to retrieve tasks in the list
     * @param ui -not needed-
     * @param storage -not needed-
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        tasks.getlist();

    }
}
