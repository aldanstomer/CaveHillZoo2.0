//Aldan St. Omer
//Assignment 2 - COMP2232

public class OverdosingException extends Exception
{
    public OverdosingException()
    {
        super();
    }//OverdosingExceptionConstructor

    public String toString()
    {
        return("Animal overdosed and died.");
    }//toString
}//OverdosingException
