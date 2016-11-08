/* declarations of flags */

%%

%class Lexer
%unicode
%public
%function next_token
%type Container
%line
%column
%scanerror Exception
%line


/* copy and paste methods and exception handlers */

%{
Container getContainer(int id,  String tag) { 
	return new Container(id, yyline + 1, yycolumn + 1, tag , yytext());
}

void throwIllegalException(String msg, String value, int line) throws IllegalException {
	throw new IllegalException(msg, value, line);
}

%}

/* terminators */

LINE_TERMINATOR = (\n|\r|\r\n)
WHITESPACE = {LINE_TERMINATOR} | [ \t]

/* bloat lines and comments */

COMMENT_LINE = "//" .* {LINE_TERMINATOR}
LONG_COMMENT = "/*"~"*/"
COMMENT = {LONG_COMMENT} | {COMMENT_LINE}
COMMENT_ERROR = "/*" ([^\*] | (\*[^/]))*


/* arithmetics and brackets */

STRUCT = ([{};,] | "\(" | "\)" | "\[" | "\]" | "\." | "<" | ">")
OP =	"-" | "!" | "*" | "/" | "%" | "+" | "-" 
		 | "<=" | ">=" | "==" | "!=" | "&&" | "\|\|" | "="
		
/* legal digits, alphabetical cars and definers */
INTEGER = 0 | [1-9][0-9]*
ZERO_FIRST_INTEGER = 0[0-9]+
LETTER = [A-Za-z0-9_]
ID = [a-z]{LETTER}*
CLASS_ID = [A-Z]{ID}*

/* quotes and escape sequences */

QUOTE = "\""
QUOTE_CHAR = "\\\""
CHAR = [[\x20-\x21\x23-\x5B\x5D-\x7E]("\\n")("\\\\")("\\\"")("\\t")]
STRING = {QUOTE}{CHAR}*{QUOTE}
OPEN_STRING = {QUOTE}[^\"]*


/* comments comments comments */

%%

{COMMENT}			{ /* skipping comments */ }
{WHITESPACE}		{ /* skipping whitespaces */ }


/* defined language words */ 

"class"	   	{ return getContainer(sym.CLASS, "CLASS"); }
"extends" 	{ return getContainer(sym.EXTENDS, "EXTENDS"); }
"static" 	{ return getContainer(sym.STATIC, "STATIC"); }
"void" 		{ return getContainer(sym.VOID, "VOID"); }
"int" 		{ return getContainer(sym.INT, "INT"); }
"boolean" 	{ return getContainer(sym.BOOLEAN, "BOOLEAN"); }
"string" 	{ return getContainer(sym.STRING, "STRING"); }
"return" 	{ return getContainer(sym.RETURN, "RETURN"); }
"if" 		{ return getContainer(sym.IF, "IF"); }
"else" 		{ return getContainer(sym.ELSE, "ELSE"); }
"while" 	{ return getContainer(sym.WHILE, "WHILE"); }
"break" 	{ return getContainer(sym.BREAK, "BREAK"); }
"continue" 	{ return getContainer(sym.CONTINUE, "CONTINUE"); }
"this" 		{ return getContainer(sym.THIS, "THIS"); }
"new" 		{ return getContainer(sym.NEW, "NEW"); }
"length" 	{ return getContainer(sym.LENGTH, "LENGTH"); }
"true" 		{ return getContainer(sym.TRUE, "TRUE"); }
"false" 	{ return getContainer(sym.FALSE, "FALSE"); }
"null" 		{ return getContainer(sym.NULL, "NULL"); }

{STRUCT} 	{return getContainer(sym.STRUCT, sym.myMap.get(yytext())); }
{OP} 		{return getContainer(sym.OP, sym.myMap.get(yytext())); }

{ID} 		{ return getContainer(sym.ID, "ID"); }
{CLASS_ID} 	{ return getContainer(sym.CLASS_ID, "CLASS_ID"); }

{INTEGER} 	{return getContainer(sym.INTEGER, "INTEGER"); }

{STRING} 	{return getContainer(sym.STR, "STRING"); }

/* exception handlers of illegal terms */

{OPEN_STRING} 			{ throwIllegalException("Open string!", yytext(), yyline+1); }
{COMMENT_ERROR} 		{ throwIllegalException("Open comment!", yytext(), yyline+1); }
{ZERO_FIRST_INTEGER}	{ throwIllegalException("Number with a leading zero!", yytext(), yyline+1); }
. 						{ throwIllegalException("Illegal character", yytext(), yyline+1); }