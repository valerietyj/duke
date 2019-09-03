import java.awt.*;

abstract class Command {

    boolean exit = false;

    public boolean isExit() {
        return exit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
