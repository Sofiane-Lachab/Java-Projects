/*
 * Written by: Sofiane Lachab
 */

 /**
  * A generic class utilized to implement linked lists data structures.
  * This class uses a standard, singly linked list structure, adaptable
  * to any object type.
  */
 public class GenLL <T>
 {
    /**
     * Internal class utilized to make the nodes for the linked list. Each
     * node stores the data (of whatever object type the list is for) and
     * a link, which is a reference pointing to the next node.
     */
    private class ListNode
    {
        T data;
        ListNode link;
        public ListNode(T aData, ListNode aLink)
        {
            data = aData;
            link = aLink;
        }
    }
    private ListNode head;//reference to the first element
    private ListNode current;//reference to current element
    private ListNode previous;//reference to element before current
    /**
     * Default constructor (no paramterized constructor) that sets
     * all references to null, making an empty list.
     */
    public GenLL()
    {
        head = current = previous = null;
    }
    /**
     * Creates and adds a node to the end of list, storing the givent data.
     * @param aData
     */
    public void add(T aData)
    {
        ListNode newNode= new ListNode(aData, null);//link==null because end
        if(head == null)//empty list
        {
            head = newNode;
            return;
        }
        //Not an empty list
        ListNode temp = head;
        while(temp.link != null)
            temp = temp.link;
        temp.link= newNode;//Add to end of the list
    }
    /**
     * Iterates through the list and prints out the data in every node.
     */
    public void print()
    {
        ListNode temp = head;//start at beginning
        while(temp != null)//through every node
        {
            System.out.println(temp.data);
            temp = temp.link;//next node
        }
    }
    /**
     * Moves the current reference to the next node in the list,
     * unless the current reference is null.
     */
    public void goToNext()
    {
        if(current != null)
        {
            previous = current;
            current = current.link;
        }
    }
    /**
     * Moves the current reference back to the beginning of the
     * list, as well as setting previous to null.
     */
    public void reset()
    {
        current = head;
        previous = null;
    }
    /**
     * Getter for the data stored in the current node, unless
     * current is null.
     * @return the data in the current node
     */
    public T getCurrent()
    {
        if(current == null)
            return null;
        else
            return current.data;
    }
    /**
     * Sets the data in the current node to the given data, unless
     * the current node is null.
     * @param aData is the data to set the current node to store
     */
    public void setCurrent(T aData)
    {
        if(current == null)
            return;
        current.data = aData;
    }
    /**
     * Removes the current node in the list, adjusting the surrounding
     * nodes accordingly. Does nothing if the current node is null.
     */
    public void removeCurrent()
    {
        if(current != null && previous == null)//Current == head
        {
            head = head.link;
            current = head;
        }
        else if(current != null && previous != null)//not beginning of list
        {
            previous.link = current.link;
            current = current.link;
        }
    }
    /**
     * Creates and adds a new node, storing the given data, to the list,
     * in place after the current node. Does nothing if the current node
     * is null.
     * @param aData is the data to store in the new node
     */
    public void addAfterCurrent(T aData)
    {
        if(current == null)
            return;
        ListNode newNode = new ListNode(aData, current.link);
        current.link = newNode;
    }
    /**
     * Deletes the entire list by resetting the head, current, and
     * previous references to null (all nodes have no pointers).
     */
    public void deleteList()
    {
        head = current = previous = null;
    }
}