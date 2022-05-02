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

public class AnimalHealer
{
    private ArrayList<Prescription> healingList;
    private ArrayList<Animal> cages;
    private Animal ani;
    private int n,m;
    private int sumMedTtl;
    private int intHe;
    private int intOm;
    private int intCa;
    private int deaths;
    private String id;
    private String type;
    private int amt;

    public AnimalHealer(ArrayList<Animal> c)
    {
        ani = new Animal();
        healingList = new ArrayList<Prescription>();
        cages = c;
        n=0;
        m=0;
        deaths=0;
        sumMedTtl =0;
        intHe =0;
        intOm =0;
        intCa =0;
    }//AnimalHealer Constructor

    public void addPrescription()
    {
        Prescription newPresc = new Prescription();
        newPresc.setCageID(getId());
        newPresc.setMedType(getType());
        newPresc.setUnitsOfMed(getAmt());
        healingList.add(newPresc);
    }//addPrescription

    public void reportFunc(JTextArea newArea)
    {

        newArea.append(String.valueOf(java.time.LocalDate.now()));
        newArea.append("\n\nAnimals Medicated: " + healingList.size() );
        newArea.append("\nOK: " + (healingList.size() - deaths));
        newArea.append("\nDeaths: " + deaths);
        newArea.append("\n\nOverdosed");
        if (deaths>0)
        {
            for (int index=0; index<cages.size(); index++)
            {
                if (cages.get(index).getHealthStatus() > ani.getMAX_HEALTH_LVL())
                {
                    int index2 =0;

                    while (healingList.get(index2).getCageID() != cages.get(index).getCageID())
                    {
                        index2++;
                    }//endIf
                    int originStatus = cages.get(index).getHealthStatus() - healingList.get(index2).getUnitsOfMed();
                    newArea.append("\n" + cages.get(index).getCageID() +" " + cages.get(index).getName() + " " +
                            cages.get(index).getSpecies() + " || Original Health Status: " +
                            originStatus + ", Medicine Given: " + healingList.get(index2).getUnitsOfMed() +
                            " " + healingList.get(index2).getMedType());
                }//endIf
            }//endFor
        }//endIf
        else
        {
            newArea.append("\n0 animals were overdosed.");
        }//endElse
    }//reportFunc

    public void printHealingReport() throws IOException
    {
        java.util.Date date = new java.util.Date();
        File outFile = new File("HealingReport.txt");
        FileWriter fileWriter = new FileWriter(outFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Healing Report - " + date);
        printWriter.println();
        printWriter.println("Animals Medicated: " + healingList.size());
        printWriter.println("OK: " + (healingList.size() - deaths));
        printWriter.println("Deaths: " + deaths);
        printWriter.println();
        printWriter.println("Overdosed");
        if (deaths>0)
        {
            for (int index=0; index<cages.size(); index++)
            {
                if (cages.get(index).getHealthStatus() > ani.getMAX_HEALTH_LVL())
                {
                    int index2 =0;

                    while (healingList.get(index2).getCageID() != cages.get(index).getCageID())
                    {
                        index2++;
                    }//endIf
                    int originStatus = cages.get(index).getHealthStatus() - healingList.get(index2).getUnitsOfMed();
                    printWriter.println(cages.get(index).getCageID() +" " + cages.get(index).getName() + " " +
                            cages.get(index).getSpecies() + " || Original Health Status: " +
                            originStatus + ", Medicine Given: " + healingList.get(index2).getUnitsOfMed() +
                            " " + healingList.get(index2).getMedType());
                }//endIf
            }//endFor
        }//endIf
        else
        {
            printWriter.println("0 animals were overdosed.");
        }//endElse
        printWriter.println();

        fileWriter.flush();
        fileWriter.close();
    }//printHealingReport

    public void printHealingList() throws Exception
    {
        java.util.Date date = new java.util.Date();
        Animal tempAni = new Animal();
        File outFile = new File("HealingList.txt");
        FileWriter fileWriter = new FileWriter(outFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Healing List - " + date);
        printWriter.println();
        printWriter.println("(A) African Savanna");
        for(int a =0; a<healingList.size(); a++)
        {
            if (healingList.get(a).getCageID().contains("A"))
            {
                    tempAni = search(a);
                    printWriter.println(tempAni.getName() + " " + tempAni.getSpecies() + " (health: " + tempAni.getHealthStatus() +
                                        ") needs " + healingList.get(n).getUnitsOfMed() + " units");
                    n++;
            }//endIf
        }//endFor
        if (n==0)
        {
            printWriter.println("[No medical attention required]");
        }//endIf
        else{
            n=0;
        }//endElse
        printWriter.println();

        printWriter.println("(B) Amazonian Jungle");
        for(int a =0; a<healingList.size(); a++)
        {
            if (healingList.get(a).getCageID().contains("B"))
            {
                tempAni = search(a);
                printWriter.println(tempAni.getName() + " " + tempAni.getSpecies() + " (health: " + tempAni.getHealthStatus() +
                                    ") needs " + healingList.get(n).getUnitsOfMed() + " units");
                n++;
            }//endIf
        }//endFor
        if (n==0)
        {
            printWriter.println("[No medical attention required]");
        }//endIf
        else{
            n=0;
        }//endElse
        printWriter.println();

        printWriter.println("(C) Eurasian Wilds");
        for(int a =0; a<healingList.size(); a++)
        {
            if (healingList.get(a).getCageID().contains("C"))
            {
                tempAni = search(a);
                printWriter.println(tempAni.getName() + " " + tempAni.getSpecies() + " (health: " + tempAni.getHealthStatus() +
                                    ") needs " + healingList.get(n).getUnitsOfMed() + " units");
                n++;
            }//endIf
        }//endFor
        if (n==0)
        {
            printWriter.println("[No medical attention required]");
        }//endIf
        else{
            n=0;
        }//endElse
        printWriter.println();

        printWriter.println("(D) Frozen Tundra");
        for(int a =0; a<healingList.size(); a++)
        {
            if (healingList.get(a).getCageID().contains("D"))
            {
                tempAni = search(a);
                printWriter.println(tempAni.getName() + " " + tempAni.getSpecies() + " (health: " + tempAni.getHealthStatus() +
                                    ") needs " + healingList.get(n).getUnitsOfMed() + " units");
                n++;
            }//endIf
        }//endFor
        if (n==0)
        {
            printWriter.println("[No medical attention required]");
        }//endIf
        else{
            n=0;
        }//endElse
        printWriter.println();
        printWriter.println("Summary of Medicine: " + sumFunc() + " units");

        fileWriter.flush();
        fileWriter.close();
    }//printHealingList

    public int sumFunc()
    {
        while (m<healingList.size())
        {
        sumMedTtl= sumMedTtl + healingList.get(m).getUnitsOfMed();
        m++;
        if (m == healingList.size())
            {
                m =0;
                return sumMedTtl;
            }//endIf
        }//endWhile
            return 0;
    }//sumFunc

    public Animal search(int a)
    {
        int y =0;
        while (healingList.get(a).getCageID() != cages.get(y).getCageID())
        {
            y++;
        }//endWhile
        return cages.get(y);
    }//search

    public  void simHealing() throws OverdosingException
    {
        for (int index=0; index< healingList.size(); index++)
        {
            int index2 =0;

            while (healingList.get(index).getCageID() != cages.get(index2).getCageID())
            {
                index2++;
            }//endIf

            cages.get(index2).setHealthStatus(cages.get(index2).getHealthStatus() + healingList.get(index).getUnitsOfMed());

            if (cages.get(index2).getHealthStatus() > ani.getMAX_HEALTH_LVL())
            {
                deaths++;
               // throw new OverdosingException();
            }//endIf
        }//endFor
    }//simHealing

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
}//AnimalHealer
