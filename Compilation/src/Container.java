import java_cup.runtime.Symbol;

public class Container extends Symbol{
	private int mId;
	private int mLine;
	private int mColumn;
	private String mTag;
	private String mValue;

	public Container(int id, int line, int column, String tag, String value){
		super(id, line);
		mLine = line;
		mId = id;
		mColumn = column;
		mTag = tag;
		mValue = value;
	}
	
	public int getId() {
		return mId;
	}

	public int getLine() {
		return mLine;
	}

	public int getColumn() {
		return mColumn;
	}

	public String getTag() {
		return mTag;
	}

	public String getValue() {
		return mValue;
	}
}
