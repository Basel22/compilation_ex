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

"class"	   	{ return getContainer(sym.CLASS, "class"); }
"extends" 	{ return getContainer(sym.EXTENDS, "extends"); }
"static" 	{ return getContainer(sym.STATIC, "static"); }
"void" 		{ return getContainer(sym.VOID, "void"); }
"int" 		{ return getContainer(sym.INT, "int"); }
"boolean" 	{ return getContainer(sym.BOOLEAN, "boolean"); }
"string" 	{ return getContainer(sym.STRING, "string"); }
"return" 	{ return getContainer(sym.RETURN, "return"); }
"if" 		{ return getContainer(sym.IF, "if"); }
"else" 		{ return getContainer(sym.ELSE, "else"); }
"while" 	{ return getContainer(sym.WHILE, "while"); }
"break" 	{ return getContainer(sym.BREAK, "break"); }
"continue" 	{ return getContainer(sym.CONTINUE, "continue"); }
"this" 		{ return getContainer(sym.THIS, "this"); }
"new" 		{ return getContainer(sym.NEW, "new"); }
"length" 	{ return getContainer(sym.LENGTH, "length"); }
"true" 		{ return getContainer(sym.TRUE, "true"); }
"false" 	{ return getContainer(sym.FALSE, "false"); }
"null" 		{ return getContainer(sym.NULL, "null"); }

{STRUCT} 	{return getContainer(sym.STRUCT, yytext()); }
{OP} 		{return getContainer(sym.OP, yytext()); }

{ID} 		{ return getContainer(sym.ID, "ID"); }
{CLASS_ID} 	{ return getContainer(sym.CLASS_ID, "CLASS_ID"); }

{INTEGER} 	{return getContainer(sym.INTEGER, "INTEGER"); }

{STRING} 	{return getContainer(sym.STR, "STRING"); }

/* exception handlers of illegal terms */

{OPEN_STRING} 			{ throwIllegalException("Open string!", yytext(), yyline+1); }
{COMMENT_ERROR} 		{ throwIllegalException("Open comment!", yytext(), yyline+1); }
{ZERO_FIRST_INTEGER}	{ throwIllegalException("Number with a leading zero!", yytext(), yyline+1); }
. 						{ throwIllegalException("Illegal character", yytext(), yyline+1); }