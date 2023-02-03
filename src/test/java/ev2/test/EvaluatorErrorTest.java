package ev2.test;

import ev2.business.Queue;
import ev2.calculator.Evaluator;
import ev2.exception.InvalidCharacter;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class will test invalid expressions
 * @author Kevin
 * @version 1.0
 */
public class EvaluatorErrorTest {
    
    // Logger
    private final Logger LOG = LoggerFactory.getLogger(getClass().getName());
    private Evaluator evaluator;
    private Queue<String> queue;
    
    /**
     * Default constructor
     * Builds the default   Evaluator
     */
    public EvaluatorErrorTest() {
        LOG.info("Inside EvaluatorErrorTest constructor");
        this.evaluator = new Evaluator();
    }
    
    /**
     * Setup to be run before each test.
     */
    @Before
    public void setUp() {
        LOG.info("Inside EvaluatorErrorTest setUp()");
        queue = new Queue<>(10, 2);
    } 
    
    /**
     * This will test an expression divided by 0.
     */
    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() throws InvalidCharacter {
        queue.push("6");
        queue.push("/");
        queue.push("0");
        
        evaluator.calculate(evaluator.getPostfix(queue));
        fail("This calculation is impossible, it should throw an"
                + "ArithmeticException.");
    } 
    
    /**
     * This will test an expression with a number following a closing bracket.
     */
    @Test(expected = InvalidCharacter.class)
    public void testNumberBesideBracket() throws InvalidCharacter {
        queue.push("(");
        queue.push("8");
        queue.push("+");
        queue.push("9");
        queue.push(")");
        queue.push("6");
        
        evaluator.calculate(evaluator.getPostfix(queue));
        fail("This expression is not valid, a number cannot follow a closing bracket."
                + "This should throw an InvalidCharacter.");
    } 
    
    /**
     * This will test an expression with two operators stuck together.
     */
    @Test(expected = InvalidCharacter.class)
    public void testTwoOperatorsTogether() throws InvalidCharacter {
        queue.push("8");
        queue.push("+");
        queue.push("*");
        queue.push("6");
        
        evaluator.calculate(evaluator.getPostfix(queue));
        fail("This expression is not valid, you cannot have two operators together."
                + "This should throw an InvalidCharacter.");
    } 
    
    /**
     * This will test an expression with two numbers stuck together
     */
    @Test(expected = InvalidCharacter.class)
    public void testTwoNumbersTogether() throws InvalidCharacter {
        queue.push("8");
        queue.push("8");
        queue.push("*");
        queue.push("6");
        
        evaluator.calculate(evaluator.getPostfix(queue));
        fail("This expression is not valid, you cannot have two numbers together."
                + "This should throw an InvalidCharacter.");
    } 
    
    /**
     * This will test an expression with an opening and closing bracket stuck 
     * together
     */
    @Test(expected = InvalidCharacter.class)
    public void testTwoBracketsTogether() throws InvalidCharacter {
        queue.push("(");
        queue.push("8");
        queue.push("*");
        queue.push("6");
        queue.push(")");
        queue.push("(");
        queue.push("6");
        queue.push("*");
        queue.push("6");
        queue.push(")");
        
        evaluator.calculate(evaluator.getPostfix(queue));
        fail("This expression is not valid, you cannot have an opening and closing bracket together."
                + "This should throw an InvalidCharacter.");
    } 
    
    /**
     * This will test an expression with an odd number of brackets
     */
    @Test(expected = InvalidCharacter.class)
    public void testOddNumberOfBrackets() throws InvalidCharacter {
        queue.push("(");
        queue.push("(");
        queue.push("8");
        queue.push("*");
        queue.push("6");
        queue.push(")");
        
        evaluator.calculate(evaluator.getPostfix(queue));
        fail("This expression is not valid, there is an odd number of brackets."
                + "This should throw an InvalidCharacter.");
    } 
} // End EvaluatorErrorTest calss
