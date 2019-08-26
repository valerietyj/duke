import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

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
                Task t = new Task(list - 1, listArray[list-1]);
                t.markAsDone(true);
               // String status = TaskStatus(list - 1 , listArray[list-1], "done");
                yesNO[list - 1] = t.getStatusIcon();
                System.out.println("[" + t.getStatusIcon() + "] " + listArray[list - 1]);

            }
            else if (input.equals("list")) {
                if (count == 0) {
                    System.out.println("nothing in list");
                }

                for (int i = 0; i < count; i++) {
                    //String status = TaskStatus(i, listArray[i], "list");
                    //Task t = new Task(i , listArray[i]);
                   // System.out.println((i + 1) + ". " + listArray[i]);
                    System.out.println((i + 1) + ". [" + yesNO[i] + "] " + listArray[i]);
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
                Task t = new Task(count, input);
                yesNO[count]=t.getStatusIcon();
                count++;
            }

        } while (!input.isEmpty());

    }

  /*  public static String TaskStatus(int input, String name, String status) {
        Task t = new Task(input, name);


        if (status.equals("done"))
        {
            t.markAsDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            return t.getStatusIcon();
        }
        else if (status.equals("list")){
            return t.getStatusIcon();
        }
        return "error";
    }*/

}
