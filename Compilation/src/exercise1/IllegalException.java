package exercise1;

public class IllegalException extends Exception  {
	
	/**
	 *	Auto generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private int mLine;
	private String mMessage;
	private String mValue;
	
	public IllegalException(String message, String value, int line){
		super(message);
		mMessage = message;
		mLine = line;
		mValue = value;
	}

	public String getMessage() {
		return mMessage;
	}
	
	public int getLine(){
		return mLine;
	}
	
	public String getValue(){
		return mValue;
	}
}
