//Aldan St. Omer
//Assignment 2 - COMP2232

import java.util.ArrayList;
import javax.swing.*;

public class Zoo
{
    private ArrayList<Animal> cages;
    private AnimalFeeder newAF;
    private AnimalHealer newAH;
    private JTextArea newFeedJTA;
    private JTextArea newHealJTA;

    public Zoo()
    {
        cages = new ArrayList<Animal>();
        newAF = new AnimalFeeder(cages);
        newAH = new AnimalHealer(cages);
        newFeedJTA = new JTextArea();
        newHealJTA = new JTextArea();
    }//Zoo Constructor

    public int cageSearch(String cageID)
    {
        for (int index=0; index< cages.size(); index++)
        {
            if (cages.get(index).getCageID() == cageID)
            {
                return index;
            }//endIf
        }//endFor

        return -1;
    }//cageSearch

    public void addAnimal(Animal a)
    {
        cages.add(a);
    }//addAnimal

    public void showAnimals()
    {
        if (cages.size() == 0)
        {
            System.out.println("There are no animals at the Zoo today.");
        }//endIf
        else
        {
            //loop and print each element in the arraylist, display all animal details on one line
            System.out.println("There are animals at the Zoo today!");
            for (int index=0; index< cages.size(); index++)
            {
                System.out.println("Species: " + cages.get(index).getSpecies() + " | Name: " + cages.get(index).getName() + " | Age: " + cages.get(index).getAge() +
                        " | Hunger Level: " + cages.get(index).getHungerStatus() + " | Health Level: " + cages.get(index).getHealthStatus());
            }//endFor
        }//endElse

        System.out.println();
    }//showAnimals

    public Animal getAnimal(String cageID)
    {
        //IMPORTANT-Error checking for empty cage needed exception/assertions
        int fetchedAnimal;
        fetchedAnimal = cageSearch(cageID);
        if(fetchedAnimal == -1)
        {
            System.out.println("The Cage is Empty.");
        }//endIf

        return cages.get(fetchedAnimal);
    }//getAnimal

    public ArrayList<Animal> getCages()
    {
        return cages;
    }//getCages

    public JTextArea getNewFeedJTA()
    {
        return newFeedJTA;
    }//getNewFeedJTA

    public JTextArea getNewHealJTA()
    {
        return newHealJTA;
    }//getNewHealJTA

    public AnimalFeeder getNewAF()
    {
        return newAF;
    }//getNewAF

    public AnimalHealer getNewAH()
    {
        return newAH;
    }//getNewAH

    public void setNewFeedJTA(JTextArea newJTA)
    {
        this.newFeedJTA = newJTA;
    }//setNewFeedJTA

    public void setNewHealJTA(JTextArea newHealJTA)
    {
        this.newHealJTA = newHealJTA;
    }//setNewHealJTA

    public void setNewAF(AnimalFeeder newAF)
    {
        this.newAF = newAF;
    }//setNewAF

    public void setNewAH(AnimalHealer newAH)
    {
        this.newAH = newAH;
    }//setNewAH

    public void removeAnimal(String cageID)
    {
        //IMPORTANT-Error checking for empty cage needed with exception/assertions
        int fetchedAnimal;
        fetchedAnimal = cageSearch(cageID);
        if(fetchedAnimal == -1)
        {
            System.out.println("The Cage is Empty.");
        }//endIf

        cages.remove(fetchedAnimal);
    }//removeAnimal

    public void printHungerReport()
    {
        newAF.reportFunc(newFeedJTA);
    }//printHungerReport

    public void printHealthReport()
    {
       newAH.reportFunc(newHealJTA);
    }//printHealthReport

    public int getCageSize()
    {
        return cages.size();
    }//getCageSize

    public Animal getAnimal(int n)
    {
        return cages.get(n);
    }//getAnimal
}//Zoo
