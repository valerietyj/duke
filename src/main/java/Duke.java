import java.text.ParseException;
import java.io.IOException;

/**
 * Main class file
 */
public class Duke {

    private static String filePath = "/Users/impt/Desktop/duke/data/duke.txt";
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;



    /**
     * @param filePath specify file destination to write / load tasks list to /from
     * creates new duke session
     */
    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * runs the created new duke session
     * @throws ParseException is a checked exception used to check if failed to parse date in the correct format [dd/mm/yyyy 0000]
     */
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