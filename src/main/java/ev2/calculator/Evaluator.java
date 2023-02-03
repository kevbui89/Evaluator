package ev2.calculator;

import ev2.business.Queue;
import ev2.business.Stack;
import ev2.exception.InvalidCharacter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The InFixCalculator allows users to enter infix mathematical expressions and
 * obtain the postfix expression and value from that expression.
 * 
 * @author Kevin
 * @version 1.0
 */
public class Evaluator {
    
    private final Logger LOG = LoggerFactory.getLogger(Evaluator.class);
    private Queue<String> postfix;
    private Stack<String> operatorStack;
    private int bracketCounter;
    private String previous;
    private Stack<String> operationResults;
    
    public Evaluator(){}
    
    /**
     * Returns the bracket counter
     * @return 
     */
    public int getBracketCounter() {
        return bracketCounter;
    }
    
    /**
     * Creates the postfix expression of the infix expression given.
     * @param infix      The infix expression given.
     * @return postfix   Postfix expression of the equation passed
     */
    public Queue<String> getPostfix(Queue<String> infix) throws InvalidCharacter {
        LOG.info("Infix: " + infix);
        postfix = new Queue<>(10, 2);
        operatorStack = new Stack<>(10, 2);
        previous = "";
        bracketCounter = 0;
        
        while (!infix.isEmpty()) {
            String nextInfixElement = infix.pop();
            checkValue(nextInfixElement);
            if (!handleBracket(nextInfixElement)) {
                if (!isOperator(nextInfixElement)) {
                    postfix.push(nextInfixElement);
                } else {
                    addOperator(nextInfixElement);
                }
            }
            previous = nextInfixElement;
        }
        insertRemainingOperators();
        
        if (bracketCounter != 0) {
            throw new InvalidCharacter("Odd number of brackets, please check"
                    + "your bracket count.");
        }
        
        return postfix;
    } // end getPostfix()
    
    /**
     * Checks if the equation is properly formatted and contains valid values.
     * @param element   The current element in the equation to check
     */
    private void checkValue(String element) throws InvalidCharacter {
        LOG.info("Element: " + element);
        // Check if the element is null or empty
        if (element == null || element.equals("")) {
            throw new InvalidCharacter("Illegal value, equation is not "
                    + "properly formatted.");
        }
        
        // Checks if the previous value is a number
        // Consecutive numbers are invalid
        // If not, checks the previous character in the postfix
        if (isNumeric(previous)) {
            if (isNumeric(element) || element.equals("(")) {
                throw new InvalidCharacter("The following value must be"
                        + "an operator or an opening/closing bracket");
            }
        } else {
            switch (previous) {
                case "(":
                    // An operator cannot follow an opening bracket
                    if (isOperator(element)) {
                        throw new InvalidCharacter("Invalid expression");
                    }
                    // A closing bracket cannot follow an opening bracket
                    if (element.equals(")")) {
                        throw new InvalidCharacter("Invalid expression");
                    }
                    break;
                case ")":
                    // The following element must be an operator or a closing bracket
                    if (!isOperator(element) && !element.equals(")")) {
                        throw new InvalidCharacter("Invalid expression");
                    }
                    break;
                case "*":
                case "/":
                case "+":
                case "-":
                    // The element must be either a number or an opening brakcet
                    if (!(element.equals("(") || isNumeric(element))) {
                        throw new InvalidCharacter("The following value must "
                                + "be an opening bracket or a number");
                    }
                case "":
                    // Only checks the start of the expression
                    // Should always be a number or an opening brakcet
                    if (!(isNumeric(element) || element.equals("("))) {
                        throw new InvalidCharacter("Invalid expression");
                    }
                    break;
            }
        }
        
        
    }// End checkValue()
    
    /**
     * Checks if the element sent is a numeric value
     * @param element   The element to verify
     * @return          A boolean representation if the element is a boolean or not.
     */
    private boolean isNumeric(String element) {
        try {
            Double.parseDouble(element);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    } // End isNumeric()
    
    /**
     * Insert the current operator to the operator stack.
     * @param element   The element to be added
     */
    private void addOperator(String element) {
        LOG.info("Element: " + element);
        if (operatorStack.isEmpty()) {
            operatorStack.push(element);
        } else {
            insertOperator(element);
        }
    }
    
    /**
     * Handles brackets every time the expression reaches one.  It will either push
     * the bracket to the stack or clear it.
     * @param element   The current operator
     * @return          A boolean representing if a bracket was sent and dealt with.
     */
    private boolean handleBracket(String element) {
        switch(element) {
            case "(":
                bracketCounter++;
                operatorStack.push(element);
                return true;
            case ")":
                bracketCounter--;
                insertSectionOperators();
                return true;
        }
        return false;
    }
    
    /**
     * Checks if the current element is an operator or not.
     * @param element   The element to verify
     * @return          A boolean representation if the element was an operator or not.
     */
    private boolean isOperator(String element) {
        switch(element) {
            case "*":
            case "/":
            case "+":
            case "-":
                return true;
            default:
                return false;
        }
    }
    
    /**
     * Adds operators to the stack according to the rules of the expression.
     * @param operator      The current operator to add
     */
    private void insertOperator(String operator) {
        String currentOperator = operatorStack.peek();
        int precedence = getPrecedence(currentOperator);
        
        // Multiplication or division
        switch (precedence) {
            case 2:
                popAndSwapOperators(operator);
                // Addition or substraction
                break;
            case 1:
                if (getPrecedence(operator) == 2) {
                    operatorStack.push(operator);
                } else {
                    popAndSwapOperators(operator);
                }   break;
            default:
                operatorStack.push(operator);
                break;
        }
    } // End insertOperator()
    
    /**
     * Returns the precedence order of an operator
     * @param operator      The operator to evaluate
     * @return              The precedence index
     */
    private int getPrecedence(String operator) {
        switch(operator) {
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    } // End getPrecedence()
    
    /**
     * Pops and replace the current operator with the new one.
     * @param operator      The operator to be added
     */
    private void popAndSwapOperators(String operator) {
        boolean removeAgain;
        int lastOperatorPrecedence = getPrecedence(operator);
        
        do {
            removeAgain = false;
            postfix.push(operatorStack.pop());
            if (!operatorStack.isEmpty()) {
                int underCurrentPrecedence = getPrecedence(operatorStack.peek());
                if (lastOperatorPrecedence <= underCurrentPrecedence) {
                    removeAgain = true;
                }
            }
        } while(removeAgain);
        
        operatorStack.push(operator);
    } // End popAndSwapOperators()
    
    /**
     * Inserts all the operators found between two parenthesis.
     */
    private void insertSectionOperators() {
        String operator = operatorStack.peek();
        while(!operator.equals("(")) {
            postfix.push(operatorStack.pop());
            operator = operatorStack.peek();
        }
        // Removes the ( in the stack
        operatorStack.pop();
    } // End insertSectionOperators()
    
    /**
     * Removes all the remaining operators in the stack onto the postfix expression.
     */
    private void insertRemainingOperators() {
        while(!operatorStack.isEmpty()) {
            postfix.push(operatorStack.pop());
        }
    } // End insertRemainingOperators()
    
    /**
     * Computes the value for the infix expression passed from the constructor
     * @param postfixExpression     The postfix expression to be computed
     * @return                      The value of the infix expression
     */
    public double calculate(Queue<String> postfixExpression) {
        LOG.info("Postfix Expression: " + postfixExpression);
        operationResults = new Stack<>(10, 2);
        double operatorResult;
        
        while(!postfixExpression.isEmpty()) {
            String currentElement = postfixExpression.pop();
            
            if (isOperator(currentElement)) {
                operatorResult = performOperation(currentElement);
                operationResults.push(String.valueOf(operatorResult));
            } else {
                operationResults.push(currentElement);
            }
        }
        return Double.parseDouble(operationResults.pop());
    } // End calculate()
    
    /**
     * Performs the appropriate operation when an operator is encountered in the postfix
     * expression
     * @param operator  The operator sent to use for the operation
     * @return          The result of the operation
     */
    private double performOperation(String operator) {
        double num1 = Double.parseDouble(operationResults.pop());
        double num2 = Double.parseDouble(operationResults.pop());
        
        switch (operator) {
            case "*":
                return num2 * num1;
            case "+":
                return num2 + num1;
            case "-":
                return num2 - num1;
            case "/":
                if (num1 == 0.0) {
                    throw new ArithmeticException("Cannot divide by 0.");
                }
                return num2 / num1;
            default:
                return 0;
        }
    }
} // End Evaluator class
