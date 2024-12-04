/*
 * Written By: Sofiane Lachab
 */
import java.util.Scanner;//keyboard input
import java.util.InputMismatchException;
import java.io.File;//file objects
import java.io.FileNotFoundException;
import java.io.FileWriter;//writing to file
import java.io.IOException;

/**
 * This class utilizes an array of linked lists that store info
 * of Task objects. It allows the user to add or remove tasks, print
 * tasks both to the console and a file, or load a task list from a file.
 */
public class TaskOrganizer 
{
    private static GenLL<Task>[] organizedTasks;//all tasks
    private static final int PRIORITY_NUM = 5;//five priorities [0,4]
    private static Scanner keyboard = new Scanner(System.in);//user input

    /**
     * Main method that runs the logic for the whole program. Calls helper
     * functions to perform the user's desired actions.
     * @param args is not used
     */
    public static void main(String[] args)
    {
        organizedTasks = new GenLL[PRIORITY_NUM];
        for(int i = 0; i < organizedTasks.length; i++)
        {
            organizedTasks[i] = new GenLL<Task>();
        }//constructed all linked lists

        System.out.println("Welcome to the Task Organizer!");
        System.out.println("Please follow the instructions below.");

        boolean quit = false;//flag to terminate program
        while(!quit)
        {
            System.out.println("\nPress 1 to add a task.");
            System.out.println("Press 2 to remove a task.");
            System.out.println("Press 3 to print out all tasks to the console.");
            System.out.println("Press 4 to load a new task list from a file");
            System.out.println("Press 5 to write all tasks to a file.");
            System.out.println("Press 0 to quit");

            boolean validInput = false;//flag for either 1, 2, 3, 4, 5, or 0
            int input = 0;//default to 0
            while(!validInput)
            {
                try
                {
                    input = keyboard.nextInt();//only takes their int
                    keyboard.nextLine();//clears data stream

                    if(!(input >= 0 && input <= 5))//if not one of the options
                        throw new InputMismatchException();//go to catch
                    
                    validInput = true;//was one of the options
                }
                catch(InputMismatchException e)//if not one of the options
                {
                    System.out.println("Sorry, that was not a valid input, please try "
                        + " again.");
                    keyboard.nextLine();//clears data stream
                }
            }

            switch(input)//user's int
            {
                case 1:
                    add();
                    break;
                case 2:
                    remove();
                    break;
                case 3:
                    printTasks();
                    break;
                case 4:
                    readFile();
                    break;
                case 5:
                    printToFile();
                    break;
                case 0:
                    quit = true;//flags to terminate program
                    break;
            }
        }
    }

    /**
     * Asks the user what action and priority they would like for their
     * new task, constructs the task, and adds it to the linked list
     * corresponding with its priority.
     */
    public static void add()
    {
        System.out.println("\nPlease enter what you would like the "
            + "action for the task to be.");
        String action = keyboard.nextLine();
        boolean validInput = false;//flag for valid priority
        int priority = 4;//deafault value
        while(!validInput)//until valid priority
        {
            try
            {
                System.out.println("\nPlease enter what you would like the "
                    + "priority to be (value of 0-4)");
                priority = keyboard.nextInt();//only ints
                if(!(priority >= 0 && priority <= 4))//not 0, 1, 2, 3, or 4
                    throw new InputMismatchException();//handled in catch
                keyboard.nextLine();//clear data stream
                validInput = true;//valid priority given
            }
            catch(InputMismatchException e)//invalid priority given
            {
                System.out.println("Sorry, that wasn an invalid input. "
                    + "Please try again.");
                keyboard.nextLine();//clears data stream
            }
        }

        Task newTask = new Task(action, priority);//user's desired new task
        boolean duplicateTask = contains(newTask);//checks if already exits
        if(duplicateTask)
            System.out.println("\nThat task was already found in the task list, "
                + "so it was not added.");//doesn't add if already exists
        else
            organizedTasks[priority].add(newTask);//adds if unique task
    }

    /**
     * Asks the user what task they would like to seek out (asks for the action and
     * the priority), searches the list corresponding to its priority, and removes it
     * if it's there. No need to check whole list if one is found since no duplicates
     * are allowed in any of the task lists.
     */
    public static void remove()
    {
        System.out.println("Please enter the action of the task you would "
            + "like to remove.");
        String action = keyboard.nextLine();
        boolean validInput = false;//flag for valid priority
        int priority = 4;//default priority value
        while(!validInput)//until a valid priority is given
        {
            try
            {
                System.out.println("\nPlease enter what you would like the "
                    + "priority to be (value of 0-4)");
                priority = keyboard.nextInt();//only ints
                if(!(priority >= 0 && priority <= 4))//not 0, 1, 2, 3, or 4
                    throw new InputMismatchException();//handled in catch
                keyboard.nextLine();//clears data stream
                validInput = true;//valid priority given
            }
            catch(InputMismatchException e)//invalid priority given
            {
                System.out.println("Sorry, that wasn an invalid input. "
                    + "Please try again.");
                keyboard.nextLine();//clears data stream
            }
        }
        
        Task finderTask = new Task(action, priority);//task to remove
        if(contains(finderTask))//only if it is in the list
            organizedTasks[priority].removeCurrent();
            //contains stops current once it is found, so removeCurrent() works
    }

    /**
     * Prints all tasks to the user's console.
     */
    public static void printTasks()
    {
        for(int i = 0; i < organizedTasks.length; i++)//all five lists
        {
            organizedTasks[i].print();
        }
    }

    /**
     * Deletes any current tasks lists and loads up new ones from a file
     * that the user inputs.
     */
    public static void readFile()
    {
        for(int i = 0; i < organizedTasks.length; i++)//all five lists
        {
            organizedTasks[i].deleteList();
        }

        Scanner reader = null;//to read the file
        boolean fileFound = false;//flag for valid file given
        while(!fileFound)//until valid file given
        {
            try
            {
                System.out.println("\nPlease enter the name of the file "
                    + "you would like to load the data from.");
                String fileName = keyboard.nextLine();
                reader = new Scanner(new File(fileName));//tries to make it
                fileFound = true;//successful if file is found
            }
            catch(FileNotFoundException e)//if file can't be found
            {
                System.out.println("Sorry, that file could not be found. "
                    + "Please try again.");
            }
        }

        while(reader.hasNextLine())//for every line in the file
        {
            String line = reader.nextLine();//takes the whole line
            if(line.contains("\t"))//if formatted correctly
            {
                String[] info = line.split("\t");//split at tab
                //the action is second, the string with the priority is first
                Task newTask = new Task(info[1], Integer.parseInt(info[0]));
                organizedTasks[newTask.getPriority()].add(newTask);//adds task
            }
        }
        reader.close();//prevent resource leak
    }

    /**
     * Writes all the tasks to a file of the user's choice, allowing them to
     * append if an existing file.
     */
    public static void printToFile()
    {
        File resultsFile = null;//is the new file
        FileWriter writer = null;//to write to the new file
        boolean fileWorked = false;//flag for if FileWriter worked
        while(!fileWorked)//until the file works
        {
            try
            {
                System.out.println("\nEnter the name of the file you would like to "
                    + "print to. It can either be a new file or an existing file.");
                String fileName = keyboard.nextLine();
                resultsFile = new File(fileName);//makes the file object
                if(!resultsFile.createNewFile())//true if the file is made and doesn't exist yet
                {
                    boolean validInput = false;//only yes or no
                    while(!validInput)//until a yes or no
                    {
                        System.out.println("\nIt looks like you entered a file that already "
                            + "exists. Would you like to append to this file? Yes or No?");
                        String append = keyboard.nextLine();
                        if(append.equalsIgnoreCase("Yes"))
                        {
                            writer = new FileWriter(resultsFile, true);
                            validInput = true;//chose to append to existing file
                        }
                        else if(append.equalsIgnoreCase("No"))
                        {
                            writer = new FileWriter(resultsFile, false);
                            validInput = true;//chose to overwrite existing file
                        }
                        else//if not a yes or no
                            System.out.println("\nSorry, that was an invalid input."
                                + " Please try again.");
                    }
                }
                else//was a new file
                {
                    writer = new FileWriter(fileName);//no need to append
                }

                fileWorked = true;//successful FileWriter initialization
            }
            catch(IOException e)//if FileWriter object didn't work
            {
                System.out.println("\nSorry, that file you entered did not work. "
                    + "Please try entering a file again.\n");
            }
        }
        
        try
        {
            for(int i = 0; i < organizedTasks.length; i++)//all five lists
            {
                organizedTasks[i].reset();//back to start of list
                while(organizedTasks[i].getCurrent() != null)//until end of list
                {
                    writer.write(organizedTasks[i].getCurrent().toString() + "\n");//write to file
                    organizedTasks[i].goToNext();//progresses down the list
                }
            }
            writer.close();//save and close the file
        }
        catch(IOException e)//if FileWriter didn't work
        {
            System.out.println("Sorry, something went wrong while printing "
                + "to the file. System will terminate, please try again.");
            System.exit(0);
        }
    }

    /**
     * Checks to see if the given task is already in the task list.
     * @param aTask is the task to look for
     * @return boolean representing if the task is in the task list
     */
    public static boolean contains(Task aTask)
    {
        boolean hasTask = false;//initially false
        organizedTasks[aTask.getPriority()].reset();//start at head of list
        while(organizedTasks[aTask.getPriority()].getCurrent() != null 
            && hasTask != true)//until end of list or it is found
        {
            if(organizedTasks[aTask.getPriority()].getCurrent().equals(aTask))
                hasTask = true;//true when found and then exits loop without moving current
            else
                organizedTasks[aTask.getPriority()].goToNext();//only moves if not found
        }
        return hasTask;
    }
}
