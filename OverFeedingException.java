//Aldan St. Omer
//Assignment 2 - COMP2232

public class OverFeedingException extends Exception
{
    public OverFeedingException()
    {
        super();
    }//OverFeedingExceptionConstructor

    public String toString()
    {
        return("Animal overfed and died.");
    }//toString
}//OverFeedingException
