public class errorCommand extends Command{
    DukeException errorMsg;

    public errorCommand(String msg) {
        this.errorMsg = new DukeException(msg);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(errorMsg);
    }
}
