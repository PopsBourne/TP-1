package tp.p3.exceptions;

@SuppressWarnings("serial")
public class SpaceNameException extends Exception {


	public SpaceNameException(){
		super();
	}
	
	public SpaceNameException(String msg){
		super(msg);
	}
	
	public SpaceNameException(String msg, Throwable cause){
		super(msg,cause);
	}
	
	public SpaceNameException(Throwable cause){
		super(cause);
	}
	
}
