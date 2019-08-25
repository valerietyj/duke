import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] listArray = new String[100];
        String input;
        int count = 0;
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

        do {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();

            if (input.equals("list")) {
                if (count == 0) {
                    System.out.println("nothing in list");
                }
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + listArray[i]);
                }
                continue;
            }
            else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (!input.isEmpty())
            {
                System.out.println("added: " + input);
                listArray[count] = input;
                count++;
            }

        } while (!input.isEmpty());

    }
}
