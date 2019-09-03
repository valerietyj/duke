import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> storeList;
    private int currentTC = 0;

    public TaskList() {
        this.storeList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> storeList) {
        this.storeList = storeList;
    }

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

    String getTasks() {
        String store = storeList.get(storeList.size() - 1).storeList();
        return store;
    }

    String getIndexedTasks(int i) {
        String store = storeList.get(i-1).toString();
        return store;
    }


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


        void getlist() {

        if (storeList.isEmpty()) {
            System.out.println("list is empty");
        } else {
            for (int i = 0; i < storeList.size(); i++) {
                System.out.println((i + 1) + ". " + storeList.get(i).toString());
            }
        }


    }

    void done(int index) {
        String toOverwrite = storeList.get(index - 1).storeList();


        storeList.get(index - 1).markAsDone();
        String status = storeList.get(index - 1).getStatusIcon();
        System.out.println("[" + status + "] " + storeList.get(index - 1).description);

        Storage.modifyFile(toOverwrite, storeList.get(index - 1).storeList());
    }

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
