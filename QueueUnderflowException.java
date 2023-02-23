
/**
*
* @author  Estifanos Kebebew
*/
class QueueUnderflowException extends RuntimeException {

	public QueueUnderflowException() {
		this("This should have caused an QueueUnderflowException");
	}

	public QueueUnderflowException(String message) {
		super(message);
	}
}
