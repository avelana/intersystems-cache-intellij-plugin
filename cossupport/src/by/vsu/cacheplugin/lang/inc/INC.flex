package by.vsu.cacheplugin.lang.inc;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import by.vsu.cacheplugin.lang.inc.psi.CacheObjectScriptIncTypes;
import com.intellij.psi.TokenType;

%%

%class CacheObjectScriptIncLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

/* digits */
DIGIT=[0-9]
OCTAL_DIGIT=[0-7]
HEX_DIGIT=[0-9A-Fa-f]
INTEGER_LITERAL={DECIMAL_INTEGER_LITERAL}|{HEX_INTEGER_LITERAL}
DECIMAL_INTEGER_LITERAL=(0|([1-9]({DIGIT})*))
HEX_INTEGER_LITERAL=0[Xx]({HEX_DIGIT})*

FLOAT_LITERAL=({FLOATING_POINT_LITERAL1})|({FLOATING_POINT_LITERAL2})|({FLOATING_POINT_LITERAL3})|({FLOATING_POINT_LITERAL4})
FLOATING_POINT_LITERAL1=({DIGIT})+"."({DIGIT})*({EXPONENT_PART})?
FLOATING_POINT_LITERAL2="."({DIGIT})+({EXPONENT_PART})?
FLOATING_POINT_LITERAL3=({DIGIT})+({EXPONENT_PART})
FLOATING_POINT_LITERAL4=({DIGIT})+
EXPONENT_PART=[Ee]["+""-"]?({DIGIT})*

QUOTED_LITERAL="'"([^\\\'\r\n]|{ESCAPE_SEQUENCE})*("'"|\\)?
DOUBLE_QUOTED_LITERAL=\"([^\\\"\r\n]|{ESCAPE_SEQUENCE})*(\"|\\)?
ESCAPE_SEQUENCE=\\[^\r\n]
NUM = ({FLOAT_LITERAL})| ({INTEGER_LITERAL}) |  ({QUOTED_LITERAL}) |  ({DOUBLE_QUOTED_LITERAL})

/* spaces */
WHITE_SPACE_CHAR=[\ \t\f]
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
CRLF={LineTerminator}
COMMA = [,]

/* operations */
OPERATIONS = {OPS1}|{OPS2}
OPS1 = "++"|"--"|"'="|"<"|">"|"<="|">="|"<<"|">>"|"&"|"&&"|"|"|"||"|"+="|"-="|"*="|"/="|"&="
OPS2 = "|="|"^="|"%="|"<<="|">>="|"="|"\'"|"~"|"?"|":"|"+"|"-"|"*"|"/"|"^"|"%"

/* identifiers & symbols */
IDENTIFIER = ([:letter:]|%) ([:letter:]|{DIGIT}|_ )*
VALUE_CHARACTER=[^\n\r\f\\] | "\\"{CRLF} | "\\".
VALUE = {VALUE_CHARACTER}* {CRLF}
KEY_SEPARATOR=[\ \t]*[=][\ \t]* | [\ \t]+
KEY_CHARACTER=[a-zA-Z0-9]+ [:]
SYS = "$"
GLOBAL = "^"
STRING = "\""+{VALUE_CHARACTER}*+"\"" | "'"+{VALUE_CHARACTER}*+"'"
/* comments */
COMMENT = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment} | {CacheComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}*
CacheComment = ";" {InputCharacter}*
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

/* commands */
COMMAND = {COMMAND_1} | {COMMAND_2} | {COMMAND_3}
COMMAND_1="BREAK"|"CATCH"|"CLOSE"|"CONTINUE"|"DO"|"DO WHILE"|"ELSE"|"ELSEIF"|"FOR"|"GOTO"|"HALT"|"HANG"
COMMAND_2="READ"|"IF"|"JOB"|"KILL"|"LOCK"|"MERGE"|"NEW"|"OPEN"|"QUIT"|"SET"|"TCOMMIT"|"THROW"|"TROLLBACK"
COMMAND_3="TRY"|"TSTART"|"USE"|"VIEW"|"WHILE"|"WRITE"|"XECUTE"|"ZKILL"|"ZNSPACE"|"ZTRAP"|"ZWRITE"|"ZZDUMP"
START = {KEY_CHARACTER} | {WHITE_SPACE_CHAR}+ | {COMMENT}
ERROR = !{START}

%state IN_COMMAND
%state WAIT
%%

//command string
{KEY_CHARACTER}                                                 { yybegin(IN_COMMAND); return CacheObjectScriptIncTypes.LABEL; }
{WHITE_SPACE_CHAR}+                                             { yybegin(IN_COMMAND); return TokenType.WHITE_SPACE; }
{COMMENT}                                                       { yybegin(IN_COMMAND); return CacheObjectScriptIncTypes.COMMENT;}
{CRLF}                                                          { yybegin (YYINITIAL); return CacheObjectScriptIncTypes.CRLF; }
<IN_COMMAND> {COMMAND}                                                       { return CacheObjectScriptIncTypes.COMMAND; }
<IN_COMMAND> {OPERATIONS}                                                    { return CacheObjectScriptIncTypes.OPERATION;}

<IN_COMMAND> {IDENTIFIER}                                                    { return CacheObjectScriptIncTypes.LOCAL;}
<IN_COMMAND> {GLOBAL}{IDENTIFIER}                                            { return CacheObjectScriptIncTypes.GLOBAL;}
<IN_COMMAND> {SYS}{IDENTIFIER}                                               { return CacheObjectScriptIncTypes.SYS;}
<IN_COMMAND> {GLOBAL}{SYS}{IDENTIFIER}                                       { return CacheObjectScriptIncTypes.GLOBALSYS;}
<IN_COMMAND> {STRING}                                                        { return CacheObjectScriptIncTypes.STRING;}

<IN_COMMAND> {COMMA}                                                         { return CacheObjectScriptIncTypes.COMMA;}
<IN_COMMAND> {CRLF}                                                          { yybegin (YYINITIAL); return CacheObjectScriptIncTypes.CRLF; }
.                                                                            { return TokenType.BAD_CHARACTER; }
<IN_COMMAND> {COMMENT}                                                       { return CacheObjectScriptIncTypes.COMMENT;}

<IN_COMMAND> {INTEGER_LITERAL}                                               { return CacheObjectScriptIncTypes.NUMBER; }
<IN_COMMAND> {FLOAT_LITERAL}                                                 { return CacheObjectScriptIncTypes.NUMBER; }
<IN_COMMAND> {QUOTED_LITERAL}                                                { return CacheObjectScriptIncTypes.NUMBER; }
<IN_COMMAND> {DOUBLE_QUOTED_LITERAL}                                         { return CacheObjectScriptIncTypes.NUMBER; }


