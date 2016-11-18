package exercise2;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	/***
	 * this method returns all regex expression to catch operators in a list
	 */
	public static List<String[]> getAllRegexExpressions(){
		String[] switchRegex = {"R(\\d*)\\s<->\\sR(\\d*)", rowOpType.SWITCH.toString()};
		String[] multiplyRegex = {"R(\\d*)\\s<-\\s(\\d*\\/*\\d*)R\\d*", rowOpType.MULTI.toString()};
		String[] factorRegex = {"R(\\d*)\\s<-\\sR\\d*\\s([+])\\s(\\d*\\/*\\d*)R(\\d*)", rowOpType.FACTOR.toString()};
		List<String[]> regexExpressions = new ArrayList<>();
		
		regexExpressions.add(switchRegex);
		regexExpressions.add(factorRegex);
		regexExpressions.add(multiplyRegex);
		
		return regexExpressions;
	}
}
