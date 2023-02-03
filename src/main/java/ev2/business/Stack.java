package ev2.business;

import ev2.utilities.DynamicArray;

/**
 * This class defines a stack data structure and provides all the functionality
 * expected from a stack.
 * @author Kevin
 * @param <T>   The type of value stored in this stack
 */
public class Stack<T> implements IStack<T> {
    
    private DynamicArray<T> stack;
    private int pointer;
    
    /**
     * One parameter constructor
     * @param size          The wanted size of the stack
     * @param growthFactor  The growth factor of the stack
     */
    public Stack(int size, int growthFactor) {
        this.stack = new DynamicArray<>(size, growthFactor);
        this.pointer = -1;
    }

    /**
     * Adds an element to the top of the stack.
     * @param element   The element to be added       
     * @return          The element pushed   
     */
    @Override
    public T push(T element) {
        stack.add(element);
        pointer++;
        return element;
    }

    /**
     * Removes an element from the top of the stack.
     * @return element     The element removed
     */
    @Override
    public T pop() {
        if (!isEmpty()) {
            T element = stack.remove(pointer);
            pointer--;
            return element;
        } else {
            throw new IndexOutOfBoundsException("The array is empty.");
        }
    }
    
    /**
     * Checks if the stack contains the element requested.
     * @param element   The element to search
     * @return found    A boolean representation if the element was found or not.
     */
    @Override
    public boolean contains(T element) {
        boolean found = false;
        
        for (int i = 0; i < stack.size(); i++) {
            T newElement = stack.get(i);
            if (newElement.equals(element)) {
                return true;
            }
        }
        return found;
    }

    /**
     * Returns the size of the stack.
     * @return size     The size of the stack.
     */
    @Override
    public int size() {
        return stack.size();
    }

    /**
     * Clears the stack.
     */
    @Override
    public void clear() {
        stack.clear();
    }

    /**
     * Checks if the stack is empty.
     * @return boolean      A boolean representation if the stack is empty or not.
     */
    @Override
    public boolean isEmpty() {
        return stack.size() == 0;
    }
    
    /**
     * Checks the element at the top of the stack.
     * @return      The element at the top of the stack
     */
    @Override
    public T peek() {
        if (!isEmpty()) {
            return stack.get(pointer);
        } else {
            throw new IndexOutOfBoundsException("The stack is empty");
        }
    }

    /**
     * Returns a string representation of the stack.
     * @return  A string representation of the stack.
     */
    @Override
    public String toString() {
        String result = "Stack{ ";
        for (int i = 0; i < stack.size(); i++) {
            if (i == stack.size() - 1) {
                result += stack.get(i) + " ";
            } else {
                result += stack.get(i) + ", ";
            }
        }
        result += "}";
        return result;
    }
}
