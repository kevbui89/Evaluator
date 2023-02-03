package ev2.business;

/**
 * The interface for the Stack.
 * Last In First Out.
 * @author Kevin
 * @param <T>   The type of value stored in this stack
 */
public interface IStack<T> {
    T push(T element);
    T pop();
    boolean contains(T element);
    T peek();
    int size();
    void clear();
    boolean isEmpty();
}
