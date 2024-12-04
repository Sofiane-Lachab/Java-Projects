/*
 * Written By: Sofiane Lachab
 */
/**
 * Generic implementation of a minimum heap data structure. Can be
 * applied to any comparable type. 
 */
public class MinHeap <T extends Comparable<T>>
{
    private T[] heap;//array for the heap
    private int lastIndex;//at first null element
    public static final int DEF_SIZE = 128;//default for the heap

    /**
     * Default constructor for the minimum heap, setting the maximum
     * number of elements to the default size.
     */
    public MinHeap()
    {
        init(DEF_SIZE);
    }
    /**
     * Parametrized constructor that takes in a size for the heap, utilizing
     * that for the maximum number of elements.
     * @param aSize is the size to set the heap to
     */
    public MinHeap(int aSize)
    {
        init(aSize);
    }
    /**
     * Initializer for the heap that checks for a valid size (positive)
     * setting it to the default value if not valid.
     * @param aSize is the size to check to set the heap to
     */
    @SuppressWarnings("unchecked")
    private void init(int aSize)
    {
        if(aSize <= 0)//non-positive
        {
            init(DEF_SIZE);//default size 
            return;
        }
        heap = (T[])(new Comparable[aSize]);//instantiate
        lastIndex = 0;//marker for first null
    }

    /**
     * Adds the given data to the heap at the end, adjusting if necessary
     * to maintain the min heap property.
     * @param aData is the data to add to the heap
     */
    public void add(T aData)
    {
        if(lastIndex >= heap.length)//full heap
            return;
        heap[lastIndex] = aData;//at the end
        bubbleUp();//adjust
        lastIndex++;//move marker
    }
    /**
     * Moves up the newly added element in the heap if it is less
     * than its parents, adjusting until the min heap is correct.
     */
    private void bubbleUp()
    {
        int index = lastIndex;
        //while not the root and child is less than parent
        while(index > 0 && heap[(index-1)/2].compareTo(heap[index]) > 0)
        {
            //swap
            T temp = heap[(index-1)/2];
            heap[(index-1)/2] = heap[index];
            heap[index] = temp;
            index = (index-1)/2;//check new location
        }
    }
    /**
     * Removes the root from the heap and returns its data, then moving up
     * the last element to the root and adjusting the heap as necessary.
     * @return the data that was removed
     */
    public T remove()
    {
        if(lastIndex == 0)//empty heap
            return null;
        //save data and swap
        T ret = heap[0];
        heap[0] = heap[lastIndex-1];
        heap[lastIndex-1] = null;
        lastIndex--;//decrement marker
        bubbleDown();//adjust
        return ret;
    }
    /**
     * Moves down the newly moved element if it is greater than its
     * children, adjusting until the min heap is correct.
     */
    private void bubbleDown()
    {
        int index = 0;
        while(index*2+1 < lastIndex)//not the bottom of heap
        {
            int smallIndex = index*2+1;//left child
            //if right child isn't null and left child is greater than right child
            if(index*2+2 < lastIndex && heap[index*2+1].compareTo(heap[index*2+2]) > 0)
                smallIndex = index*2+2;//right child is smaller
            //if current is greater than the lesser child
            if(heap[index].compareTo(heap[smallIndex]) > 0)
            {
                //swap
                T temp = heap[index];
                heap[index] = heap[smallIndex];
                heap[smallIndex] = temp;
            }
            else
                break;//heap is correct
            index = smallIndex;//check new location
        }
    }
    /**
     * Shows first element in the heap (next to be removed).
     * @return the first element in the heap
     */
    public T peek()
    {
        return heap[0];
    }
    /**
     * Iterates through the heap, printing each element.
     */
    public void print()
    {
        for(T h : heap)
        {
            if(h == null)
                break;
            System.out.println(h);
        }
    }
    /**
     * Creates a new min heap with the data from the given array, then
     * reinserts into the array by removing from the heap, therefore
     * sorting the data.
     * @param array is the data to sort
     */
    public void heapSort(T[] array)
    {
        if(array == null)//empty array
            return;
        MinHeap<T> mHeap = new MinHeap<T>(array.length);//make a heap
        for(int i = 0; i< array.length; i++)
            mHeap.add(array[i]);//add to heap
        for(int i = 0; i < array.length; i++)
            array[i] = mHeap.remove();//removes in order
    }
}
