//Aldan St. Omer
//Assignment 2 - COMP2232

import javax.swing.*;

public class Welcome extends JFrame
{
    private String name;

    public Welcome()
    {
        name = "No Name";
        displayWelcome();
    }//WelcomeConstructor

    public void displayWelcome()
    {
        JFrame welcome = new JFrame();
        name = JOptionPane.showInputDialog(welcome, "Welcome new Zoo Keeper to The Cave Hill Zoo!\nHope you are ready for a good days work!\nPlease enter your preferred username: ", "WELCOME", JOptionPane.PLAIN_MESSAGE);
        if (name == null)
        {
            name = "John/Jane Doe";
        }//endIf
        if (name.isEmpty() )
        {
            name = "John/Jane Doe";
        }//endIf
    }//displayWelcome

    public void setName(String name)
    {
        this.name = name;
    }//setWelcome

    public String getName()
    {
        return name;
    }//getWelcome
}//Welcome
