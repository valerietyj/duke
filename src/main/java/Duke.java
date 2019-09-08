import java.text.ParseException;
import java.io.IOException;


public class Duke {
    private static String filePath= "/Users/impt/Desktop/duke/data/duke.txt";
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;


    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public void run() throws ParseException {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) throws ParseException {
       new Duke(filePath).run();

    }

}