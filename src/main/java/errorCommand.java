/**
 * throw duke exception
 */
public class errorCommand extends Command{
    DukeException errorMsg;

    public errorCommand(String msg) {
        this.errorMsg = new DukeException(msg);
    }

    /**
     * display error message
     * @param tasks -not needed-
     * @param ui is object from Ui that can use to print message / errors
     * @param storage -not needed-
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(errorMsg);
    }
}
