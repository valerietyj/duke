import java.awt.*;

/**
 * parent class for any type of commands
 */
abstract class Command {

    boolean exit = false;

    /**
     *
     * @return if user input "bye", boolean flag of exit will be change to true. and return exit command.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * abstract class
     * @param tasks is an object from TaskList that have operations to add/delete tasks in the list
     * @param ui is object from Ui that can use to print message / errors
     * @param storage is an object from Storage that is used to modify/read/write onto file
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
