//Aldan St. Omer
//Assignment 2 - COMP2232

import java.util.Scanner;

public class ZooKeeper
{
    private Zoo newZoo;
    private String name;

    public ZooKeeper()
    {
        name = "John/Jane Doe";
        newZoo = new Zoo();
    }//ZooKeeper Constructor

    public Zoo getNewZoo()
    {
        return newZoo;
    }//getNewZoo

    public void setNewZoo(Zoo newZoo)
    {
        this.newZoo = newZoo;
    }//setNewZoo

    public String getName()
    {
        return name;
    }//getZooKeeperName

    public void setName(String name)
    {
        this.name = name;
    }//setZooKeeperName
}//ZooKeeper
