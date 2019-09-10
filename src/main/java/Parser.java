import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * class Parser make sense of user input which will pass on to the different command class if input is valid
 */
public class Parser {

    /**
     * divide input to match function that Duke has
     * @param input breakdown input to teach Duke what to do
     * @return user inputs to the different command class
     * @throws ParseException checked exception used to check if failed to parse date in the correct format [dd/mm/yyyy 0000]
     * @throws DukeException Teach Duke to deal with errors when there is incorrect inputs entered by the user.
     */

    public static Command parse(String input) throws ParseException, DukeException {

        String s = input;
        int list = 0;
        String[] parts = s.split(" ");
        String userAction = parts[0];

        if (userAction.equals("done")) {
            try {
                list = Integer.parseInt(parts[1]);
                return new doneCommand(list);
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Your index for done is missing!");
            }

        } else if (userAction.equals("find")) {

            try {
                return new findCommand(parts[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Your field for find is missing!");
            }

        } else if (userAction.equals("delete")) {
            try {
                list = Integer.parseInt(parts[1]);
                return new deleteCommand(list);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Your index for delete is missing!");
            }

        } else if (userAction.equals("list")) {

            return new List();


        } else if (userAction.equals("bye")) {

            return new ExitCommand();

        } else if (userAction.isBlank()) {

            return new errorCommand("User input cannot be empty!");
        }

        else {
            String arr[] = input.split(" ");;
            String taskdesc = "", deadlineName = "", deadlineTime = "", eventName = "", eventTime = "";
            taskdesc = input.substring(arr[0].length(), input.length());

            Scanner sc1 = new Scanner(arr[0]);
            if (sc1.hasNext("todo") || sc1.hasNext("deadline") || sc1.hasNext("event")) {
                    if (!taskdesc.isBlank()) {
                        //start of code here!
                        if (userAction.equals("deadline")) {
                            String desc[] = taskdesc.split("/by", 2);
                            deadlineName = desc[0];

                            try {
                                deadlineTime = desc[1];
                                SimpleDateFormat inputDate = new SimpleDateFormat("dd/MM/yyy HHmm");
                                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy, h:mma"); // duke to understand this format
                                Date dlInput = inputDate.parse(deadlineTime);
                                String deadDate = formatter.format(dlInput);

                                return new AddCommand("deadline", deadlineName, deadDate);

                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("☹ OOPS!!! You missed out your deadline TIMING!");
                            }

                        } else if (arr[0].equals("event")) {
                            String desc[] = taskdesc.split("/at", 2);
                            eventName = desc[0]; //event name
                            try {
                                eventTime = desc[1]; //date

                                SimpleDateFormat inputDate = new SimpleDateFormat("dd/MM/yyy HHmm");
                                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy, h:mma"); // duke to understand this format
                                Date dlInput = inputDate.parse(eventTime);
                                String eventDate = formatter.format(dlInput);
                                return new AddCommand("event", eventName, eventDate);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("☹ OOPS!!! You missed out your event TIMING!");
                            }


                        } else {
                            return new AddCommand("todo", taskdesc, null);
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! The description of a " + userAction + " cannot be empty.");

                    }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        }
        throw new DukeException("try again");

    }

}
