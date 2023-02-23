/*
 * 
 */
import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {

    private T[] queueArray;
    private int size;
    private int first;
    private int last;

    /** provide two constructors 
	 * 1. takes an int as the size of the queue
	 * 2. default constructor - uses a default as the size of the queue
	 * 
	 */
    public MyQueue() {
        this(10);
    }
    public MyQueue(int capacity) {
        this.queueArray = (T[]) new Object[capacity];
        this.size = 0;
        this.first = 0;
        this.last = -1;
    }

	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
    public boolean isFull() {
        return this.size == this.queueArray.length;
    }

    /**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException("Queue is empty");
        }
        T item = this.queueArray[this.first];
        this.first = (this.first + 1) % this.queueArray.length;
        this.size--;
        return item;
    }

    /**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
    public int size() {
        return this.size;
    }

    /**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
    public boolean enqueue(T e) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException("Queue is full");
        }
        this.last = (this.last + 1) % this.queueArray.length;
        this.queueArray[this.last] = e;
        this.size++;
        return true;
    }

    /**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
    public String toString() {
        String text = new String();
        
        for (int i = 0; i < this.size; i++) {
            int index = (this.first + i) % this.queueArray.length;
            text += this.queueArray[index].toString();
        }
        
        return text;
    }

    /**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
    public String toString(String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            int index = (this.first + i) % this.queueArray.length;
            sb.append(this.queueArray[index].toString());
            if (i < this.size - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full
	 
	  */
    public void fill(ArrayList<T> list) throws QueueOverflowException {
        if (list.size() > this.queueArray.length - this.size) {
            throw new QueueOverflowException("Not enough space in queue");
        }
        for (T item : list) {
            enqueue(item);
        }
    }
}
