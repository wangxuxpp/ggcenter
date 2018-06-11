package base.exception;

@SuppressWarnings("serial")
public class CenterConnectException extends RuntimeException {

	public CenterConnectException() {
		super();
	}
	
	public CenterConnectException(String message) {
		super(message);
	}

	public CenterConnectException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public CenterConnectException(Exception er) {
        super(er.getMessage());
    }
}
