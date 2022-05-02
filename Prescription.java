//Aldan St. Omer
//Assignment 2 - COMP2232


public class Prescription
{
    private String cageID;
    private String medType;
    private int unitsOfMed;

    public Prescription()
    {
        cageID = "";
        medType = "";
        unitsOfMed = 0;
    }//Prescription Constructor

    public void setMedType(String medType)
    {
        this.medType = medType;
    }//setMedType

    public void setCageID(String cageID)
    {
        this.cageID = cageID;
    }//setCageID

    public void setUnitsOfMed(int unitsOfMed)
    {
        this.unitsOfMed = unitsOfMed;
    }//setUnitsOfMed

    public String getMedType()
    {
        return medType;
    }//getMedType

    public String getCageID()
    {
        return cageID;
    }//getCageID

    public int getUnitsOfMed()
    {
        return unitsOfMed;
    }//getUnitsOfMed
}//Prescription
