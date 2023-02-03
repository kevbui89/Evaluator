package ev2.test;

import ev2.utilities.DynamicArray;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the test class for the custom array
 * @author Kevin
 * @version 1.0
 */
public class DynamicArrayTest {
    
    // Logger
    private final Logger LOG = LoggerFactory.getLogger(getClass().getName());
    private DynamicArray<String> array = new DynamicArray<>();
    
    /**
     * Setup to be run before each test.
     */
    @Before
    public void setUp() {
        LOG.info("Inside CustomArrayTest setUp()");
        array.add("Element 1");
        array.add("Element 2");
    }
    
    /**
     * This will test if the custom array returns the right size.
     */
    @Test
    public void testCustomArraySize() {
        int size = array.size();
        
        assertEquals("The size of this custom array is 2", 2, size);
    }
    
    /**
     * This will test a no parameter add element which should be added to the end.
     */
    @Test
    public void testAddElement() {
        array.add("Element 3");
        int size = array.size();
        String lastElement = array.get(2);
        
        assertEquals("The size of this custom array is 3", 3, size);
        assertEquals("The last element is Element 3", "Element 3", lastElement);
    }
    
    /**
     * This will test the add element with an index.
     * The element should be added at the requested position.
     */
    @Test
    public void testAddElementWithIndex() {
        array.add(0, "New First Element");
        int size = array.size();
        String firstElement = array.get(0);
        
        assertEquals("The size of this custom array is 3", 3, size);
        assertEquals("The last element is Element 3", "New First Element", firstElement);
    }
    
    /**
     * This will test the get method.
     * It should return the element at the requested position.
     */
    @Test
    public void testGet() {
        String ele2 = array.get(1);
        
        assertEquals("The element at position 1 is Element 2", "Element 2", ele2);
    }
    
    @Test
    public void testSet() {
        array.set(1, "New Second Element");
        String ele2 = array.get(1);
        
        assertEquals("The new second element is New Second Element", "New Second Element", ele2);
    }
    
    /**
     * This will test the no parameter remove method.
     * It should remove the last element added to the array.
     */
    @Test
    public void testRemove() {
        String removedElement = array.remove();
        int size = array.size();
        
        assertEquals("The array size after removal should is 1", 1, size);
        assertEquals("The removed element is Element 2", "Element 2", removedElement);
    }
    
    /**
     * This will test the remove method with an position parameter.
     * It should remove the element at the requested parameter and push the all
     * other elements to the left.
     */
    @Test
    public void testRemoveWithIndex() {
        String removedElement = array.remove(0);
        int size = array.size();
        String firstElement = array.get(0);
        
        assertEquals("The array size after removal should is 1", 1, size);
        assertEquals("The removed element is Element 1", "Element 1", removedElement);
        assertEquals("The new element at position 0 is Element 2", "Element 2", firstElement);
    }
    
    /**
     * This will test the grow array. 
     * If the element added exceeds the capacity, the array will grow to 
     * accomodate.
     */
    @Test
    public void testGrowArray() {
        array.add("Element 3");
        array.add("Element 4");
        array.add("Element 5");
        array.add("Element 6");
        array.add("Element 7");
        array.add("Element 8");
        array.add("Element 9");
        array.add("Element 10");
        array.add("Element 11");
        
        int size = array.size();
        int capacity = array.capacity();
        
        assertEquals("The array size should be 11", 11, size);
        assertEquals("The initial capacity was 10, "
                + "but when adding an 11th element, "
                + "the capacity should now be 20", 20, capacity);
    }
    
    /**
     * This will test the indexOf method.
     * It will return the element at the requested index.
     */
    @Test
    public void testIndexOf() {
        int index = array.indexOf("Element 2");
        
        assertEquals("The index should be 1", 1, index);
    }
    
    /**
     * This will test the clear method.  The array should have a size of 0 after
     * the clear() method has run.
     */
    @Test
    public void testClear() {
        array.clear();        
        int size = array.size();
        
        assertEquals("The array size should be 0", 0, size);
    }
    
    /**
     * This will test the clear method.  If the user tries to get an element
     * from the array after the clear method has run, it should throw an
     * ArrayIndexOutOfBoundsException.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testClearException() {
        array.clear();        
        String firstElement = array.get(0);
        
        fail("The array is clear, you should not be able to get anything with any index, "
                + "it should throw an ArrayIndexOutOfBoundsException");
    }
    
    /**
     * This will test the validate index.  The index the user provides to fetch
     * data from the array must be positive and within the array size.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testValidateIndex() {
        String validation = array.get(-1);
        
        fail("The array size is 2, it should not allow any negative index, "
                + "it should throw an ArrayIndexOutOfBoundsException");
    }
    
    /**
     * This will test the toString method.
     */
    @Test
    public void testToString() {
        String array = this.array.toString();
        
        assertEquals("The strings should be equal", "CustomArray{ Element 1, Element 2 }", array);
    }
}

