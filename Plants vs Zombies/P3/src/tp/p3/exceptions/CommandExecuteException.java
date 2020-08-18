package tp.p3.exceptions;

@SuppressWarnings("serial")
public class CommandExecuteException extends Exception {

	public CommandExecuteException(){
		super();
	}
	
	public CommandExecuteException(String msg){
		super(msg);
	}
	
	public CommandExecuteException(String msg, Throwable cause){
		super(msg,cause);
	}
	
	public CommandExecuteException(Throwable cause){
		super(cause);
	}
	
}
