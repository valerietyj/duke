import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * has functions to modify, read from or write to file
 */
public class Storage {
    private static String new_filePath;

    /**
     *
     * @param filePath is the destination that want file to be read from / write to
     */
    Storage (String filePath) {
       new_filePath = filePath;
    }

    /**
     * when user add tasks to list, at the same time will write to file
     * @param string string format will be written in the format of Task.storeList() format
     */
    static void writeToFile(String string){

        File file = new File(new_filePath);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter writer = new PrintWriter(fileWriter, true);
            writer.println(string);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //for markAsDone

    /**
     * when user mark tasks as done, will update both list and file
     * @param oldString is the task that wasn't mark as done yet
     * @param newString is the task that have already been marked as done
     */
    static void modifyFile(String oldString, String newString) {
        File fileToModify = new File(new_filePath);
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
        } finally {
            try {
                read.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * when deleting task in list, also have to update the .txt file
     * @param lineToDelete find exact line in the file to delete
     */
    static void deleteLine(String lineToDelete) {
        try {

            File file = new File(new_filePath);

            if (!file.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(file.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(new_filePath));
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

    /**
     * Load the data from the hard disk when Duke starts up
     * @return all tasks from file to list on duke
     * @throws IOException -cause-
     */
    static ArrayList<Task> load() throws IOException {
        // write file onto list
        int taskCount =0;
        ArrayList<Task> storeList = new ArrayList<>();

        String tempStore = "";

        try {
            Scanner inFile = new Scanner(new File(new_filePath));
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



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return storeList;
    }

}
