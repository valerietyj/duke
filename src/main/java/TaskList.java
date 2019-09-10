import java.util.ArrayList;

/**
 * stores the tasks of user input
 */
public class TaskList {
    private ArrayList<Task> storeList;
    private int currentTC = 0;

    /**
     * contains the task list e.g., it has operations to add/delete tasks in the list
     */
    public TaskList() {
        this.storeList = new ArrayList<>();
    }

    /**
     *
     * @param storeList is the ArrayList used to store what kind of tasks, its description and date of TaskList
     */
    public TaskList(ArrayList<Task> storeList) {
        this.storeList = storeList;
    }

    /**
     * gets current tasks count on list
     * @return the total number of incomplete tasks in the list
     */
    int getTC() { //get task count
        currentTC = 0;
        for (int i = 0; i < storeList.size(); i++) {
            if (Integer.parseInt(storeList.get(i).getStatusInt()) == 0)
            {
                currentTC++;
            }
        }
        return (currentTC);
    }

    /**
     * Get the last tasks in storeList
     * @return the string format used for output purpose of adding and deleting list e.g. [T] [YES] write book
     */
    String getTasks() {
        String store = storeList.get(storeList.size() - 1).storeList();
        return store;
    }

  /*  String getIndexedTasks(int i) {
        String store = storeList.get(i-1).toString();
        return store;
    }*/

    /**
     *
     * add class store user input into TaskList when they key in a tasks to do based on these parameters:
     * @param code is either todo, deadline or event
     * @param desc describes the type of tasks / event that user has
     * @param date is used for deadline and event.
     *             The specified format for deadline is deadline [task desc] /by dd/mm/yyyy 0000 (0000 is time in 24-hour format)
     *             The specified format for deadline is event [task desc] /at dd/mm/yyyy 0000 (0000 is time in 24-hour format)
     */
    void add (String code, String desc, String date) {
        currentTC = getTC();

        if (code.equals("deadline")) {
            storeList.add(new Deadline(desc, date));
            currentTC++;
        }
        else if (code.equals("event")) {
            storeList.add(new Events(desc, date));
            currentTC++;
        }
        else {
            storeList.add(new todo(desc));
            currentTC++;
        }

        System.out.println("Got it. I've added this task: ");
        System.out.println(storeList.get(storeList.size() - 1).toString());
        System.out.println("Now you have " + currentTC + " tasks in the list.");
    }

    /**
     * when user keys in "list" as input, will print out entire list in storeList
     */
    void getlist() {

        if (storeList.isEmpty()) {
            System.out.println("list is empty");
        } else {
            for (int i = 0; i < storeList.size(); i++) {
                System.out.println((i + 1) + ". " + storeList.get(i).toString());
            }
        }


    }

    /**
     * if user input is "done", will get the index and mark the status of that index in the list and file as done
     * @param index is the position of the tasks in the list
     */
    void done(int index) {
        String toOverwrite = storeList.get(index - 1).storeList();


        storeList.get(index - 1).markAsDone();
        String status = storeList.get(index - 1).getStatusIcon();
        System.out.println("[" + status + "] " + storeList.get(index - 1).description);

        Storage.modifyFile(toOverwrite, storeList.get(index - 1).storeList());
    }

    /**
     * if user input is "find", will find the tasks that match user input
     * @param word is the description of tasks that user would like to find
     */
    void find (String word) {
        int print = 0;
        for (int i = 0; i < storeList.size(); i++) {
            if (storeList.get(i).description.contains(word)) {
                System.out.println((i + 1) + ". " + storeList.get(i).toString());
                print++;
            }
        }
        if (print == 0) {
            System.out.println("can't find matching data sorry!");
        }
    }

    /**
     * used to delete the tasks in the list and on file
     * @param index identifies the position of the tasks in the list
     */
    void delete (int index) {
        currentTC = getTC();

        System.out.println("Noted. I've removed this task: ");
        System.out.println(storeList.get(index-1).toString());
        Storage.deleteLine(storeList.get(index - 1).storeList());

        if (storeList.get(index - 1).getStatusIcon() == "NO") {
            currentTC--;
        }

        storeList.remove(index-1);

        System.out.println("Now you have " + (currentTC) + " tasks in the list.");
    }

}
