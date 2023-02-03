package ev2.business;

import ev2.utilities.DynamicArray;

/**
 * This class defines a Queue data structure and provides all the functionality
 * expected from a queue.
 * 
 * @author Kevin
 * @version 1.0
 * @param <T>       The type of value stored in this stack
 */
public class Queue<T> implements IQueue<T> {
    
    private DynamicArray<T> queue;
    private int first;
    private int last;
    
    /**
     * Two parameter constructor
     * @param capacity      The capacity of the queue
     * @param growthFactor  The growth factor of the queue
     */
    public Queue(int capacity, int growthFactor) {
        this.queue = new DynamicArray<>(capacity, growthFactor);
        this.first = 0;
        this.last = 0;
    }

    /**
     * Adds an element to the front of the queue.
     * @param element   The element to add to the queue
     * @return          The element pushed
     */
    @Override
    public T push(T element) {
        queue.add(element);
        last++;
        return element;
    }

    /**
     * Removes an element to the front of the queue
     * @return      The element removed from the queue
     */
    @Override
    public T pop() {
        if (!isEmpty()) {
            T element = queue.get(first);
            first++;
            return element;
        } else {
            throw new IndexOutOfBoundsException("The array is empty.");
        }
    }

    /**
     * Checks if the stack is empty or not.
     * @return      A boolean representation if the stack is empty or not
     */
    @Override
    public boolean isEmpty() {
        return first == last;
    } 

    /**
     * Returns the size of the queue.
     * @return      The size of the queue
     */
    @Override
    public int size() {
        return queue.size();
    } 

    /**
     * Peeks at the front element.
     * @return      The front element
     */
    @Override
    public T peek() {
        T element = queue.get(first);
        return element;
    } 
    
    /**
     * Returns a string representation of the queue.
     * @return  A string representation of the queue.
     */
    @Override
    public String toString() {
        String result = "Queue{ ";
        for (int i = 0; i < queue.size(); i++) {
            if (i == queue.size() - 1) {
                result += queue.get(i) + " ";
            } else {
                result += queue.get(i) + ", ";
            }
        }
        result += "}";
        return result;
    } 
}
