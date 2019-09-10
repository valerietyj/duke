/**
 * calls TaskList to find specific keyword and list them in tasks
 */
public class findCommand extends Command {
    String word;

    /**
     * display list of tasks based on user key word
     * @param word key word that user would like to find in tasks
     */
    public findCommand(String word){
        this.word = word;
    }

    /**
     *
     * @param tasks is an object from TaskList that have operations to find tasks in the list
     * @param ui -not needed-
     * @param storage -not needed-
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(word);
    }
}
