package tp.p3.exceptions;

@SuppressWarnings("serial")
public class FileContentsException extends Exception {

	
	public FileContentsException(){
		super();
	}
	
	public FileContentsException(String msg){
		super(msg);
	}
	
	public FileContentsException(String msg, Throwable cause){
		super(msg,cause);
	}
	
	public FileContentsException(Throwable cause){
		super(cause);
	}
	
}
