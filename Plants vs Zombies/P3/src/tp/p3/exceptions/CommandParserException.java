package tp.p3.exceptions;

@SuppressWarnings("serial")
public class CommandParserException extends Exception{

	public CommandParserException(){
		super();
	}
	
	public CommandParserException(String msg){
		super(msg);
	}
	
	public CommandParserException(String msg, Throwable cause){
		super(msg,cause);
	}
	
	public CommandParserException(Throwable cause){
		super(cause);
	}
}
