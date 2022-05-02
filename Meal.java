//Aldan St. Omer
//Assignment 2 - COMP2232

public class Meal
{
    private String cageID;
    private String foodType;
    private int foodAmt;

    public Meal()
    {
        cageID = "";
        foodType = "";
        foodAmt = 0;
    }//Meal Constructor

    public void setCageID(String cageID)
    {
        this.cageID = cageID;
    }//setCageID

    public void setFoodAmt(int foodAmt)
    {
        this.foodAmt = foodAmt;
    }//setFoodAmt

    public void setFoodType(String foodType)
    {
        this.foodType = foodType;
    }//setFoodType

    public String getCageID()
    {
        return cageID;
    }//getCageID

    public int getFoodAmt()
    {
        return foodAmt;
    }//getFoodAmt

    public String getFoodType()
    {
        return foodType;
    }//getFoodType
}//Meal
