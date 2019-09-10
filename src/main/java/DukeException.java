/**
 * throws custom exception
 */
class DukeException extends Exception
{
    /**
     * custom exception, manages error and exception not under runtime / IO exceptions
     * @param s string to be printed when there is exception
     */
    public DukeException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }

}