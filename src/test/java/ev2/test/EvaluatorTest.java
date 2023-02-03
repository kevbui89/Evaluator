package ev2.test;

import ev2.business.Queue;
import ev2.calculator.Evaluator;
import ev2.exception.InvalidCharacter;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class test the evaluator class
 * @author Kevin
 * @version 1.0
 */
@RunWith(Parameterized.class)
public class EvaluatorTest {
    
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        // First equation
        Queue<String> equation = new Queue<>(10, 2);
        equation.push("(");
        equation.push("4");
        equation.push("+");
        equation.push("3");
        equation.push(")");
        equation.push("*");
        equation.push("(");
        equation.push("3");
        equation.push("+");
        equation.push("4");
        equation.push(")");
        
        double exp = 49;
        
        // Second equation
        Queue<String> equation1 = new Queue<>(10, 2);
        equation1.push("5");
        equation1.push("+");
        equation1.push("8");
        equation1.push("*");
        equation1.push("6");
        
        double exp1 = 53;
        
        // Third equation
        Queue<String> equation2 = new Queue<>(10, 2);
        equation2.push("120");
        equation2.push("/");
        equation2.push("2");
        equation2.push("/");
        equation2.push("2");
        equation2.push("+");
        equation2.push("30");
        
        double exp2 = 60;
        
        // Fourth equation
        Queue<String> equation3 = new Queue<>(10, 2);
        equation3.push("(");
        equation3.push("(");
        equation3.push("10");
        equation3.push("+");
        equation3.push("10");
        equation3.push(")");
        equation3.push("*");
        equation3.push("2");
        equation3.push(")");
        equation3.push("/");
        equation3.push("2");
        
        double exp3 = 20;
        
        // Fifth equation
        Queue<String> equation4 = new Queue<>(10, 2);
        equation4.push("4");
        equation4.push("+");
        equation4.push("3");
        equation4.push("/");
        equation4.push("2");
        equation4.push("+");
        equation4.push("3");
        equation4.push("*");
        equation4.push("2");
        
        double exp4 = 11.5;
        
        // Sixth equation
        Queue<String> equation5 = new Queue<>(10, 2);
        equation5.push("200");
        equation5.push("+");
        equation5.push("50");
        equation5.push("*");
        equation5.push("2");
        equation5.push("/");
        equation5.push("5");
        equation5.push("+");
        equation5.push("1337");
        
        double exp5 = 1557;
        
        // Seventh equation
        Queue<String> equation6 = new Queue<>(10, 2);
        equation6.push("10.5");
        equation6.push("+");
        equation6.push("10.5");
        equation6.push("+");
        equation6.push("3.33");
        
        double exp6 = 24.33;
        
        // Eighth equation
        Queue<String> equation7 = new Queue<>(10, 2);
        equation7.push("5.55");
        equation7.push("/");
        equation7.push("5");
        equation7.push("+");
        equation7.push("1");
        
        double exp7 = 2.11;
        
        // Ninth equation
        Queue<String> equation8 = new Queue<>(10, 2);
        equation8.push("(");
        equation8.push("(");
        equation8.push("(");
        equation8.push("1");
        equation8.push("*");
        equation8.push("8");
        equation8.push(")");
        equation8.push("+");
        equation8.push("2");
        equation8.push(")");
        equation8.push("*");
        equation8.push("3");
        equation8.push(")");
        equation8.push("+");
        equation8.push("100");
        
        double exp8 = (((1 * 8) + 2) * 3) + 100;
        
        // Tenth equation
        Queue<String> equation9 = new Queue<>(10, 2);
        equation9.push("(");
        equation9.push("(");
        equation9.push("(");
        equation9.push("1");
        equation9.push("*");
        equation9.push("8");
        equation9.push(")");
        equation9.push("*");
        equation9.push("2");
        equation9.push(")");
        equation9.push("*");
        equation9.push("3");
        equation9.push(")");
        equation9.push("*");
        equation9.push("100");
        
        double exp9 = 4800;
        
        // Eleventh equation
        Queue<String> equation10 = new Queue<>(10, 2);
        equation10.push("8");
        equation10.push("*");
        equation10.push("8");
        equation10.push("*");
        equation10.push("8");
        equation10.push("*");
        equation10.push("8");

        double exp10 = 4096;
        
        // Twelfth equation
        Queue<String> equation11 = new Queue<>(10, 2);        
        equation11.push("8");
        equation11.push("/");
        equation11.push("8");
        equation11.push("/");
        equation11.push("8");
        equation11.push("/");
        equation11.push("8");

        double exp11 = 0.015625;
        
        // Thirtheenth equation
        Queue<String> equation12 = new Queue<>(10, 2);        
        equation12.push("(");
        equation12.push("8");
        equation12.push("+");
        equation12.push("12");
        equation12.push(")");
        equation12.push("+");
        equation12.push("8");
        equation12.push("-");
        equation12.push("1200");
        
        double exp12 = -1172;
        
        // Fourteenth equation
        Queue<String> equation13 = new Queue<>(10, 2);        
        equation13.push("(");
        equation13.push("123");
        equation13.push("-");
        equation13.push("321");
        equation13.push(")");
        equation13.push("-");
        equation13.push("10");
        equation13.push("*");
        equation13.push("1337");
        
        double exp13 = -13568;
        
        // Fifteenth equation
        Queue<String> equation14 = new Queue<>(10, 2);        
        equation14.push("(");
        equation14.push("(");
        equation14.push("8");
        equation14.push("+");
        equation14.push("8");
        equation14.push(")");
        equation14.push(")");
        
        double exp14 = 16;
        
        // Sisteenth equation
        Queue<String> equation15 = new Queue<>(10, 2);        
        equation15.push("(");
        equation15.push("(");
        equation15.push("8");
        equation15.push("*");
        equation15.push("8");
        equation15.push(")");
        equation15.push("*");
        equation15.push("2");
        equation15.push(")");
        
        double exp15 = 128;
        
        // Seventeenth equation
        Queue<String> equation16 = new Queue<>(10, 2);        
        equation16.push("(");
        equation16.push("9");
        equation16.push("+");
        equation16.push("8");
        equation16.push("*");
        equation16.push("2");
        equation16.push("*");
        equation16.push("3");
        equation16.push(")");
        
        double exp16 = 57;
        
        // Eighteenth equation
        Queue<String> equation17 = new Queue<>(10, 2);        
        equation17.push("2");
        equation17.push("+");
        equation17.push("2");
        equation17.push("-");
        equation17.push("1");
        
        // Two plus two is four, minus one is three quick math!
        double exp17 = 3;
        
        // Nineteenth equation
        Queue<String> equation18 = new Queue<>(10, 2);        
        equation18.push("9");
        equation18.push("+");
        equation18.push("10");
        
        // What's 9 + 10 ? Tweenyyyyoneeeee!
        double exp18 = 19;
        
        // Twentieth equation
        Queue<String> equation19 = new Queue<>(10, 2);        
        equation19.push("9999");
        equation19.push("*");
        equation19.push("9999");
        
        double exp19 = 99980001;
        
        // Twenty first equation
        Queue<String> equation20 = new Queue<>(10, 2);        
        equation20.push("(");
        equation20.push("2");
        equation20.push("*");
        equation20.push("2");
        equation20.push(")");
        equation20.push("+");
        equation20.push("(");
        equation20.push("3");
        equation20.push("*");
        equation20.push("3");
        equation20.push(")");
        equation20.push("*");
        equation20.push("(");
        equation20.push("5");
        equation20.push("-");
        equation20.push("2");
        equation20.push(")");
        
        double exp20 = 31;
        
        // Twenty second equation
        Queue<String> equation21 = new Queue<>(10, 2);        
        equation21.push("(");
        equation21.push("2");
        equation21.push("*");
        equation21.push("2");
        equation21.push(")");
        equation21.push("-");
        equation21.push("(");
        equation21.push("3");
        equation21.push("*");
        equation21.push("3");
        equation21.push(")");
        equation21.push("-");
        equation21.push("(");
        equation21.push("5");
        equation21.push("-");
        equation21.push("2");
        equation21.push(")");
        
        double exp21 = -8;
        
        // Twenty third equation
        Queue<String> equation22 = new Queue<>(10, 2);        
        equation22.push("(");
        equation22.push("2.57");
        equation22.push("*");
        equation22.push("3.96");
        equation22.push(")");
        equation22.push("+");
        equation22.push("44.01");
        equation22.push("*");
        equation22.push("3.0");
        
        double exp22 = 142.2072;
        
        // Twenty fourth equation
        Queue<String> equation23 = new Queue<>(10, 2);        
        equation23.push("(");
        equation23.push("2.57");
        equation23.push("/");
        equation23.push("3.96");
        equation23.push(")");
        equation23.push("*");
        equation23.push("24.21");
        equation23.push("/");
        equation23.push("3.0");
        
        double exp23 = 5.237348484848485;
        
        // Twenty fourth equation
        Queue<String> equation24 = new Queue<>(10, 2);        
        equation24.push("(");
        equation24.push("(");
        equation24.push("(");
        equation24.push("3.50");
        equation24.push(")");
        equation24.push(")");
        equation24.push(")");
        
        double exp24 = 3.50;
        
        // Array of expected results
        Object[][] data = new Object[][] {
            {equation, exp},
            {equation1, exp1},
            {equation2, exp2},
            {equation3, exp3},
            {equation4, exp4},
            {equation5, exp5},
            {equation6, exp6},
            {equation7, exp7},
            {equation8, exp8},
            {equation9, exp9},
            {equation10, exp10},
            {equation11, exp11},
            {equation12, exp12},
            {equation13, exp13},
            {equation14, exp14},
            {equation15, exp15},
            {equation16, exp16},
            {equation17, exp17},
            {equation18, exp18},
            {equation19, exp19},
            {equation20, exp20},
            {equation21, exp21},
            {equation22, exp22},
            {equation23, exp23},
            {equation24, exp24}
        };
        return Arrays.asList(data);
    }
    
    // Private variables
    private final double expectedResult; 
    private final Evaluator evaluator;
    private final Queue<String> equation;
    private final Logger LOG = LoggerFactory.getLogger(getClass().getName());
    
    /**
     * Constructor that builds the data to test
     * @param equation          The equation to evaluate
     * @param expectedResult    The result expected
     */
    public EvaluatorTest(Queue<String> equation, double expectedResult) {
        this.equation = equation;
        this.evaluator = new Evaluator();
        this.expectedResult = expectedResult;
    }
    
    /**
     * Test each equation in the test array
     */
    @Test
    public void TestEquations() throws InvalidCharacter {
        Queue<String> expression = evaluator.getPostfix(equation);
        double obtained = evaluator.calculate(expression);
        
        LOG.info("Infix: " + equation.toString());
        LOG.info("Postfix: " + expression.toString());
        LOG.info("Expected: " + expectedResult);
        LOG.info("Obtained: " + obtained);
                     
        assertEquals(expectedResult, obtained, 0);
    }
}
