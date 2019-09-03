public class doneCommand extends Command{
    int index;

    public doneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.done(index);
    }
}
