/*
 * Programmer: Sofiane Lachab
 * Course: CSCE 146
 * Assignment: Vector Math Program (HW 00)
 */

import java.util.InputMismatchException; // for invalid user input
import java.util.Scanner; // to read from keyboard

public class VectorMath 
{
    /**
     * Main method that runs the looping logic for the vector math operations.
     * It will prompt the user to enter their desired function, vectors, and
     * offer them the option to terminate the program.
     * @param args is not used
     */
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in); // for user input
        int choice = 0; // represents user-selected function
        boolean goodChoice = false; // if the user's choice is valid
        boolean quit = false; // flag to quit out of while loop to end program

        do
        {
            do
            {
                try
                {
                    System.out.println("\nWelcome to Vector Operations. Please"
                    + " choose your desired function");
                    System.out.println("To add two vectors, press '1'");
                    System.out.println("To subtract two vectors, press '2'");
                    System.out.println("To find the magnitude of a vector,"
                        + " press '3'");
                    System.out.println("To quit out of the program, press"
                        + " '0'");

                    choice = keyboard.nextInt(); // only takes ints
                    keyboard.nextLine(); // clears data stream

                    if(choice == 1 || choice == 2 || choice == 3
                        || choice == 0) // if one of given options
                    {
                        goodChoice = true; // valid choice
                    }
                    else
                    {
                        System.out.println("\nSorry, that was not a valid"
                            + " option. Please try again.\n");
                    }
                }
                catch(InputMismatchException e) // if user doesn't enter an int
                {
                    System.out.println("\nSorry, that was an incorrect input."
                        + " Please try again.\n");
                    keyboard.nextLine(); // clears data stream
                }
            }
            while(!goodChoice); // until a valid choice is provided

            switch (choice)
            {
                case 1:
                {
                    int size = 0; // size of vector
                    boolean goodInt = false; // flag for valid size
                    do
                    {
                        try
                        {
                            System.out.println("How many values would you like"
                            + " in each vector?");
                        
                            size = keyboard.nextInt(); // only ints
                            keyboard.nextLine(); // clears data stream

                            if(size >= 1) // vector has length of one or more
                            {
                                goodInt = true; // valid input provided
                            }
                            else
                            {
                                goodInt = false; // invalid input provided
                                System.out.println("Sorry, that value is"
                                    + " invalid. Please try again.\n");
                            }
                        }
                        catch(InputMismatchException e) // if not an int
                        {
                            System.out.println("Sorry, please enter that value"
                                + " again.");
                                keyboard.nextLine(); // clears data stream
                        }
                    }
                    while(!goodInt); // until valid input provided
                    
                    double[] first = new double[size]; // first vector
                    double[] second = new double[size]; // second vector

                    System.out.println("Enter each value for the first array,"
                        + " pressing 'enter' after each one.");
                    for(int i = 0; i < size; i++)
                    {
                        boolean goodValue = false; // flag for valid input
                        do
                        {
                            try
                            {
                                double value = keyboard.nextDouble();
                                first[i] = value; // user input into vector

                                goodValue = true; // valid choice provided
                                keyboard.nextLine(); // clears data stream
                            }
                            catch(InputMismatchException e) // if not a double
                            {
                                System.out.println("Sorry, please enter that"
                                + " value again.");
                                keyboard.nextLine(); // clears data stream
                            }
                        }
                        while(!goodValue); // until a valid option entered
                    }

                    System.out.println("Enter each value for the second array,"
                        + " pressing 'enter' after each one.");
                    for(int i = 0; i < size; i++)
                    {
                        boolean goodValue = false; // flag for valid input
                        do
                        {
                            try
                            {
                                double value = keyboard.nextDouble(); 
                                second[i] = value; // user input into vector

                                goodValue = true; // valid choice provided
                                keyboard.nextLine(); // clears data stream
                            }
                            catch(InputMismatchException e) // if not a double
                            {
                                System.out.println("Sorry, please enter that"
                                + " value again.");
                                keyboard.nextLine(); // clears data stream
                            }
                        }
                        while(!goodValue); // until a valid opiton entered
                    }

                    double[] sum = add(first, second); // final vector
                    
                    System.out.println("Here is your resulting vector.");
                    for(int i = 0; i < sum.length; i++)
                    {
                        System.out.println(sum[i]); // prints the array
                    }

                    break;
                }

                case 2:
                {
                    int size = 0; // size of vectors
                    boolean goodInt = false; // flag for valid size
                    do
                    {
                        try
                        {
                            System.out.println("How many values would you like"
                            + " in each vector?");
                        
                            size = keyboard.nextInt(); // only ints
                            keyboard.nextLine(); // clears data stream

                            if(size >= 1) // vector has length of one or more
                            {
                                goodInt = true; // valid input
                            }
                            else
                            {
                                goodInt = false; // invalid input
                                System.out.println("Sorry, that value is"
                                    + " invalid. Please try again.\n");
                            }
                        }
                        catch(InputMismatchException e) // if not an int
                        {
                            System.out.println("Sorry, please enter that value"
                                + " again.");
                                keyboard.nextLine(); // clears data stream
                        }
                    }
                    while(!goodInt); // until valid size provided
                    
                    double[] first = new double[size]; // first vector
                    double[] second = new double[size]; // second vector

                    System.out.println("Enter each value for the first array,"
                        + " pressing 'enter' after each one.");
                    for(int i = 0; i < size; i++)
                    {
                        boolean goodValue = false; // flag for valid input
                        do
                        {
                            try
                            {
                                double value = keyboard.nextDouble(); 
                                first[i] = value; // user input into vector

                                goodValue = true; // valid choice provided
                                keyboard.nextLine(); // clears data stream
                            }
                            catch(InputMismatchException e) // if not a double
                            {
                                System.out.println("Sorry, please enter that"
                                    + " value again.");
                                keyboard.nextLine(); // clears data stream
                            }
                        }
                        while(!goodValue); // until valid option provided
                    }

                    System.out.println("Enter each value for the second array,"
                        + " pressing 'enter' after each one.");
                    for(int i = 0; i < size; i++)
                    {
                        boolean goodValue = false; // flag for valid input
                        do
                        {
                            try
                            {
                                double value = keyboard.nextDouble(); 
                                second[i] = value; // input into the vector

                                goodValue = true; // valid choice provided
                                keyboard.nextLine(); // clears data stream
                            }
                            catch(InputMismatchException e) // if not a double
                            {
                                System.out.println("Sorry, please enter that"
                                    + " value again.");
                                    keyboard.nextLine(); // clears data stream
                            }
                        }
                        while(!goodValue); // until valid option provided
                    }

                    double[] difference = sub(first, second); // final vector
                    
                    System.out.println("Here is your resulting vector.");
                    for(int i = 0; i < difference.length; i++)
                    {
                        System.out.println(difference[i]); // prints the array
                    }

                    break;
                }
                
                case 3:
                {
                    int size = 0; // size of vector
                    boolean goodInt = false; // flag for valid size
                    do
                    {
                        try
                        {
                            System.out.println("How many values would you like"
                            + " in your vector?");
                        
                            size = keyboard.nextInt(); // only ints
                            keyboard.nextLine(); // clears data stream

                            if(size >= 1) // vector has length of one or more
                            {
                                goodInt = true; // valid input provided
                            }
                            else
                            {
                                goodInt = false; // invalid input
                                System.out.println("Sorry, that value is"
                                    + " invalid. Please try again.\n");
                            }
                        }
                        catch(InputMismatchException e) // if not an int
                        {
                            System.out.println("Sorry, please enter that value"
                                + " again.");
                                keyboard.nextLine(); // clears data stream
                        }
                    }
                    while(!goodInt); // until valid size provided

                    
                    double[] vector = new double[size]; // user's vector

                    System.out.println("Enter each value for the array,"
                        + " pressing 'enter' after each one.");
                    for(int i = 0; i < size; i++)
                    {
                        boolean goodValue = false; // flag for valid input
                        do
                        {
                            try
                            {
                                double value = keyboard.nextDouble();
                                vector[i] = value; // input into the vector

                                goodValue = true; // valid choice provided
                                keyboard.nextLine(); // clears data stream
                            }
                            catch(InputMismatchException e) // if not a double
                            {
                                System.out.println("Sorry, please enter that"
                                    + " value again.");
                                keyboard.nextLine(); // clears data stream
                            }
                        }
                        while(!goodValue); // until valid input provided
                    }

                    double answer = magnitude(vector); // resulting magnitude
                    
                    System.out.println("Here is your resulting magnitude.");
                    System.out.println(answer); // prints the magnitude

                    break;
                }
                case 0:
                {
                    quit = true; // flag to terminate program
                    System.out.println("\nThank you for using Vector" 
                        + " Operations. Goodbye.");
                    break;
                }
                default:
                    break;
            }
        }
        while(!quit); // until user chooses to terminate

        keyboard.close(); // prevent data leak
        System.exit(0); // terminate program
    }


    /**
     * Computes the sum between two, user-provided vectors by 
     * adding their parallel components and producing a new vector
     * with those resulting sums.
     * @param x is the array that represents the first user-provided vector
     * that will be added with the second
     * @param y is the array that represents the second user-provided vector
     * that will be added with the first
     * @return result is new array that represents the sum vector from
     * the two user-provided vectors
     */
    static double[] add(double[] x, double[] y)
    {
        double[] result = new double[x.length]; // returned vector
        
        for(int i = 0; i < x.length; i++)
        {
            result[i] = x[i] + y[i]; // adds parallel elements
        }

        return result; // returns new vector
    }

    /**
     * Computes the difference between two, user-provided vectors by 
     * subtracting their parallel components and producing a new vector
     * with those resulting differences.
     * @param x is the array that represents the first user-provided vector
     * that will be subtracted with the second
     * @param y is the array that represents the second user-provided vector
     * that will be subtracted from the first
     * @return result is new array that represents the difference vector from
     * the two user-provided vectors
     */
    static double[] sub(double[] x, double[] y)
    {
        double[] result = new double[x.length]; // returned vector
        
        for(int i = 0; i < x.length; i++)
        {
            result[i] = x[i] - y[i]; // subtracts parallel elements
        }

        return result; // returns new vector
    }

    /**
     * Calculates the magnitude of the user-provided vector by taking its
     * values, summing their squares, and square rooting the sum.
     * @param x is the array that represents the user-provided vector
     * @return result is the value that represents the vector's magnitude
     */
    static double magnitude (double[] x)
    {
        double result; // returned magnitude
        
        double sum = 0; // sum of squares
        for(int i = 0; i < x.length; i++)
        {
            double square = x[i] * x[i]; // squares elements
            sum += square; // sums the squares
        }
        result = Math.sqrt(sum); // square roots the sum

        return result; // returns magnitude
    }
}