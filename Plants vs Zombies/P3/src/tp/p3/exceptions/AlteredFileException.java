package tp.p3.exceptions;

@SuppressWarnings("serial")
public class AlteredFileException extends Exception {

	public AlteredFileException() {
		super();
	}

	public AlteredFileException(String msg) {
		super(msg);
	}

	public AlteredFileException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AlteredFileException(Throwable cause) {
		super(cause);
	}
}
