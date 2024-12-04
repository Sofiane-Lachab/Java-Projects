/*
 * Written By: Sofiane Lachab
 */

/**
 * Helper class for the TaskOrganizer class. This class is used
 * to make Task objects.
 */
public class Task
{
    private String action;
    private int priority;

    /**
     * Default constructor (not used) that sets a Task's default
     * action to "none" and default priority to 4.
     */
    public Task()
    {
        action = "none";
        priority = 4;
    }
    /**
     * Parameterized constructor that sets the Task's instance data
     * to the inputted values.
     * @param aAction represents the Task's action
     * @param aPriority represents the Task's priority
     */
    public Task(String aAction, int aPriority)
    {
        action = aAction;
        priority = aPriority;
    }

    /**
     * Returns the Task's action.
     * @return the Task's action
     */
    public String getAction() 
    {
        return action;
    }
    /**
     * Sets the Task's action to the given value, or to the
     * default value if the given is null.
     * @param aAction is what to replace the Task's action with
     */
    public void setAction(String aAction) 
    {
        if(aAction != null)
            this.action = aAction;
        else
            this.action = "none";
    }
    /**
     * Returns the Task's priority.
     * @return the Task's priority
     */
    public int getPriority() 
    {
        return priority;
    }
    /**
     * Sets the Task's priority to the given value, or to the
     * default value if the given is null.
     * @param aPriority is what to replace the Task's priority with
     */
    public void setPriority(int aPriority) 
    {
        if(aPriority >= 0 && aPriority <= 4)//0, 1, 2, 3, or 4
            this.priority = aPriority;
        else
            this.priority = 4;
    }
    /**
     * Compares the Task object to the given Task object,
     * identifying them as equal if their priority and action
     * are the same.
     * @param aTask is the Task to compare to
     * @return boolean representing if they are identical
     */
    public boolean equals(Task aTask)
    {
        if(this.action.equals(aTask.getAction())
            && this.priority == aTask.getPriority())
            return true;
        else
            return false;
    }
    /**
     * Modified toString of the Task that shows its priority and action.
     */
    public String toString()
    {
        return "Priority: " + priority + "\t" + "Action " + action;
    }
}