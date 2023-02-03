package ev2.business;

/**
 * The interface for the Queue.
 * First In First Out.
 * @author Kevin
 * @param <T>   The type of value stored in this stack
 */
public interface IQueue<T> {
    T push(T element);
    T pop();
    boolean isEmpty();
    int size();
    T peek();   
}
