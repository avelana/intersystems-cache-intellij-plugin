package by.vsu.cacheplugin.lang.mac;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import by.vsu.cacheplugin.lang.mac.psi.CacheObjectScriptMacTypes;
import com.intellij.psi.TokenType;

%%

%class CacheObjectScriptMacLexer
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
INTEGER_LITERAL={DECIMAL_INTEGER_LITERAL}|{HEX_INTEGER_LITERAL}|{OCTAL_INTEGER_LITERAL}
DECIMAL_INTEGER_LITERAL=(0|([1-9]({DIGIT})*))
HEX_INTEGER_LITERAL=0[Xx]({HEX_DIGIT})*
OCTAL_INTEGER_LITERAL=0({OCTAL_DIGIT})*

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
COMMAND = {COMMAND_1} | {COMMAND_2} | {COMMAND_3}|{COMMAND_4} | {COMMAND_5} | {COMMAND_6}| {COMMAND_7} | {COMMAND_8}
COMMAND_1=[Bb][Rr][Ee][Aa][Kk]|[Cc][Aa][Tt][Cc][Hh]|[Cc][Ll][Oo][Ss][Ee]|[Cc][Oo][Nn][Tt][Ii][Nn][Uu][Ee]
COMMAND_2=[Dd][Oo]|[Dd][Oo]+" "+[Ww][Hh][Ii][Ll][Ee]|[Ee][Ll][Ss][Ee]|[Ee][Ll][Ss][Ee][Ii][Ff]|[Ff][Oo][Rr]
COMMAND_3=[Rr][Ee][Aa][Dd]|[Gg][Oo][Tt][Oo]|[Hh][Aa][Ll][Tt]|[Hh][Aa][Nn][Gg]|[Ii][Ff]|[Jj][Oo][Bb]
COMMAND_4=[Qq][Uu][Ii][Tt]|[Ss][Ee][Tt]|[Tt][Cc][Oo][Mm][Mm][Ii][Tt]|[Tt][Hh][Rr][Oo][Ww]|[Ee][Xx][Ee][Cc][Uu][Tt][Ee]
COMMAND_5=[Tt][Rr][Yy]|[Tt][Ss][Tt][Aa][Rr][Tt]|[Uu][Ss][Ee]|[Vv][Ii][Ee][Ww]|[Ww][Hh][Ii][Ll][Ee]
COMMAND_6=[Zz][Kk][Ii][Ll][Ll]|[Zz][Nn][Ss][Pp][Aa][Cc][Ee]|[Zz][Tt][Rr][Aa][Pp]|[Zz][Ww][Rr][Ii][Tt][Ee]
COMMAND_7=[Zz][Zz][Dd][Uu][Mm][Pp]|[Mm][Ee][Rr][Gg][Ee]|[Nn][Ee][Ww]|[Oo][Pp][Ee][Nn]
COMMAND_8=[Kk][Ii][Ll][Ll]|[Ll][Oo][Cc][Kk]|[Tt][Rr][Oo][Ll][Ll][Bb][Aa][Cc][Kk]|[Ww][Rr][Ii][Tt][Ee]

%state IN_COMMAND
%state WAIT
%%

//command string
{KEY_CHARACTER}                                                 { yybegin(IN_COMMAND); return CacheObjectScriptMacTypes.LABEL; }
{WHITE_SPACE_CHAR}+                                             { yybegin(IN_COMMAND); return TokenType.WHITE_SPACE; }
{COMMENT}                                                       { yybegin(IN_COMMAND); return CacheObjectScriptMacTypes.COMMENT;}
{CRLF}                                                          { yybegin (YYINITIAL); return CacheObjectScriptMacTypes.CRLF; }
<IN_COMMAND> {COMMAND}                                                       { return CacheObjectScriptMacTypes.COMMAND; }
<IN_COMMAND> {OPERATIONS}                                                    { return CacheObjectScriptMacTypes.OPERATION;}

<IN_COMMAND> {IDENTIFIER}                                                    { return CacheObjectScriptMacTypes.LOCAL;}
<IN_COMMAND> {GLOBAL}{IDENTIFIER}                                            { return CacheObjectScriptMacTypes.GLOBAL;}
<IN_COMMAND> {SYS}{IDENTIFIER}                                               { return CacheObjectScriptMacTypes.SYS;}
<IN_COMMAND> {GLOBAL}{SYS}{IDENTIFIER}                                       { return CacheObjectScriptMacTypes.GLOBALSYS;}
<IN_COMMAND> {STRING}                                                        { return CacheObjectScriptMacTypes.STRING;}

<IN_COMMAND> {COMMA}                                                         { return CacheObjectScriptMacTypes.COMMA;}
<IN_COMMAND> {CRLF}                                                          { yybegin (YYINITIAL); return CacheObjectScriptMacTypes.CRLF; }
<IN_COMMAND> {COMMENT}                                                       { return CacheObjectScriptMacTypes.COMMENT;}

<IN_COMMAND> {INTEGER_LITERAL}                                               { return CacheObjectScriptMacTypes.NUMBER; }
<IN_COMMAND> {FLOAT_LITERAL}                                                 { return CacheObjectScriptMacTypes.NUMBER; }
<IN_COMMAND> {QUOTED_LITERAL}                                                { return CacheObjectScriptMacTypes.NUMBER; }
<IN_COMMAND> {DOUBLE_QUOTED_LITERAL}                                         { return CacheObjectScriptMacTypes.NUMBER; }
.                                                                            { return TokenType.BAD_CHARACTER; }



