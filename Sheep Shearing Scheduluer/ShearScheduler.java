/*
 * Written By: Sofiane Lachab
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Utilizes a min heap data structure to manage Sheep objects.
 * Also reads the user-inputted file create the Sheep objects.
 * Acts as the back end to the Sheep Shear Scheduler project.
 */
public class ShearScheduler 
{
    //one heap for Sheep to shear after arrival, one saying when they arrive
    private static MinHeap<Sheep> SheepToShear = new MinHeap<Sheep>();
    private static MinHeap<Sheep> SheepArriving = new MinHeap<Sheep>();
    private static Sheep CurrentSheep;//currently being sheared
    private static int currentTime = 0;//time that has passed
    /**
     * Getter for the CurrentSheep variable.
     * @return the CurrentSheep
     */
    public static Sheep getCurrentSheep()
    {
        return CurrentSheep;
    }
    /**
     * Getter for the currentTime variable.
     * @return the currentTime
     */
    public static int getCurrentTime()
    {
        return currentTime;
    }
    /**
     * Reads the user inputted file to create the Sheep objects, only
     * interpreting correctly formatted lines. If the file cannot be found
     * returns false, and true if it was successfully found and read.
     * @param input is the String representing the file name
     * @return boolean representing if the read was successful
     */
    public static boolean readFile(String input)
    {
        boolean fileRead;//flag for file found
        try
        {
            Scanner reader = new Scanner(new File(input));//to read file
            while(reader.hasNextLine())//until end of file
            {
                String line = reader.nextLine();//line by line
                if(line.matches(".*\t.*\t.*"))//only proper format
                {
                    String[] parts = line.split("\t");
                    //swap arrival and sharing because compareTo() uses shearing
                    Sheep aSheep = new Sheep(parts[0], Integer.parseInt(parts[2]), 
                        Integer.parseInt(parts[1]));
                    SheepArriving.add(aSheep);
                }
            }
            reader.close();//prevent data leak
            fileRead = true;//successful
        }
        catch(FileNotFoundException e)
        {
            fileRead = false;
        }
        return fileRead;
    }
    /**
     * Sets the given Sheep to the CurrentSheep if there isn't one, or adds
     * it to the heap of Sheep if there is a CurrentSheep.
     * @param aSheep is the Sheep to add
     */
    public static void addSheep(int currentTime)
    {
        //use getShearingTime() because that is arrival time from the file
        while(SheepArriving.peek() != null 
            && SheepArriving.peek().getShearingTime() <= currentTime)
        {
            Sheep temp = SheepArriving.remove();
            //swap arrival and shearing time back
            int realArrival = temp.getShearingTime();
            temp.setShearingTime(temp.getArrivalTime());
            temp.setArrivalTime(realArrival);
            //ready for shear
            SheepToShear.add(temp);
        }
        if(CurrentSheep == null)
            CurrentSheep = SheepToShear.remove();
    }
    /**
     * Shears the CurrentSheep, increasing the currentTime by
     * the amount it took to finish the Sheep. Then replaces the
     * CurrentSheep with the next Sheep to be sheared.
     */
    public static Sheep shearSheep()
    {
        currentTime += CurrentSheep.getShearingTime();
        //store sheared sheep
        Sheep ret = CurrentSheep;
        // add new sheep
        CurrentSheep = null;
        return ret;
    }
    /**
     * Heklper method that checks if there are any sheep
     * that have arrived and are ready to shear.
     * @return boolean representing if a sheep is ready to shear
     */
    public static boolean sheepAvailable()
    {
        if(SheepToShear.peek() != null || CurrentSheep != null)
            return true;
        else
            return false;
    }
    /**
     * Checks if the Sheep are done by seeing if the CurrentSheep is null.
     * @return boolean representing if the Sheep are finished
     */
    public static boolean isDone()
    {
        if(CurrentSheep == null && SheepArriving.peek() == null
            && SheepToShear.peek() == null)
            return true;
        else
            return false;
    }
}
