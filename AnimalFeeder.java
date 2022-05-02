//Aldan St. Omer
//Assignment 2 - COMP2232

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AnimalFeeder
{
    private ArrayList<Meal> feedingList;
    private ArrayList<Animal> cages;
    private Animal ani;
    private int n, m, deaths, intHa, intFr, intGr, intFi, intMe;
    private String id;
    private String type;
    private int amt;

    public AnimalFeeder(ArrayList<Animal> c)
    {
        ani = new Animal();
        feedingList = new ArrayList<Meal>();
        cages = c;
        n=0;
        m=0;
        deaths=0;
        intHa =0;
        intFr =0;
        intGr =0;
        intFi =0;
        intMe =0;
        id="";
        type="";
        amt=0;
    }//AnimalFeeder Constructor

    public void addMeal()
    {
        Meal newMeal = new Meal();
        newMeal.setCageID(getId());
        newMeal.setFoodType(getType());
        newMeal.setFoodAmt(getAmt());
        feedingList.add(newMeal);
    }//addMeal

    public void reportFunc(JTextArea newArea)
    {
        java.util.Date date = new java.util.Date();
        newArea.append(String.valueOf(date));
        newArea.append("\n\nAnimals Fed: " + feedingList.size() );
        newArea.append("\nOK: " + (feedingList.size() - deaths));
        newArea.append("\nDeaths: " + deaths);
        newArea.append("\n\nOverfed");
        if (deaths>0)
        {
            for (int index=0; index<cages.size(); index++)
            {
                if (cages.get(index).getHungerStatus() > ani.getMAX_FOOD_LVL())
                {
                    int index2 =0;

                    while (feedingList.get(index2).getCageID() != cages.get(index).getCageID())
                    {
                        index2++;
                    }//endIf
                    int originStatus = cages.get(index).hungerStatus - feedingList.get(index2).getFoodAmt();
                    newArea.append("\n" + cages.get(index).getCageID() +" " + cages.get(index).getName() + " " +
                            cages.get(index).getSpecies() + " || Original Hunger Status: " +
                            originStatus + ", Food Given: " + feedingList.get(index2).getFoodAmt() +
                            " " + feedingList.get(index2).getFoodType());
                }//endIf
            }//endFor
        }//endIf
        else
        {
            newArea.append("\n0 animals were overfed.");
        }//endElse
    }//reportFunc

    public void printFeedingReport() throws IOException
    {
        java.util.Date date = new java.util.Date();
        File outFile = new File("FeedingReport.txt");
        FileWriter fileWriter = new FileWriter(outFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Feeding Report - " + date);
        printWriter.println();
        printWriter.println("Animals Fed: " + feedingList.size());
        printWriter.println("OK: " + (feedingList.size() - deaths));
        printWriter.println("Deaths: " + deaths);
        printWriter.println();
        printWriter.println("Overfed");
        if (deaths>0)
        {
            for (int index=0; index<cages.size(); index++)
            {
                if (cages.get(index).getHungerStatus() > ani.getMAX_FOOD_LVL())
                {
                    int index2 =0;

                    while (feedingList.get(index2).getCageID() != cages.get(index).getCageID())
                    {
                        index2++;
                    }//endIf
                    int originStatus = cages.get(index).hungerStatus - feedingList.get(index2).getFoodAmt();
                   printWriter.println(cages.get(index).getCageID() +" " + cages.get(index).getName() + " " +
                                        cages.get(index).getSpecies() + " || Original Hunger Status: " +
                                        originStatus + ", Food Given: " + feedingList.get(index2).getFoodAmt() +
                                        " " + feedingList.get(index2).getFoodType());
                }//endIf
            }//endFor
        }//endIf
        else
        {
            printWriter.println("0 animals were overfed.");
        }//endElse

        printWriter.println();
        fileWriter.flush();
        fileWriter.close();
    }//printFeedingReport

    public void printFeedingList() throws IOException
    {
        java.util.Date date = new java.util.Date();
        Animal tempAni = new Animal();
        File outFile = new File("FeedingList.txt");
        FileWriter fileWriter = new FileWriter(outFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Feeding List - " + date);
        printWriter.println();
        printWriter.println("(A) African Savanna");
        for(int a =0; a<feedingList.size(); a++)
        {
            if (feedingList.get(a).getCageID().contains("A"))
            {
                tempAni = search(a);
                printWriter.println(tempAni.getName() + " " + tempAni.getSpecies() + "\t" + feedingList.get(a).getFoodAmt() + " " + feedingList.get(a).getFoodType());
                n++;
            }//endIf
        }//endFor
        if (n==0)
        {
            printWriter.println("[No Animals Fed]");
        }//endIf
        else
        {
            n=0;
        }//endElse
        printWriter.println();

        printWriter.println("Food Summary");
        for(int a =0; a<feedingList.size(); a++)
        {
            if (feedingList.get(a).getCageID().contains("A"))
            {
                sumFunc(a);
                m++;
            }//endIf
        }//endFor
        if (m==0)
        {
            printWriter.println("[No Units]");
        }//endIf
        else
        {
            m=0;
        }//endElse
        if (intHa >0)
        {
            printWriter.println(intHa + " Hay");
        }//endIf
        if (intFr >0)
        {
            printWriter.println(intFr + " Fruit");
        }//endIf
        if (intGr >0)
        {
            printWriter.println(intGr + " Grain");
        }//endIf
        if (intFi >0)
        {
            printWriter.println(intFi + " Fish");
        }//endIf
        if (intMe >0)
        {
            printWriter.println(intMe + " Meat");
        }//endIf
        intHa=0;
        intFr=0;
        intGr=0;
        intFi=0;
        intMe=0;
        printWriter.println();

        printWriter.println("(B) Amazonian Jungle");
        for(int a =0; a<feedingList.size(); a++)
        {
            if (feedingList.get(a).getCageID().contains("B"))
            {
                tempAni = search(a);
                printWriter.println(tempAni.getName() + " " + tempAni.getSpecies() + "\t" + feedingList.get(a).getFoodAmt() + " " + feedingList.get(a).getFoodType());
                n++;
            }//endIf
        }//endFor
        if (n==0)
        {
            printWriter.println("[No Animals Fed]");
        }//endIf
        else
        {
            n=0;
        }//endElse
        printWriter.println();

        printWriter.println("Food Summary");
        for(int a =0; a<feedingList.size(); a++)
        {
            if (feedingList.get(a).getCageID().contains("B"))
            {
                sumFunc(a);
                m++;
            }//endIf
        }//endFor
        if (m==0)
        {
            printWriter.println("[No Units]");
        }//endIf
        else
        {
            m=0;
        }//endElse
        if (intHa >0)
        {
            printWriter.println(intHa + " Hay");
        }//endIf
        if (intFr >0)
        {
            printWriter.println(intFr + " Fruit");
        }//endIf
        if (intGr >0)
        {
            printWriter.println(intGr + " Grain");
        }//endIf
        if (intFi >0)
        {
            printWriter.println(intFi + " Fish");
        }//endIf
        if (intMe >0)
        {
            printWriter.println(intMe + " Meat");
        }//endIf
        intHa=0;
        intFr=0;
        intGr=0;
        intFi=0;
        intMe=0;
        printWriter.println();

        printWriter.println("(C) Eurasian Wilds");
        for(int a =0; a<feedingList.size(); a++)
        {
            if (feedingList.get(a).getCageID().contains("C"))
            {
                tempAni = search(a);
                printWriter.println(tempAni.getName() + " " + tempAni.getSpecies() + "\t" + feedingList.get(a).getFoodAmt() + " " + feedingList.get(a).getFoodType());
                n++;
            }//endIf
        }//endFor
        if (n==0)
        {
            printWriter.println("[No Animals Fed]");
        }//endIf
        else
        {
            n=0;
        }//endElse
        printWriter.println();
        printWriter.println("Food Summary");
        for(int a =0; a<feedingList.size(); a++)
        {
            if (feedingList.get(a).getCageID().contains("C"))
            {
                sumFunc(a);
                m++;
            }//endIf
        }//endFor
        if (m==0)
        {
            printWriter.println("[No Units]");
        }//endIf
        else
        {
            m=0;
        }//endElse
        if (intHa >0)
        {
            printWriter.println(intHa + " Hay");
        }//endIf
        if (intFr >0)
        {
            printWriter.println(intFr + " Fruit");
        }//endIf
        if (intGr >0)
        {
            printWriter.println(intGr + " Grain");
        }//endIf
        if (intFi >0)
        {
            printWriter.println(intFi + " Fish");
        }//endIf
        if (intMe >0)
        {
            printWriter.println(intMe + " Meat");
        }//endIf
        intHa=0;
        intFr=0;
        intGr=0;
        intFi=0;
        intMe=0;
        printWriter.println();

        printWriter.println("(D) Frozen Tundra");
        for(int a =0; a<feedingList.size(); a++)
        {
            if (feedingList.get(a).getCageID().contains("D"))
            {
                tempAni = search(a);
                printWriter.println(tempAni.getName() + " " + tempAni.getSpecies() + "\t" + feedingList.get(a).getFoodAmt() + " " + feedingList.get(a).getFoodType());
                n++;
            }//endIf
        }//endFor
        if (n==0)
        {
            printWriter.println("[No Animals Fed]");
        }//endIf
        else
        {
            n=0;
        }//endElse
        printWriter.println();

        printWriter.println("Food Summary");
        for(int a =0; a<feedingList.size(); a++)
        {
            if (feedingList.get(a).getCageID().contains("D"))
            {
                sumFunc(a);
                m++;
            }//endIf
        }//endFor
        if (m==0)
        {
            printWriter.println("[No Units]");
        }//endIf
        else
        {
            m=0;
        }//endElse
        if (intHa >0)
        {
            printWriter.println(intHa + " Hay");
        }//endIf
        if (intFr >0)
        {
            printWriter.println(intFr + " Fruit");
        }//endIf
        if (intGr >0)
        {
            printWriter.println(intGr + " Grain");
        }//endIf
        if (intFi >0)
        {
            printWriter.println(intFi + " Fish");
        }//endIf
        if (intMe >0)
        {
            printWriter.println(intMe + " Meat");
        }//endIf
        intHa=0;
        intFr=0;
        intGr=0;
        intFi=0;
        intMe=0;
        printWriter.println();

        fileWriter.flush();
        fileWriter.close();
    }//printFeedingList

    public void sumFunc(int a)
    {
        if (feedingList.get(a).getFoodType().contains("Hay"))
        {
            intHa= intHa + feedingList.get(a).getFoodAmt();
        }//endIf
        if (feedingList.get(a).getFoodType().contains("Fruit"))
        {
            intFr= intFr + feedingList.get(a).getFoodAmt();
        }//endIf
        if (feedingList.get(a).getFoodType().contains("Grain"))
        {
            intGr= intGr + feedingList.get(a).getFoodAmt();
        }//endIf
        if (feedingList.get(a).getFoodType().contains("Fish"))
        {
            intFi= intFi + feedingList.get(a).getFoodAmt();
        }//endIf
        if (feedingList.get(a).getFoodType().contains("Meat"))
        {
            intMe= intMe + feedingList.get(a).getFoodAmt();
        }//endIf
    }//sumFunc

    public Animal search(int a)
    {
        int y =0;
        while (feedingList.get(a).getCageID() != cages.get(y).getCageID())
        {
            y++;
        }//endWhile
        return cages.get(y);
    }//search

    public  void simFeeding() throws OverFeedingException
    {
        for (int index=0; index< feedingList.size(); index++)
        {
            int index2 =0;

            while (feedingList.get(index).getCageID() != cages.get(index2).getCageID())
            {
                index2++;
            }//endIf

            cages.get(index2).setHungerStatus(cages.get(index2).getHungerStatus() + feedingList.get(index).getFoodAmt());

            if (cages.get(index2).getHungerStatus() > ani.getMAX_FOOD_LVL())
            {
                deaths++;
                //throw new OverFeedingException();
            }//endIf
        }//endFor
    }//simFeeding

    public String getId()
    {
        return id;
    }//getId

    public String getType()
    {
        return type;
    }//getType

    public int getAmt()
    {
        return amt;
    }//getAmt

    public void setId(String id)
    {
        this.id = id;
    }//setId

    public void setType(String type)
    {
        this.type = type;
    }//setType

    public void setAmt(int amt)
    {
        this.amt = amt;
    }//setAmt
}//AnimalFeeder
