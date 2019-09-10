/**
 * when user input = "bye"
 */
public class ExitCommand extends Command {
    /**
     * change boolean exit to true
     */
    public ExitCommand () {
        exit = true;
    }

    /**
     *
     * @param tasks -not needed-
     * @param ui is object from Ui that can use to print message / errors
     * @param storage is an object from Storage that is used to modify/read/write onto file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

}
