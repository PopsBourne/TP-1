package tp.p3.exceptions;

@SuppressWarnings("serial")
public class InvalidPatternException extends Exception {


	public InvalidPatternException(){
		super();
	}
	
	public InvalidPatternException(String msg){
		super(msg);
	}
	
	public InvalidPatternException(String msg, Throwable cause){
		super(msg,cause);
	}
	
	public InvalidPatternException(Throwable cause){
		super(cause);
	}
	
}
