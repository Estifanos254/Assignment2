/*
 * @author Estifanos Kebebew
 */
import java.util.regex.Pattern;

public class Notation {

    static MyQueue<Character> queue;
    static MyStack<Character> stack;
    private static final String ops = "-+*/";
    private static final String operands = "0123456789";
    
    
    /**
     * a method to evaluatePostfixExpression to evaluate the postfix expression.
     * It will take in a string and return a double
     *
     * @param postfixExpr
     * @return
     * @throws InvalidNotationFormatException
     */
    public static double evaluatePostfixExpression​(java.lang.String postfixExpr)
            throws InvalidNotationFormatException{
        //checking for valid    
        if (postfixExpr == null || postfixExpr.length() == 0) {
            throw new InvalidNotationFormatException("Invalid postfix expression!");
        }
        MyStack<Double> stack = new MyStack<Double>();
        try {
            char[] chars = postfixExpr.toCharArray();

            for (char cr : chars) {
                if (isOperand(cr)) {
                    stack.push(new Double(cr -  '0')); 
                } else if (isOperator(cr)) {
                    Double opr1 = 0.0;
                    Double op2 = 0.0;
                    if (stack.isEmpty()) {
                        throw new InvalidNotationFormatException("Invalid format");
                    }
                    opr1 = stack.pop();
                    if (stack.isEmpty()) {
                        throw new InvalidNotationFormatException("Invalid format");
                    }
                    op2 = stack.pop();
                    Double result;
                    switch (cr) {
                        case '*':
                            result = opr1 * op2;
                            stack.push(result);
                            break;
                        case '/':
                            result = op2 / opr1;
                            stack.push(result);
                            break;
                        case '+':
                            result = opr1 + op2;
                            stack.push(result);
                            break;
                        case '-':
                            result = op2 - opr1;
                            stack.push(result);
                            break;
                    }
                }
            }
            return stack.pop();
        } catch (Exception ex) {
            throw new InvalidNotationFormatException("Invalid postfix expression!");
        }
        
    }


    /**
     * to convert infix notation to postfix notation
     *
     * @param infix
     * @return
     */
    public static java.lang.String convertInfixToPostfix​(java.lang.String infix)
            throws InvalidNotationFormatException {
        if (infix == null || infix.length() == 0 || ! parenthesisChecker(infix)) {
            throw new InvalidNotationFormatException("Invalid input");
        }
        MyStack<Character> stack = new MyStack<>();
        String postfix = "";
        int i = 0;
        while( i < infix.length()) {  // degac
            char cr = infix.charAt(i);
            if (Character.isDigit(cr)) {
                postfix += cr;
            } else if (cr == '(') {
                stack.push(cr);
            } else if (cr == ')') {
                while (!stack.isEmpty() && stack.top() != '(') {
                    postfix += stack.pop();
                }
                if (!stack.isEmpty() && stack.top() != '(') {
                    return "Invalid expression";
                } else {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && precedence(cr) <= precedence(stack.top())) {
                    postfix += stack.pop();
                }
                stack.push(cr);
            }
            i++;
        }
        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }
        return postfix;
    }

    private static int precedence(char cr) {
        switch (cr) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
        
             

    public static java.lang.String convertPostfixToInfix​(java.lang.String postfix)
            throws InvalidNotationFormatException{
        String infix = "";
        String nextChar;
        String operand = "";
        
        String sr = "";
        while (postfix.indexOf(' ') >= 0) {
            postfix = postfix.replaceAll(" ", "");
        }
        //introducing space after each token
        for (int i = 0; i < postfix.length(); i++) {
            sr = sr + postfix.charAt(i) + " ";
        }
        postfix = sr.trim();

        MyStack<String> postfixStack = new MyStack<String>(postfix.length());
        MyStack<String> infixStack = new MyStack<String>(postfix.length());

        try {
           //     infixStack.push(Character.toString(postfix.charAt(i)));
    
            int i = 0;
            while(i < postfix.length()) {
            	infixStack.push(Character.toString(postfix.charAt(i)));
            	i++;
            }

            while (!infixStack.isEmpty()) {
                postfixStack.push(infixStack.pop());
            }

            while (!postfixStack.isEmpty()) {
                nextChar = postfixStack.pop();
                if (nextChar.equalsIgnoreCase("+") || nextChar.equalsIgnoreCase("-")
                        || nextChar.equalsIgnoreCase("*") || nextChar.equalsIgnoreCase("/")
                        || nextChar.equalsIgnoreCase("%")) {
                    if (!operand.isEmpty()) {
                        // push the operand
                        // DEBUG: System.out.println("Operand: \"" + operand + "\" pushed to stack");
                        infixStack.push(operand);
                        operand = "";
                    }
                    try {
                        String op2 = infixStack.pop();
                        String opr1 = infixStack.pop();

                        if (!postfixStack.isEmpty()) {
                            // surround expression with parenthesis
                            String exp = "(" + opr1 + nextChar + op2 + ")";
                            infixStack.push(exp);
                        } else {
                            // last expression does not require surrounding parenthesis
                            String exp = opr1 + nextChar + op2;
                            // DEBUG: System.out.println("Expression: \"" + exp + "\" pushed to stack");
                            infixStack.push(exp);
                        }
                    } catch (StackUnderflowException e) {
                        throw new InvalidNotationFormatException("\nUnable to process user entered expression"
                                + "\nERROR - Invalid postfix expression entered"
                                + "\nThe stack was cleared to allow for additional processing of expressions\n\n");

                    }

                } else if (isNum(nextChar) || nextChar.equals(".")) {
                    // nextChar is a digit
                    operand += nextChar;
                } else if (nextChar.equalsIgnoreCase(" ")) {
                    // ignore all spaces
                    if (!operand.isEmpty()) {
                        // push the operand
                        // DEBUG: System.out.println("Operand: \"" + operand + "\" pushed to stack");
                        infixStack.push(operand);
                        operand = "";
                    }

                } else if (Pattern.matches("[A-Za-z]", nextChar.toString())) {
                    // nextChar is a letter
                    operand += nextChar;

                } else {
                    // unsupported character
                    sr = ("Invalid: \"" + nextChar + "\"");
                    sr += ("ERROR - unsupport value or character!");
                    sr += ("Program terminated with error");
                    throw new InvalidNotationFormatException(sr);

                }
            }

            infix = infixStack.top();
            if (infixStack.top().equals("")) {
                // unable to convert user entered Postfix expression 
                throw new InvalidNotationFormatException("Unable to convert user entered Postfix expression: \n  \""
                        + postfix + "\"");
            } else {
                // successfully converted user entered Postfix expression 
                // remove trailing space(s) from user entered expression and infix conversion string
                while (postfix.endsWith(" ")) {
                    postfix = postfix.trim();
                }
                while (infix.endsWith(" ")) {
                    infix = infix.substring(0, infix.length() - 2);
                }

            }
        } catch (Exception ex) {
            throw new InvalidNotationFormatException(sr);
        }
        return "(" + infix + ")";

    }
    



    /**
     * Method to check whether a string has parenthesis in balance
     *
     * @param sr
     * @return
     */
    private static boolean parenthesisChecker(String Sr) {
        int stack = 0;
        int i = 0;
        while ( i < Sr.length()) {
            char cr = Sr.charAt(i);
            if (cr == '(') {
                ++stack;
            } else if (cr == ')') {
                --stack;
                if (stack < 0) {
                    return false;
                }
            }
            ++i;
        }
        return stack == 0;
    }

    private static boolean isOperator(char v) {
        return ops.indexOf(v) >= 0;
    }

    private static boolean isOperand(char v) {
        return operands.indexOf(v) >= 0;
    }

    private static boolean isNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException numForEx) {
            return false;
        }
    }

   
    
}