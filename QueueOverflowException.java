


/**
 *
 * @author  EK
 */
class QueueOverflowException extends RuntimeException {

	public QueueOverflowException() {
		this("This should have caused an StackOverflowException");
	}

	public QueueOverflowException(String message) {
		super(message);
	}
}