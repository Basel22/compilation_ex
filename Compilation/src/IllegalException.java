
public class IllegalException extends Exception  {
	
	/**
	 *	Auto generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private int mLine;
	private String mMessage;
	
	public IllegalException(String message, int line){
		super(message);
		mMessage = message;
		mLine = line;
	}

	public String getMessage() {
		return mMessage;
	}
	
	public int getLine(){
		return mLine;
	}
	
	
}
