/**
 * Call TaskList to mark specified task as done
 */
public class doneCommand extends Command{
    int index;

    /**
     *
     * @param index of tasks to be marked as done
     */
    public doneCommand(int index) {
        this.index = index;
    }

    /**
     * for tasks to be mark as done
     * @param tasks is an object from TaskList that can mark tasks as done in TaskList
     * @param ui is object from Ui that can use to print message / errors
     * @param storage is an object from Storage that is used to modify/read/write onto file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.done(index);
    }
}
