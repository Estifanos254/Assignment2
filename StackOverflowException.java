

/**
*
* @author  Estifanos K
*/
class StackOverflowException extends RuntimeException {

   public StackOverflowException(String stack_is_full) {
       super(stack_is_full);
   }

public StackOverflowException() {
	// TODO Auto-generated constructor stub
}

}