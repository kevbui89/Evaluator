package ev2.test;

import ev2.business.IQueue;
import ev2.business.Queue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class test the queue class functionality.
 * @author Kevin
 * @version 1.0
 */
public class QueueTest {
    
    // Logger
    private final Logger LOG = LoggerFactory.getLogger(getClass().getName());
    private IQueue<String> queue = new Queue<>(10, 2);
    
    /**
     * Setup to be run before each test.
     */
    @Before
    public void setUp() {
        LOG.info("Inside QueueTest setUp()");
        queue.push("Element 1");
        queue.push("Element 2");
    }
    
    /**
     * This test the add method of the queue functionality.
     */
    @Test
    public void testAddToQueue() {
        String element = queue.push("Element 3");
        
        assertEquals("The last element should be Element 3", "Element 3", element);
    }
    
    /**
     * This will test the pop method of the queue functionality.
     */
    @Test
    public void testPop() {
        String poppedElement = queue.pop();
        
        assertEquals("The element popped should be Element 1", "Element 1", poppedElement);
    }
    
    /**
     * This will test the isEmpty() of the queue functionality.
     */
    @Test
    public void testIsEmpty() {
        queue.pop();
        queue.pop();
        boolean empty = queue.isEmpty();
        
        assertEquals("The queue should be empty", true, empty);
    }
    
    /**
     * This will test and check the size of the queue.
     */
    @Test
    public void testSize() {
        int size = queue.size();
        
        assertEquals("The size should be 2", 2, size);
    }
    
    /**
     * This will test the peek method of the queue functionality.
     */
    @Test
    public void testPeek() {
        String peek = queue.peek();
        
        assertEquals("The first element should be Element 1", "Element 1", peek);
    }
    
}
