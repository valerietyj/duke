import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> storeList = new ArrayList<Task>();
        //...getting task 0 from name ArrayList
        //name.get(o).(element)
        String[] listArray = new String[100];
        String[] yesNO = new String[100];
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

            String s = input;
            String[] parts = s.split(" ");
            String taskDone = parts[0];

            if (taskDone.equals("done")) {
                // System.out.println(parts[1]);
                int list = Integer.parseInt(parts[1]);
                storeList.get(list - 1).markAsDone();
                String status = storeList.get(list - 1).getStatusIcon();
                System.out.println("[" + status + "] " + storeList.get(list - 1).description);

            } else if (input.equals("list")) {
                if (count == 0) {
                    System.out.println("nothing in list");
                }

                for (int i = 0; i < count; i++) {
                    String status = storeList.get(i).getStatusIcon();
                    System.out.println((i + 1) + ". [" + status + "] " + storeList.get(i).description);
                }
                continue;
            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (!input.isEmpty()) {
                storeList.add(new Task(count, input));
                System.out.println("added: " + input);
                count++;
            }

        } while (!input.isEmpty());

    }
}