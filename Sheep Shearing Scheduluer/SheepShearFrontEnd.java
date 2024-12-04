/*
 * Written By: Sofiane Lachab
 */
import java.util.Scanner;//for keyboard
/**
 * Front-end for the Sheep Shearing Scheduler project. Prompts the
 * user to provide a file that will be sent to the back end for
 * processing, then calls the necessary functions to print out the
 * optimal sheep shearing schedule.
 */
public class SheepShearFrontEnd 
{
    private static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("Welcome to the Sheep Shearing Scheduler Program");
        System.out.println("Please enter a file to read the sheep data.");
        String input = keyboard.nextLine();//file name
        while(!ShearScheduler.readFile(input))//until a valid file
        {
            System.out.println("Sorry, that file name did not work."
                + "Please enter a new file name.");
            input = keyboard.nextLine();//new file name
        }
        do
        {
            //add sheep that have arrived and print out the next to shear
            ShearScheduler.addSheep(ShearScheduler.getCurrentTime());
            System.out.println(ShearScheduler.shearSheep());
        }
        while((!ShearScheduler.isDone()));//until all the sheep are done
    }
    
}
