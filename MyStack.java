import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> {

    private T[] stackArray;
    private int size;
    private int top;

    /**
	 * Provide two constructors
	 * 1. takes in an int as the size of the stack
	 * 2. default constructor - uses default as the size of the stack
	 */
    public MyStack() {
        this(10); // default size of 10
    }
    public MyStack(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        this.size = size;
        top = -1; // stack is initially empty
        stackArray = (T[]) new Object[size]; // create array of generic type T
    }

    /**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
    @Override
    public boolean isFull() {
        return top == size - 1;
    }

    /**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        }
        T element = stackArray[top];
        stackArray[top--] = null; // remove the element from the stack
        return element;
    }

    /**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        }
        return stackArray[top];
    }

    /**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
    @Override
    public int size() {
        return top + 1;
    }

    /**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
    @Override
    public boolean push(T e) throws StackOverflowException {
        if (isFull()) {
            throw new StackOverflowException("Stack is full");
        }
        stackArray[++top] = e;
        return true;
    }

    /**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
    @Override
    public String toString() {
        String text = new String();
        for (int i = 0; i <= top; i++) {
            text += stackArray[i];
        }
        return text;
    }

    /**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
    @Override
    public String toString(String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            sb.append(stackArray[i]).append(delimiter);
        }
        // remove the last delimiter
        if (sb.length() > 0) {
            sb.setLength(sb.length() - delimiter.length());
        }
        return sb.toString();
    }

    /**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
    @Override
    public void fill(ArrayList<T> list) {
        // make a copy of the list
        ArrayList<T> copy = new ArrayList<>(list);
        for (T element : copy) {
            push(element);
        }
    }

}
