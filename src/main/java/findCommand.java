public class findCommand extends Command {
    String word;

    public findCommand(String word){
        this.word = word;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(word);
    }
}
