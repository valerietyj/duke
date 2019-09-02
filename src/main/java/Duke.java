import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {

    public static void main(String[] args) throws IOException, customException{
        ArrayList<Task> storeList = new ArrayList<Task>();
        String filePath = "/Users/impt/Desktop/duke/data/duke.txt";
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file, true);
        String tempStore = "";
        int taskCount = 0;

        try {
            Scanner inFile = new Scanner(new File(filePath));
            while (inFile.hasNextLine()) {
                tempStore = inFile.nextLine();
                int index = 0;
                String tempString = tempStore.replace(" | ", "#");

                String[] temp = tempString.split("#");

                int gStatus = Integer.parseInt(temp[1]);

                if (temp[0].equals("D")) {
                    storeList.add(new Deadline(temp[2], temp[3]));

                    if (gStatus == 1) {
                        storeList.get(index).markAsDone();
                    }
                } else if (temp[0].equals("E")) {

                    storeList.add(new Events(temp[2], temp[3]));
                    if (gStatus == 1) {
                        storeList.get(index).markAsDone();
                    }

                } else {

                    storeList.add(new todo(temp[2]));
                    if (gStatus == 1) {
                        storeList.get(index).markAsDone();
                    }
                }

                index++;
            }
            inFile.close();

            for (int i = 0; i < storeList.size(); i++) {
                if (Integer.parseInt(storeList.get(i).getStatusInt()) == 0)
                {
                    taskCount++;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String code = "";
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
                String toOverwrite = storeList.get(list - 1).storeList();
                // System.out.println("1. " + toOverwrite);

                storeList.get(list - 1).markAsDone();
                String status = storeList.get(list - 1).getStatusIcon();
                System.out.println("[" + status + "] " + storeList.get(list - 1).description);

                //  System.out.println(storeList.get(list-1).storeList());
                modifyFile(filePath, toOverwrite, storeList.get(list-1).storeList());

            } else if (input.equals("list")) {
                /*if (count == 0) {
                    System.out.println("nothing in list");

                } */
                for (int i = 0; i < storeList.size(); i++) {

                    System.out.println((i + 1) + ". " + storeList.get(i).toString());
                }

            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (!input.isEmpty()) {

                // print task name

                String arr[] = input.split(" ", 2);
                String taskName = arr[0]; //task name
                arr[1].replace("\\W","");
                //   System.out.println(arr[1]);
                Scanner sc1 = new Scanner(arr[0]);
                if (sc1.hasNext("todo") || sc1.hasNext("deadline") || sc1.hasNext("event")){
                    //  System.out.print("");
                    try {

                        if (!arr[1].isEmpty()) {
                            String theRest = arr[1];

                            if (taskName.equals("deadline")) {
                                String start[] = theRest.split("/by", 2);
                                String deadlineName = start[0]; //event name
                                String deadlineTime = start[1]; //date
                                //String deadDate = deadlineTime.replaceAll(" ", "");

                                SimpleDateFormat inputDate = new SimpleDateFormat("dd/MM/yyy HHmm");
                                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy, h:mma"); // duke to understand this format
                                Date dlInput = inputDate.parse(deadlineTime);
                               String deadDate = formatter.format(dlInput);
                                // System.out.println(date);

                                storeList.add(new Deadline(deadlineName, deadDate));

                            } else if (taskName.equals("event")) {

                                String start1[] = theRest.split("/at", 2);
                                String eventName = start1[0]; //event name
                                String eventTime = start1[1]; //date
                                String eventDate = eventTime.replaceAll(" ", "");

                                SimpleDateFormat inputDate = new SimpleDateFormat("dd/MM/yyy HHmm");
                                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy, h:mma"); // duke to understand this format
                                Date dlInput = inputDate.parse(eventTime);
                                eventDate = formatter.format(dlInput);
                                storeList.add(new Events(eventName, eventDate));

                            } else if (taskName.equals("todo")) {
                                storeList.add(new todo(theRest));

                            }

                            System.out.println("Got it. I've added this task: ");
                            System.out.println(storeList.get(storeList.size()-1).toString());
                            taskCount++;
                            System.out.println("Now you have " + (taskCount) + " tasks in the list.");

                            PrintWriter writer = new PrintWriter(fileWriter);
                            writer.println(storeList.get(count).storeList());
                            writer.close();

                            count++;

                        }
                    }
                    catch (ArrayIndexOutOfBoundsException | ParseException e) {
                        System.out.println("☹ OOPS!!! The description of a " + taskName + " cannot be empty.");
                    }
                }
                else {
                    try {
                        throw new customException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }



            }
        } while (!input.isEmpty());

    }

    static void modifyFile(String filePath, String oldString, String newString) {
        File fileToModify = new File(filePath);
        String oldContent = "";
        BufferedReader read = null;
        FileWriter writer = null;

        try {
            read = new BufferedReader(new FileReader(fileToModify));
            String line = read.readLine();

            while (line != null) {
                oldContent += line + System.lineSeparator();
                line = read.readLine();
            }

            String newContent = oldContent.replace(oldString, newString);

            writer = new FileWriter(fileToModify);

            writer.write(newContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                read.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}