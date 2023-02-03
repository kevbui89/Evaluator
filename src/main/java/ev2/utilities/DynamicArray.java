package ev2.utilities;

/**
 * This is the customized array with generics.
 * It has few functionalities of similar to the one of the java.util ArrayList
 * Collection.
 * 
 * I didn't implement IQueue and IStack here because both the stack and queue have
 * the same naming for their methods and it was too confusing to keep track of each.
 * 
 * I decided to implement the IQueue in a custom Queue class and the IStack in a
 * custom Stack class and use them for the evaluator.
 * @author Kevin
 * @param <T> The type of value stored in this array
 */
public class DynamicArray<T> {

    private int size;
    private int capacity;
    private int growthFactor;
    private T[] arrayList;

    /**
     * Default constructor
     */
    public DynamicArray() {
        this(10, 2);
    }

    /**
     * One parameter constructor
     *
     * @param capacity      The initial capacity of the array
     * @param growthFactor  The growth factor of the array
     */
    public DynamicArray(int capacity, int growthFactor) {
        super();
        if (capacity < 0 || growthFactor < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        } else if (capacity == 0) {
            this.capacity = 0;
            this.growthFactor = growthFactor;
            this.arrayList = (T[]) new Object[capacity];
        } else {
            this.capacity = capacity;
            this.growthFactor = growthFactor;
            this.arrayList = (T[]) new Object[capacity];
        }
    }

    /**
     * Adds an element to the dynamic array
     * @param element   The element to be added
     */
    public void add(T element) {
        ensureCapacity(size + 1);
        arrayList[size++] = element;
    } 

    /**
     * Adds an element to the dynamic array at the index requested
     * @param index     The index position where to add the element
     * @param element   The element to be added
     */
    public void add(int index, T element) {
        validateIndex(index);
        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            arrayList[i] = arrayList[i - 1];
        }

        arrayList[index] = element;
        size++;
    } 

    /**
     * Returns the element of the array at the requested position
     * @param index     The index position of the requested element
     * @return          The element at the requested position
     */
    public T get(int index) {
        validateIndex(index);
        return (T) arrayList[index];
    } 

    /**
     * Sets the element at the position with the new value
     * @param index     The position of the element to set
     * @param element   The new value of the element to set
     * @return          The old value before the element was changed
     */
    public T set(int index, T element) {
        validateIndex(index);
        T oldValue = (T) arrayList[index];
        arrayList[index] = element;
        return oldValue;
    } 

    /**
     * Removes the last element set in the stack
     * @return          The old value before the element was removed
     */
    public T remove() {
        T oldValue = (T) arrayList[size - 1];
        arrayList[--size] = null;
        return oldValue;
    } 

    /**
     * Removes the element at the requested position
     * @param index     The position of the element to be removed
     * @return          The old value before the element was removed
     */
    public T remove(int index) {
        validateIndex(index);
        T oldValue = (T) arrayList[index];
        int positionMoved = size - index - 1;
        if (positionMoved > 0) {
            System.arraycopy(arrayList, index + 1, arrayList, index, positionMoved);
        }
        arrayList[--size] = null;

        return oldValue;
    } 

    /**
     * Returns the first value of the index of the requested element in the array
     * @param element   The element to be searched
     * @return          The index position of the element
     */
    public int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (arrayList[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(arrayList[i])) {
                    return i;
                }
            }
        }
        return -1;
    } 
    
    /**
     * Clears the array
     */
    public void clear() {
        size = 0;
    } 

    /**
     * Returns the size of the array
     * @return      The size of the array
     */
    public int size() {
        return size;
    } 
    
    /**
     * Returns the capacity of the array
     * @return      The capacity of the array
     */
    public int capacity() {
        return capacity;
    } 
    
    /**
     * Returns the growth factor of the array
     * @return      The growth factor of the array
     */
    public int growthFactor() {
        return growthFactor;
    } 
    
    /**
     * Sets the growth factor.
     * Only use if you know before hand what the size of your array will be.
     * @param growth The new value of the growth factor to be set
     */
    public void setGrowthFactor(int growth) {
        this.growthFactor = growth;
    } 

    /**
     * Returns a string representation of the array
     * @return      The string representation of the array
     */
    @Override
    public String toString() {
        String result = "CustomArray{ ";
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                result += arrayList[i] + " ";
            } else {
                result += arrayList[i] + ", ";
            }
        }
        result += "}";
        return result;
    } 

    /**
     * Double the capacity to make sure you can add elements to the array
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity - arrayList.length > 0) {
            growArray();
        }
    } 
    
    /**
     * Grows the array by doubling the capacity (using the growth factor)
     */
    private void growArray() {
        capacity = capacity * growthFactor;
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; ++i) {
                newArray[i] = arrayList[i];
            }
        arrayList = newArray;
    } // End growArray()

    /**
     * Validates to see if the index input is valid and can be used by the requesting method
     * @param index     The index to be validated
     */
    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    } 
}
