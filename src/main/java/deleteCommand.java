public class deleteCommand extends Command{
    int index;

    public deleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(index);

    }
}
