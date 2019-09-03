public class AddCommand extends Command {
    String t_code;
    String t_desc;
    String t_date;

    public AddCommand(String code, String desc, String date) {
        this.t_code = code;
        this.t_desc = desc;
        this.t_date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(t_code, t_desc, t_date);
        storage.writeToFile(tasks.getTasks());

    }
}
