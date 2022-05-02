//Aldan St. Omer
//Assignment 2 - COMP2232

import java.util.Random;

public class Animal
{
    protected String species;
    protected String name;
    protected int age;
    protected int hungerStatus;
    protected int healthStatus;
    protected String category;
    protected String cageID;
    protected final int MAX_FOOD_LVL = 5;
    protected final int MAX_HEALTH_LVL = 10;

    public Animal()
    {
        Random randNum = new Random();
        species ="";
        name = "";
        age = 0;
        hungerStatus = randNum.nextInt(5) + 1;
        healthStatus = randNum.nextInt(10) + 1;
        category = "";
        cageID = "";
    }//Animal Constructor

    public  void eatFood(int amount)
    {
        setHungerStatus(getHungerStatus() + amount);
    }//eatFood

    public void takeMedicine(int amount)
    {
        setHealthStatus(getHealthStatus() + amount);
    }//takeMedicine

    public void speak()
    {
        System.out.println("make noise");
    }//speak

    public String getSpecies()
    {
        return species;
    }//getSpecies

    public String getName()
    {
        return name;
    }//getName

    public int getAge()
    {
        return age;
    }//getAge

    public int getHealthStatus()
    {
        return healthStatus;
    }//getHealthStatus

    public int getHungerStatus()
    {
        return hungerStatus;
    }//getHungerStatus

    public String getCategory() {
        return category;
    }//getCategory

    public String getCageID() {
        return cageID;
    }//getCageID

    public int getMAX_FOOD_LVL()
    {
        return MAX_FOOD_LVL;
    }//getMAX_FOOD_LVL

    public int getMAX_HEALTH_LVL()
    {
        return MAX_HEALTH_LVL;
    }//getMAX_HEALTH_LVL

    public void setSpecies(String species)
    {
        this.species = species;
    }//setSpecies

    public void setName(String name)
    {
        this.name = name;
    }//setName

    public void setAge(int age)
    {
        this.age = age;
    }//setAge

    public void setCategory(String category) {
        this.category = category;
    }//setCategory

    public void setCageID(String cageID) {
        this.cageID = cageID;
    }//setCageID

    public void setHungerStatus(int hungerStatus)
    {
        this.hungerStatus = hungerStatus;
    }//setHungerStatus

    public void setHealthStatus(int healthStatus)
    {
        this.healthStatus = healthStatus;
    }//setHealthStatus
}//Animal