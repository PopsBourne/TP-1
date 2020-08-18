package tp.p3.exceptions;

@SuppressWarnings("serial")
public class NoFileNameException extends Exception {


	public NoFileNameException(){
		super();
	}
	
	public NoFileNameException(String msg){
		super(msg);
	}
	
	public NoFileNameException(String msg, Throwable cause){
		super(msg,cause);
	}
	
	public NoFileNameException(Throwable cause){
		super(cause);
	}
	
}
