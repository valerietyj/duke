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

    public static void main(String[] args) throws IOException, DukeException{
        ArrayList<Task> storeList = new ArrayList<>();
        String filePath = "/Users/impt/Desktop/duke/data/duke.txt";

        int taskCount = writeFile(filePath, storeList);

        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter writer = new PrintWriter(fileWriter, true);

        //start of duke code
        String code = "";
        String input;
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

            } else if (taskDone.equals("find")) {
                for (int i = 0; i < storeList.size(); i++) {
                    if (storeList.get(i).description.contains(parts[1])) {
                        System.out.println((i + 1) + ". " + storeList.get(i).toString());
                    }
                    else {
                        System.out.println("can't find matching data sorry!");
                    }
                }
            } else if (taskDone.equals("delete")) {
                int list = Integer.parseInt(parts[1]);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(storeList.get(list-1).toString());
                deleteLine(filePath, storeList.get(list-1).storeList());
                if (storeList.get(list-1).getStatusIcon() == "NO") {
                    taskCount--;
                }
                storeList.remove(list -1);
                System.out.println("Now you have " + (taskCount) + " tasks in the list.");


            } else if (input.equals("list")) {
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
                                String deadDate = deadlineTime.replaceAll(" ", "");

                                SimpleDateFormat inputDate = new SimpleDateFormat("dd/MM/yyy HHmm");
                                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy, h:mma"); // duke to understand this format
                                Date dlInput = inputDate.parse(deadlineTime);
                                deadDate = formatter.format(dlInput);

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


                            writer.println(storeList.get(storeList.size()-1).storeList());


                            System.out.println("Got it. I've added this task: ");
                            System.out.println(storeList.get(storeList.size()-1).toString());
                            taskCount++;
                            System.out.println("Now you have " + (taskCount) + " tasks in the list.");

                        }
                    }
                    catch (ArrayIndexOutOfBoundsException | ParseException e) {
                        System.out.println("☹ OOPS!!! The description of a " + taskName + " cannot be empty.");
                    }

                }
                else {
                    try {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }

            }
        } while (!input.isEmpty());

        writer.close();


    }

    static void deleteLine(String filePath, String lineToDelete) {
        try {

            File file = new File(filePath);

            if (!file.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(file.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(lineToDelete)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!file.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(file))
                System.out.println("Could not rename file");

        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    static int writeFile(String filePath, ArrayList<Task> storeList) throws IOException {
        // write file onto list
        int taskCount =0;

        String tempStore = "";

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

                } else if (temp[0].equals("T")){

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
        return taskCount++;
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