/*
 * Written By: Sofiane Lachab
 */
/**
 * Helper class for the Sheep Shearing Scheduler project.
 * Utilized to make comparable Sheep objects.
 */
public class Sheep implements Comparable<Sheep>
{
    private String name;
    private int shearingTime;//how long to shear
    private int arrivalTime;//when sheep arrived
    //default values
    private static final String DEF_NAME = "none";
    private static final int DEF_SHEARING_TIME = 1;
    private static final int DEF_ARRIVAL_TIME = 0;
    /**
     * Default constructor that sets instance data to default values.
     */
    public Sheep()
    {
        init(DEF_NAME, DEF_SHEARING_TIME, DEF_ARRIVAL_TIME);
    }
    /**
     * Parameterized constructor that sets instance data to given arguments.
     * @param aName is for the name variable
     * @param aShearingTime is for the shearingTime variable
     * @param aArrivalTime is for the arrivalTime variable
     */
    public Sheep(String aName, int aShearingTime, int aArrivalTime)
    {
        init(aName, aShearingTime, aArrivalTime);
    }
    /**
     * Instantiates the instance data after first checking if the given values
     * are valid, setting them to defaults if not.
     * @param aName is for the name variable
     * @param aShearingTime is for the shearingTime variable
     * @param aArrivalTime is for the arrivalTime variable
     */
    private void init(String aName, int aShearingTime, int aArrivalTime)
    {
        if(aName != null)
            name = aName;
        else
            init(DEF_NAME, aShearingTime, aArrivalTime);
        if(aShearingTime >= 0)
            shearingTime = aShearingTime;
        else
            init(aName, DEF_SHEARING_TIME, aArrivalTime);
        if(aArrivalTime >= 0)
            arrivalTime = aArrivalTime;
        else
            init(aName, aShearingTime, DEF_ARRIVAL_TIME);
    }
    /**
     * Getter for the name variable.
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    /**
     * Getter for the shearingTime variable.
     * @return the shearingTime
     */
    public int getShearingTime()
    {
        return shearingTime;
    }
    /**
     * Getter for the arrivalTime variable
     * @return the arrivalTime
     */
    public int getArrivalTime()
    {
        return arrivalTime;
    }
    /**
     * Setter for the name variable, first checking if the given value
     * is valid and utilizing the default value if not.
     * @param aName is for the name variable
     */
    public void setName(String aName)
    {
        if(aName != null)
            name = aName;
        else
            name = DEF_NAME;
    }
    /**
     * Setter for the shearingTime variable, first checking if the given value
     * is valid and utilizing the default value if not.
     * @param aShearingTime is for the shreaingTime
     */
    public void setShearingTime(int aShearingTime)
    {
        if(aShearingTime >= 0)
            shearingTime = aShearingTime;
        else
            shearingTime = DEF_SHEARING_TIME;
    }
    /**
     * Setter for the name arrivalTime, first checking if the given value
     * is valid and utilizing the default value if not.
     * @param aArrivalTime is for the arrivalTime
     */
    public void setArrivalTime(int aArrivalTime)
    {
        if(aArrivalTime >= 0)
            arrivalTime = aArrivalTime;
        else
            arrivalTime = DEF_ARRIVAL_TIME;
    }
    /**
     * Implementation of the compareTo() method that compares Sheep
     * based on their shearingTime first, and then by their names if
     * the times are equal.
     * @return an int representation of how the Sheep compare
     */
    public int compareTo(Sheep aSheep)
    {
        int result;
        if(this.getShearingTime() > aSheep.getShearingTime())
            result = 1;
        else if(this.getShearingTime() < aSheep.getShearingTime())
            result = -1;
        else//shearingTimes are equal
            result = (this.getName().compareTo(aSheep.getName()));
        return result;
    }
    /**
     * String representation of the sheep under thae format of 
     * "Name: <<name>>, Shearing Time: <<shearingTime>>,
     *  Arrival Time: <<arrivalTime>>"
     * @return String representation of the sheep
     */
    public String toString()
    {
        return "Name: " + name + ", Shearing Time: " + shearingTime
            + ", Arrival Time: " + arrivalTime;
    }
}