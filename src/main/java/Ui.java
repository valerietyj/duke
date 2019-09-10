import com.sun.tools.javac.Main;

import java.text.ParseException;
import java.util.Scanner;

/**
 * user interface of duke
 */
public class Ui {

    /**
     * Duke start interface
     */
    static void showWelcome() {
        //start of duke code
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");


    }
    Scanner sc = new Scanner(System.in);

    /**
     * @return pass user input to Parser class
     * @throws ParseException checked exception used to check if failed to parse date in the correct format [dd/mm/yyyy 0000]
     */
    public String readCommand() throws ParseException {
        String input = "";

        do {
            input = sc.nextLine();
            return (input);
        }
        while (sc.hasNextLine());

    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }



}
