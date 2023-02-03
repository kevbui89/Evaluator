package ev2.test;

import ev2.business.IStack;
import ev2.business.Stack;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class test the stack functionality.
 * @author Kevin
 */
public class StackTest {
    
     // Logger
    private final Logger LOG = LoggerFactory.getLogger(getClass().getName());
    private IStack<String> stack = new Stack<>(10, 2);
    
    /**
     * Setup to be run before each test.
     */
    @Before
    public void setUp() {
        LOG.info("Inside StackTest setUp()");
        stack.push("Element 1");
        stack.push("Element 2");
    }
    
    /**
     * This test the add method of the stack functionality.
     */
    @Test
    public void testAddToStack() {
        String element = stack.push("Element 3");
        
        assertEquals("The last element should be Element 3", "Element 3", element);
    }
    
    /**
     * This will test the pop method of the stack functionality.
     */
    @Test
    public void testPop() {
        String poppedElement = stack.pop();
        
        assertEquals("The element popped should be Element 2", "Element 2", poppedElement);
    }
    
    /**
     * This will test the contains method of the stack functionality.
     */
    @Test
    public void testContainsTrue() {
        boolean containsTrue = stack.contains("Element 1");
        
        assertEquals("The stack contains an Element 1", true, containsTrue);
    }
    
    /**
     * This will test the contains method of the stack functionality.
     */
    @Test
    public void testContainsFalse() {
        boolean containsFalse = stack.contains("Element 3");
        
        assertEquals("The stack does not contain an Element 3", false, containsFalse);
    }
    
    /**
     * This will test and check the size of the stack.
     */
    @Test
    public void testSize() {
        int size = stack.size();
        
        assertEquals("The size should be 2", 2, size);
    }
    
    /**
     * This will test and check the size of the stack after it has been cleared.
     */
    @Test
    public void testClear() {
        stack.clear();
        int size = stack.size();
        
        assertEquals("The size should be 0", 0, size);
    }
    
    /**
     * This will test the isEmpty() of the stack functionality.
     */
    @Test
    public void testIsEmpty() {
        stack.pop();
        stack.pop();
        boolean empty = stack.isEmpty();
        
        assertEquals("The stack should be empty", true, empty);
    }
    
    /**
     * This will test the toString method of the stack.
     */
    @Test
    public void testToString() {
        String stackStr = stack.toString();
        
        assertEquals("The strings should be equal", "Stack{ Element 1, Element 2 }", stackStr);
    }
    
}
