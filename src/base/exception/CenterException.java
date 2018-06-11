package base.exception;

@SuppressWarnings("serial")
public class CenterException extends RuntimeException {
	public CenterException() {
		super();
	}
	
	public CenterException(String message) {
		super(message);
	}

	public CenterException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public CenterException(Exception er) {
        super(er.getMessage());
    }
}
