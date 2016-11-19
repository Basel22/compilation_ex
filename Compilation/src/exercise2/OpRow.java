package exercise2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpRow {
	
	private ops mOperatorType;
	private rowOpType mType;
	private int mRowSrc;
	private int mRowDst;
	private Scalar mFactor;
	
	public OpRow(ops opType, int row_src, int row_dst, Scalar factor, rowOpType type){
		mOperatorType = opType;
		mRowSrc = row_src;
		mRowDst = row_dst;
		mFactor = factor;
		mType = type;
	}
	
	/***
	 * empty constructor
	 */
	public OpRow(){
		
	}

	/***
	 * 
	 * @param path2file
	 * 		path to the operations file that needs to be parsed
	 * @return
	 * 		return an OpRow object containing the relevant data
	 */
	public static List<OpRow> parseOpFile(String path2file){
		List<String[]> regexExp = Constants.getAllRegexExpressions();
		List<OpRow> listOfOps = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader(path2file))){
			String opLine;
			while ((opLine = reader.readLine()) != null) {
				for (String[] expression: regexExp){
					//build the pattern object
					Pattern pattern = Pattern.compile(expression[0]);
					
					//build the matcher object
					Matcher matcher = pattern.matcher(opLine);
					
					if (matcher.find()){
						OpRow row = new OpRow();
						String checkScalar;
						switch(rowOpType.valueOf(expression[1])){
						case FACTOR:
							row.setmType(rowOpType.FACTOR);
							row.setmRowSrc(Integer.parseInt(matcher.group(1)));
							row.setmRowDst(Integer.parseInt(matcher.group(4)));
							row.setmOperatorType(ops.ADD);
							checkScalar = matcher.group(3);
							if (checkScalar.contains("/")){
								String[] comps = checkScalar.split("/");
								row.setmFactor(new Scalar(Integer.parseInt(comps[0]), Integer.parseInt(comps[1])));
							}
							break;
						case MULTI:
							row.setmType(rowOpType.MULTI);
							row.setmRowSrc(Integer.parseInt(matcher.group(1)));
							row.setmRowDst(Integer.parseInt(matcher.group(1)));
							row.setmOperatorType(null);
							checkScalar = matcher.group(2);
							if (checkScalar.contains("/")){
								String[] comps = checkScalar.split("/");
								row.setmFactor(new Scalar(Integer.parseInt(comps[0]), Integer.parseInt(comps[1])));
							}
							break;
						case SWITCH:
							row.setmType(rowOpType.SWITCH);
							row.setmRowSrc(Integer.parseInt(matcher.group(1)));
							row.setmRowDst(Integer.parseInt(matcher.group(2)));
							row.setmOperatorType(null);
							row.setmFactor(null);
							break;
						default:
							break;
							
						}
						listOfOps.add(row);
						break;
					}
				}
		    }
			return listOfOps;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*Setters and Getters section*/
	
	public void setmOperatorType(ops mOperatorType) {
		this.mOperatorType = mOperatorType;
	}

	public void setmRowSrc(int mRowSrc) {
		this.mRowSrc = mRowSrc;
	}

	public void setmRowDst(int mRowDst) {
		this.mRowDst = mRowDst;
	}

	public void setmFactor(Scalar mFactor) {
		this.mFactor = mFactor;
	}

	public void setmType(rowOpType mType) {
		this.mType = mType;
	}

	public ops getmOperatorType() {
		return mOperatorType;
	}

	public rowOpType getmType() {
		return mType;
	}

	public int getmRowSrc() {
		return mRowSrc;
	}

	public int getmRowDst() {
		return mRowDst;
	}

	public Scalar getmFactor() {
		return mFactor;
	}
}
