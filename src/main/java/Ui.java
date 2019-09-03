import java.text.ParseException;
import java.util.Scanner;

public class Ui {


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

    static String readCommand() throws ParseException {
        String input;

        do {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            return (input);
        }
        while (!input.isEmpty());

    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }



}
