/**
 * inherits from command;
 * AddCommand method specifies parameter of object needed to be added to TaskList;
 * execute method adds task into TaskList
 */
public class AddCommand extends Command {
    String t_code;
    String t_desc;
    String t_date;

    /**
     *
     * class addCommand inherits from Command
     * @param code - todo / event / deadline which defines type of tasks
     * @param desc - describe the tasks name of todo / event / deadline
     * @param date - only applicable to event / deadline, date for todo will be null.
     *
     */
    public AddCommand(String code, String desc, String date) {
        this.t_code = code;
        this.t_desc = desc;
        this.t_date = date;
    }

    /**
     *
     * abstract method that allows subclasses
     * @param tasks is an object from TaskList class which adds the tasks from user input
     * @param ui -did not use-
     * @param storage writes to .txt file locally
     *
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(t_code, t_desc, t_date);
        storage.writeToFile(tasks.getTasks());

    }
}
