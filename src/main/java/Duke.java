import java.sql.Array;
import java.util.Arrays;
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
                    int list = Integer.parseInt(parts[1]);
                    storeList.get(list - 1).markAsDone();
                    String status = storeList.get(list - 1).getStatusIcon();
                    System.out.println("[" + status + "] " + storeList.get(list - 1).description);

                } else if (input.equals("list")) {
                    if (count == 0) {
                        System.out.println("nothing in list");
                    }

                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + storeList.get(i).toString());
                    }
                    continue;
                } else if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (!input.isEmpty()) {
                    System.out.println("Got it. I've added this task: ");

                    // print task name
                    String arr[] = input.split(" ", 2);
                    String taskName = arr[0]; //task name

                    String theRest = arr[1];

                    if (taskName.equals("deadline")) {

                        String start[] = theRest.split("/by", 2);
                        String deadlineName = start[0]; //event name
                        String deadlineTime = start[1]; //date

                        storeList.add(new Deadline(deadlineName, deadlineTime));

                    }
                    else if (taskName.equals("event")) {

                        String start1[] = theRest.split("/at", 2);
                        String eventName = start1[0]; //event name
                        String eventTime = start1[1]; //date

                        storeList.add(new Events(eventName, eventTime));

                    }
                    else if (taskName.equals("todo")) {
                        storeList.add(new todo(theRest));

                    }
                    System.out.println(storeList.get(count).toString());
                    System.out.println("Now you have " + (count+1) + " tasks in the list.");
                    count++;

                }

            } while (!input.isEmpty());

        }
}