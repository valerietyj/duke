import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DukeTest {
    @Test
    public void taskTest() throws ParseException, DukeException {

        Parser parse = new Parser();
        // parse.parse("hello");
        // assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        // assertThrows(DukeException.class, (Executable) new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));

        //task todo
        parse.parse("todo test");
        todo todo1 = new todo("test");
        assertEquals("[T][NO] test", todo1.toString());

        // task deadline
        parse.parse("deadline work /by 23/09/2019 0800");
        Deadline dl1 = new Deadline("work", "23 Sep 2019, 8:00am");
        assertEquals("[D][NO] work (by: 23 Sep 2019, 8:00am)", dl1.toString());

        //task event
        parse.parse("event interview /at 10/09/2019 1430");
        Events e1 = new Events("interview", "10 Sep 2019, 2:30pm");
        assertEquals("[E][NO] interview (at: 10 Sep 2019, 2:30pm)", e1.toString());

        // event mark as done
        e1.markAsDone();
        assertEquals("[E][YES] interview (at: 10 Sep 2019, 2:30pm)",e1.toString());

    }





}
