/**
 * if user input is "delete"
 */
public class deleteCommand extends Command{
    int index;

    /**
     * get index of task to be deleted
     * @param index identifies the index and pass on to TaskList
     */
    public deleteCommand(int index) {
        this.index = index;
    }

    /**
     * for deletion of takss
     * @param tasks is an object from TaskList that have operations delete tasks in the list
     * @param ui is object from Ui that can use to print message / errors
     * @param storage is an object from Storage that is used to modify/read/write onto file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(index);

    }
}
