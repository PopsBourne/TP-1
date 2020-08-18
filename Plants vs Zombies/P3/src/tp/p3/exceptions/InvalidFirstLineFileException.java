package tp.p3.exceptions;

@SuppressWarnings("serial")
public class InvalidFirstLineFileException extends Exception {
	
	public InvalidFirstLineFileException(){
		super();
	}
	
	public InvalidFirstLineFileException(String msg){
		super(msg);
	}
	
	public InvalidFirstLineFileException(String msg, Throwable cause){
		super(msg,cause);
	}
	
	public InvalidFirstLineFileException(Throwable cause){
		super(cause);
	}
}
