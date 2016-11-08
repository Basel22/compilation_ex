package exercise1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class sym {
	public static final int CLASS = 1;
	public static final int EXTENDS = 2;
	public static final int STATIC = 3;
	public static final int VOID = 4;
	public static final int INT= 5; 
	public static final int BOOLEAN = 6;
	public static final int STRING = 7;
	public static final int RETURN = 8;
	public static final int IF = 9;
	public static final int ELSE = 10;
	public static final int WHILE = 11;
	public static final int BREAK = 12;
	public static final int CONTINUE = 13;
	public static final int THIS = 14;
	public static final int NEW = 15;
	public static final int LENGTH = 16;
	public static final int TRUE = 17;
	public static final int FALSE = 18;
	public static final int NULL = 19;
	public static final int STRUCT = 20;
	public static final int OP = 21;
	public static final int ID = 22;
	public static final int CLASS_ID = 23;
	public static final int INTEGER = 24;
	public static final int STR = 25;
	public static final int EOF = 26;
	
	static final Map<String, String> myMap;
	static{
		Map<String, String> aMap = new HashMap<>();
        aMap.put(";", "SEMI");
        aMap.put("=", "ASSIGN");
        aMap.put(",", "COMMA");
        aMap.put("/", "DIVIDE");
        aMap.put(".", "DOT");
        aMap.put("==", "EQUAL");
        aMap.put(">", "GT");
        aMap.put(">=", "GTE");
        aMap.put("&&", "LAND");
        aMap.put("[", "LB");
        aMap.put("(", "LP");
        aMap.put("{", "LCBR");
        aMap.put("!", "LNEG");
        aMap.put("||", "LOR");
        aMap.put("<", "LT");
        aMap.put("=<", "LTE");
        aMap.put("-", "MINUS");
        aMap.put("%", "MOD");
        aMap.put("*", "MULTIPLY");
        aMap.put("!=", "NEQUAL");
        aMap.put("+", "PLUS");
        aMap.put("]", "RB");
        aMap.put("}", "RCBR");
        aMap.put(")", "RP");
        aMap.put("", "");
        myMap = Collections.unmodifiableMap(aMap);
	}
}
