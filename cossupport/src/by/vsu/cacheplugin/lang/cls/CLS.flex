package by.vsu.cacheplugin.lang.cls;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import by.vsu.cacheplugin.lang.cls.psi.CacheObjectScriptClsTypes;
import com.intellij.psi.TokenType;

%%

%class CacheObjectScriptClsLexer
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
IDENTIFIER = ([:letter:]|%|_) ([:letter:]|{DIGIT}|_ )*
VALUE_CHARACTER=[^\n\r\f\\] | "\\"{CRLF} | "\\".
VALUE = {VALUE_CHARACTER}* {CRLF}
KEY_SEPARATOR=[\ \t]*[=][\ \t]* | [\ \t]+
KEY_CHARACTER=[a-zA-Z0-9]+ [:]
SYS = "$"
GLOBAL = "^"
STRING = "\""+{VALUE_CHARACTER}*+"\"" | "'"+{VALUE_CHARACTER}*+"'"
/* comments */
COMMENT = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
//| {CacheComment}
DOT= "."
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}*
//CacheComment = ";" {InputCharacter}*
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
DescriptionComment = "///" {InputCharacter}*
/* commands */
COMMAND = {COMMAND_1} | {COMMAND_2} | {COMMAND_3}
COMMAND_1="BREAK"|"CATCH"|"CLOSE"|"CONTINUE"|"DO"|"DO WHILE"|"ELSE"|"ELSEIF"|"FOR"|"GOTO"|"HALT"|"HANG"
COMMAND_2="READ"|"IF"|"JOB"|"KILL"|"LOCK"|"MERGE"|"NEW"|"OPEN"|"QUIT"|"SET"|"TCOMMIT"|"THROW"|"TROLLBACK"
COMMAND_3="TRY"|"TSTART"|"USE"|"VIEW"|"WHILE"|"WRITE"|"XECUTE"|"ZKILL"|"ZNSPACE"|"ZTRAP"|"ZWRITE"|"ZZDUMP"
START = {KEY_CHARACTER} | {WHITE_SPACE_CHAR}+ | {COMMENT}
ERROR = !{START}

/*class def*/
IMPORT = [Ii][Mm][Pp][Oo][Rr][Tt]
INCLUDE = [Ii][Nn][Cc][Ll][Uu][Dd][Ee] | [Ii][Nn][Cc][Ll][Uu][Dd][Ee][Gg][Ee][Nn][Ee][Rr][Aa][Tt][Oo][Rr]
CLASS= [Cc][Ll][Aa][Ss][Ss]
LBRASE = "{"
RBRASE = "}"
PACKAGE = {IDENTIFIER} {DOT}
EXTENDS = [Ee][Xx][Tt][Ee][Nn][Dd][Ss]
AS = [Aa][Ss]
CLASSNAME={PACKAGE}*{IDENTIFIER}
LPARENTHESIS="("
RPARENTHESIS=")"
LBRACKET="["
RBRACKET="]"
EQ="="

/*foreign key def*/
FKEY=[Ff][Oo][Rr][Ee][Ii][Gg][Nn][Kk][Ee][Yy]
REF=[Rr][Ee][Ff][Ee][Rr][Ee][Nn][Cc][Ee][Ss]
SEMICOLON=";"

/*index def*/
INDEX=[Ii][Nn][Dd][Ee][Xx]
ON=[Oo][Nn]

/*class states*/
%state IN_CLASSNAME, IN_CLASSENTRY, IN_CLASSICLUDE, IN_CLASSIMPORT,IN_OTHER,IN_FILENAME
%state IN_CLASS_LIST,IN_AS,IN_EXTENDS,IN_KEYWORDS,IN_ARGS,IN_CLASS,IN_CLASS_LIST_EXT
/*foreign key states*/
%state IN_FKEY,IN_FKEY_PROPS,IN_FKEY_OTHER,IN_FKEY_REF,IN_FKEYWORDS,IN_FARGS
/*index key states*/
%state IN_INDEX,IN_INDEX_PROPS,IN_INDEX_OTHER,IN_INDEX_ON,IN_IKEYWORDS,IN_IARGS,IN_INDEX_ENTRY
%state WAIT
%%

//command string
{CLASS}                                                { yybegin (IN_CLASS);  return CacheObjectScriptClsTypes.CLASS; }
<IN_CLASS> {
        {CLASSNAME}                                    { yybegin (IN_OTHER);return CacheObjectScriptClsTypes.CLASSNAME;  }
}
{INCLUDE}                                              { yybegin (IN_FILENAME); return CacheObjectScriptClsTypes.INCLUDE; }
{IMPORT}                                               { yybegin (IN_CLASSNAME); return CacheObjectScriptClsTypes.IMPORT; }
<IN_FILENAME> {
    {CLASSNAME}                                        { yybegin (YYINITIAL);return CacheObjectScriptClsTypes.FILENAME;  }
    {LPARENTHESIS}                                     { yybegin (IN_CLASS_LIST);return CacheObjectScriptClsTypes.LPAR;}
    <IN_CLASS_LIST>{
        {CLASSNAME}                                    { return CacheObjectScriptClsTypes.FILENAME;}
        {COMMA}                                        { return CacheObjectScriptClsTypes.COMMA;}
        {RPARENTHESIS}                                 { yybegin (YYINITIAL); return CacheObjectScriptClsTypes.RPAR;}
    }
    {WHITE_SPACE_CHAR}+                                { return TokenType.WHITE_SPACE; }
}
<IN_CLASSNAME> {
    {CLASSNAME}                                        { yybegin (YYINITIAL);return CacheObjectScriptClsTypes.CLASSNAME;  }
    {LPARENTHESIS}                                     { yybegin (IN_CLASS_LIST);return CacheObjectScriptClsTypes.LPAR;}
    <IN_CLASS_LIST>{
        {CLASSNAME}                                    { return CacheObjectScriptClsTypes.CLASSNAME;}
        {COMMA}                                        { return CacheObjectScriptClsTypes.COMMA;}
        {RPARENTHESIS}                                 { yybegin (YYINITIAL); return CacheObjectScriptClsTypes.RPAR;}
    }
    {WHITE_SPACE_CHAR}+                                { return TokenType.WHITE_SPACE; }
}
<IN_OTHER> {
    {AS}                                               { yybegin (IN_AS); return CacheObjectScriptClsTypes.AS;}
        {EXTENDS}                                      { yybegin (IN_EXTENDS); return CacheObjectScriptClsTypes.EXTENDS;}
    <IN_AS>{
        {CLASSNAME}                                    { yybegin (IN_OTHER); return CacheObjectScriptClsTypes.CLASSNAME; }
    }
    <IN_EXTENDS>{
        {LPARENTHESIS}                                 { yybegin (IN_CLASS_LIST_EXT);return CacheObjectScriptClsTypes.LPAR;}
        <IN_CLASS_LIST_EXT>{
            {CLASSNAME}                                { return CacheObjectScriptClsTypes.CLASSNAME;}
            {COMMA}                                    { return CacheObjectScriptClsTypes.COMMA;}
            {RPARENTHESIS}                             { yybegin (IN_OTHER);return CacheObjectScriptClsTypes.RPAR; }
        }
    }
    {LBRACKET}                                         { yybegin (IN_KEYWORDS); return CacheObjectScriptClsTypes.LBRACKET;}
    <IN_KEYWORDS>{
        {STRING}                                       { return CacheObjectScriptClsTypes.KEYWORD;}
        {IDENTIFIER}                                   { return CacheObjectScriptClsTypes.KEYWORD;}
        {CLASSNAME}                                    { return CacheObjectScriptClsTypes.KEYWORD;}
        {COMMA}                                        { return CacheObjectScriptClsTypes.COMMA;}
        {EQ}                                           { return CacheObjectScriptClsTypes.EQ;}
        {LPARENTHESIS}                                 { yybegin (IN_ARGS);return CacheObjectScriptClsTypes.LPAR;}
        <IN_ARGS>{
            {NUM}                                      { return CacheObjectScriptClsTypes.KEYWORD;}
            {STRING}                                   { return CacheObjectScriptClsTypes.KEYWORD;}
            {IDENTIFIER}                               { return CacheObjectScriptClsTypes.KEYWORD;}
            {CLASSNAME}                                { return CacheObjectScriptClsTypes.KEYWORD;}
            {COMMA}                                    { return CacheObjectScriptClsTypes.COMMA;}
            {RPARENTHESIS}                             { yybegin (IN_KEYWORDS);return CacheObjectScriptClsTypes.RPAR;}
        }
        {RBRACKET}                                     {yybegin (IN_OTHER); return CacheObjectScriptClsTypes.RBRACKET;}
    }
    {WHITE_SPACE_CHAR}+                                { return TokenType.WHITE_SPACE; }
    {LBRASE}                                           { yybegin (IN_CLASSENTRY);return CacheObjectScriptClsTypes.LBRACE;}
}
<IN_CLASSENTRY> {
    {FKEY}                                             { yybegin (IN_FKEY); return CacheObjectScriptClsTypes.FKEY_KEYWORD; }
    {INDEX}                                             { yybegin (IN_INDEX); return CacheObjectScriptClsTypes.INDEXWORD; }
    //foreign key def
    <IN_FKEY> {
        {IDENTIFIER}                                   { return CacheObjectScriptClsTypes.FKEY_NAME;}
        {LPARENTHESIS}                                 { yybegin (IN_FKEY_PROPS); return CacheObjectScriptClsTypes.LPAR; }
        <IN_FKEY_PROPS>{
            {IDENTIFIER}                               { return CacheObjectScriptClsTypes.FPROP;}
            {COMMA}                                    { return CacheObjectScriptClsTypes.COMMA;}
            {RPARENTHESIS}                             { yybegin (IN_FKEY_OTHER); return CacheObjectScriptClsTypes.RPAR; }
        }
        <IN_FKEY_OTHER>{
            {REF}                                          { yybegin (IN_FKEY_REF); return CacheObjectScriptClsTypes.FREF_WORD; }
            <IN_FKEY_REF>{
                {CLASSNAME}                                { return CacheObjectScriptClsTypes.FREF_CLASS; }
                {LPARENTHESIS}                             { yybegin (IN_FKEY_PROPS); return CacheObjectScriptClsTypes.LPAR; }
                <IN_FKEY_PROPS>{
                    {IDENTIFIER}                           { return CacheObjectScriptClsTypes.FPROP;}
                    {COMMA}                                { return CacheObjectScriptClsTypes.COMMA;}
                    {RPARENTHESIS}                         { yybegin (IN_FKEY_OTHER); return CacheObjectScriptClsTypes.RPAR; }
                }
                {SEMICOLON}                                { yybegin (IN_CLASSENTRY); return CacheObjectScriptClsTypes.SEMICOLON;}
                {LBRACKET}                                 { yybegin (IN_FKEYWORDS); return CacheObjectScriptClsTypes.LBRACKET;}
                <IN_FKEYWORDS>{
                    {STRING}                               { return CacheObjectScriptClsTypes.FKEYWORD;}
                    {IDENTIFIER}                           { return CacheObjectScriptClsTypes.FKEYWORD;}
                    {CLASSNAME}                            { return CacheObjectScriptClsTypes.FKEYWORD;}
                    {COMMA}                                { return CacheObjectScriptClsTypes.COMMA;}
                    {EQ}                                   { return CacheObjectScriptClsTypes.EQ;}
                    {LPARENTHESIS}                         { yybegin (IN_FARGS);return CacheObjectScriptClsTypes.LPAR;}
                    <IN_FARGS>{
                        {NUM}                              { return CacheObjectScriptClsTypes.FKEYWORD;}
                        {STRING}                           { return CacheObjectScriptClsTypes.FKEYWORD;}
                        {IDENTIFIER}                       { return CacheObjectScriptClsTypes.FKEYWORD;}
                        {CLASSNAME}                        { return CacheObjectScriptClsTypes.FKEYWORD;}
                        {COMMA}                            { return CacheObjectScriptClsTypes.COMMA;}
                        {RPARENTHESIS}                     { yybegin (IN_FKEYWORDS);return CacheObjectScriptClsTypes.RPAR;}
                    }
                    {RBRACKET}                             { yybegin (IN_FKEY); return CacheObjectScriptClsTypes.RBRACKET;}
                }
            }
        }
    }
    //index def
        <IN_INDEX> {
            {IDENTIFIER}                                   { yybegin (IN_INDEX_OTHER); return CacheObjectScriptClsTypes.INDEXNAME;}
            <IN_INDEX_OTHER>{
                {ON}                                          { yybegin (IN_INDEX_ON); return CacheObjectScriptClsTypes.ON; }
                <IN_INDEX_ON>{
                    {IDENTIFIER}                               { return CacheObjectScriptClsTypes.INDEXPROP; }
                    {LPARENTHESIS}                             { yybegin (IN_INDEX_PROPS); return CacheObjectScriptClsTypes.LPAR; }
                    <IN_INDEX_PROPS>{
                        {IDENTIFIER}                           { return CacheObjectScriptClsTypes.INDEXPROP;}
                        {COMMA}                                { return CacheObjectScriptClsTypes.COMMA;}
                        {RPARENTHESIS}                         { yybegin (IN_INDEX_OTHER); return CacheObjectScriptClsTypes.RPAR; }
                    }
                    {SEMICOLON}                                { yybegin (IN_CLASSENTRY); return CacheObjectScriptClsTypes.SEMICOLON;}
                    {LBRASE}                                   { yybegin (IN_INDEX_ENTRY);return CacheObjectScriptClsTypes.LBRACE;}
                    <IN_INDEX_ENTRY>{
                        {RBRASE}                               { yybegin (IN_CLASSENTRY);return CacheObjectScriptClsTypes.RBRACE;}
                    }
                    {LBRACKET}                                 { yybegin (IN_IKEYWORDS); return CacheObjectScriptClsTypes.LBRACKET;}
                    <IN_IKEYWORDS>{
                        {STRING}                               { return CacheObjectScriptClsTypes.IKEYWORD;}
                        {IDENTIFIER}                           { return CacheObjectScriptClsTypes.IKEYWORD;}
                        {CLASSNAME}                            { return CacheObjectScriptClsTypes.IKEYWORD;}
                        {COMMA}                                { return CacheObjectScriptClsTypes.COMMA;}
                        {EQ}                                   { return CacheObjectScriptClsTypes.EQ;}
                        {LPARENTHESIS}                         { yybegin (IN_IARGS);return CacheObjectScriptClsTypes.LPAR;}
                        <IN_IARGS>{
                            {NUM}                              { return CacheObjectScriptClsTypes.IKEYWORD;}
                            {STRING}                           { return CacheObjectScriptClsTypes.IKEYWORD;}
                            {IDENTIFIER}                       { return CacheObjectScriptClsTypes.IKEYWORD;}
                            {CLASSNAME}                        { return CacheObjectScriptClsTypes.IKEYWORD;}
                            {COMMA}                            { return CacheObjectScriptClsTypes.COMMA;}
                            {RPARENTHESIS}                     { yybegin (IN_IKEYWORDS);return CacheObjectScriptClsTypes.RPAR;}
                        }
                        {RBRACKET}                             { yybegin (IN_INDEX); return CacheObjectScriptClsTypes.RBRACKET;}
                    }
                }
            }
        }
    {RBRASE}                                           { yybegin (YYINITIAL); return CacheObjectScriptClsTypes.RBRACE;}
}

{DescriptionComment}                                   { return CacheObjectScriptClsTypes.DESCRIPTION;}
{COMMENT}                                              { return CacheObjectScriptClsTypes.COMMENT;}

{CRLF}                                                 { return TokenType.WHITE_SPACE; }
{WHITE_SPACE_CHAR}+                                    { return TokenType.WHITE_SPACE; }
.                                                      { return TokenType.BAD_CHARACTER; }
