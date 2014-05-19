package by.vsu.cacheplugin.lang.cls;

import by.vsu.cacheplugin.lang.cls.psi.CacheObjectScriptClsTypes;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Reader;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class CacheObjectScriptClsSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey ARG_CLASS = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.BOLD));
    public static final TextAttributesKey ARG_PARAM = createTextAttributesKey("Mac_STRING", new TextAttributes(Color.darkGray, null, null, null, Font.ITALIC));
    public static final TextAttributesKey ARG_PARAMS = createTextAttributesKey("Mac_COMMAND", new TextAttributes(Color.BLUE, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey CLASSDEF = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey CLASS_ENTRY = createTextAttributesKey("Mac_COMMAND", new TextAttributes(Color.BLUE, null, null, null, Font.BOLD));
    public static final TextAttributesKey CLASS_LIST = createTextAttributesKey("Mac_STRING", new TextAttributes(Color.darkGray, null, null, null, Font.ITALIC));
    public static final TextAttributesKey CLASS_NAME_BLOCK = createTextAttributesKey("Mac_COMMAND", new TextAttributes(Color.BLUE, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey DEF_BLOCK = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey ELEM = createTextAttributesKey("Mac_COMMAND", new TextAttributes(Color.BLUE, null, null, null, Font.BOLD));
    public static final TextAttributesKey EXTEND_BLOCK = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.ITALIC));
    public static final TextAttributesKey FILE_LIST = createTextAttributesKey("Mac_COMMAND", new TextAttributes(Color.BLUE, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey FKEY = createTextAttributesKey("Mac_COMMAND", new TextAttributes(Color.BLUE, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey FKEYWORD_ITEM = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.BOLD));
    public static final TextAttributesKey FKEY_KEYWORD_BLOCK = createTextAttributesKey("Mac_LOCAL", new TextAttributes(Color.ORANGE.darker(), null, null, null, Font.ITALIC));
    public static final TextAttributesKey FPROPS = createTextAttributesKey("Mac_LOCAL", new TextAttributes(Color.ORANGE.darker(), null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey IKEYWORD_ITEM = createTextAttributesKey("Mac_STRING", new TextAttributes(Color.darkGray, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey IMPORT_BLOCK = createTextAttributesKey("Mac_LOCAL", new TextAttributes(Color.ORANGE.darker(), null, null, null, Font.BOLD));
    public static final TextAttributesKey INCLUDE_BLOCK = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.ITALIC));
    public static final TextAttributesKey INDEX = createTextAttributesKey("Mac_LOCAL", new TextAttributes(Color.ORANGE.darker(), null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey INDEX_BODY = createTextAttributesKey("Mac_LOCAL", new TextAttributes(Color.ORANGE.darker(), null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey INDEX_KEYWORD_BLOCK = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.BOLD));
    public static final TextAttributesKey INDEX_PROPS = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.ITALIC));
    public static final TextAttributesKey KEYWORDS_BLOCK = createTextAttributesKey("Mac_LOCAL", new TextAttributes(Color.ORANGE.darker(), null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey KEYWORD_ITEM = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey METHOD = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.BOLD));
    public static final TextAttributesKey METHOD_ARGS = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.ITALIC));
    public static final TextAttributesKey METHOD_BODY = createTextAttributesKey("Mac_SYS", new TextAttributes(Color.magenta, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey METHOD_KEYWORDS_BLOCK = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey MKEYWORD_ITEM = createTextAttributesKey("Mac_SYS", new TextAttributes(Color.magenta, null, null, null, Font.BOLD));
    public static final TextAttributesKey RET_PARAM = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.ITALIC));
    public static final TextAttributesKey RET_PARAMS = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey RET_VAL = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey ARGDEFAULT = createTextAttributesKey("Mac_STRING", new TextAttributes(Color.darkGray, null, null, null, Font.BOLD));
    public static final TextAttributesKey ARGKEYWORD = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.ITALIC));
    public static final TextAttributesKey ARGMODIFIER = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey ARGNAME = createTextAttributesKey("Mac_SYS", new TextAttributes(Color.magenta, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey AS = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.BOLD));
    public static final TextAttributesKey CLASS = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.ITALIC));
    public static final TextAttributesKey CLASSNAME = createTextAttributesKey("Mac_SYS", new TextAttributes(Color.magenta, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey COMMA = createTextAttributesKey("Mac_LABEL", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.BOLD));
    public static final TextAttributesKey DESCRIPTION = createTextAttributesKey("Mac_GLOBALSYS", new TextAttributes(Color.PINK, null, null, null, Font.ITALIC));
    public static final TextAttributesKey EQ = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey EXTENDS = createTextAttributesKey("Mac_GLOBALSYS", new TextAttributes(Color.PINK, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey FILENAME = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.BOLD));
    public static final TextAttributesKey FKEYWORD = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.ITALIC));
    public static final TextAttributesKey FKEY_KEYWORD = createTextAttributesKey("Mac_GLOBALSYS", new TextAttributes(Color.PINK, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey FKEY_NAME = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey FPROP = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.BOLD));
    public static final TextAttributesKey FREF_CLASS = createTextAttributesKey("Mac_STRING", new TextAttributes(Color.darkGray, null, null, null, Font.ITALIC));
    public static final TextAttributesKey FREF_WORD = createTextAttributesKey("Mac_GLOBALSYS", new TextAttributes(Color.PINK, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey IKEYWORD = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey IMPORT = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.BOLD));
    public static final TextAttributesKey INCLUDE = createTextAttributesKey("Mac_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey INDEXNAME = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.ITALIC));
    public static final TextAttributesKey INDEXPROP = createTextAttributesKey("Mac_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey INDEXWORD = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey LBRACE = createTextAttributesKey("Mac_LABEL", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey LBRACKET = createTextAttributesKey("Mac_GLOBAL", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey LPAR = createTextAttributesKey("Mac_LABEL", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey METHODCODE = createTextAttributesKey("Mac_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey METHODNAME = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.BOLD));
    public static final TextAttributesKey METHODWORD = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.ITALIC));
    public static final TextAttributesKey MKEYWORD = createTextAttributesKey("Mac_OPERATION", new TextAttributes(Color.BLUE.darker(), null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey ON = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey PARAM = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.BOLD));
    public static final TextAttributesKey PROJECTION = createTextAttributesKey("Mac_OPERATION", new TextAttributes(Color.BLUE.darker(), null, null, null, Font.ITALIC));
    public static final TextAttributesKey PROPERTY = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey QUERY = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.TRUETYPE_FONT));
    public static final TextAttributesKey RBRACE = createTextAttributesKey("Mac_COMMENT", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey RBRACKET = createTextAttributesKey("Mac_OPERATION", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey RETKEYWORD = createTextAttributesKey("Mac_COMMENT", new TextAttributes(Color.GRAY, null, null, null, Font.BOLD));
    public static final TextAttributesKey RPAR = createTextAttributesKey("Mac_LABEL", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey SEMICOLON = createTextAttributesKey("Mac_OPERATION", new TextAttributes(Color.BLUE.darker(), null, null, null, Font.ITALIC));
    public static final TextAttributesKey TRIGGER = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.ROMAN_BASELINE));
    public static final TextAttributesKey XDATA = createTextAttributesKey("Mac_COMMENT", new TextAttributes(Color.GRAY, null, null, null, Font.TRUETYPE_FONT));
    static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("Mac_BAD_CHARACTER",
            new TextAttributes(Color.RED, null, null, null, Font.BOLD));

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] ARG_CLASS_KEYS = new TextAttributesKey[]{ARG_CLASS};
    private static final TextAttributesKey[] ARG_PARAM_KEYS = new TextAttributesKey[]{ARG_PARAM};
    private static final TextAttributesKey[] ARG_PARAMS_KEYS = new TextAttributesKey[]{ARG_PARAMS};
    private static final TextAttributesKey[] CLASSDEF_KEYS = new TextAttributesKey[]{CLASSDEF};
    private static final TextAttributesKey[] CLASS_ENTRY_KEYS = new TextAttributesKey[]{CLASS_ENTRY};
    private static final TextAttributesKey[] CLASS_LIST_KEYS = new TextAttributesKey[]{CLASS_LIST};
    private static final TextAttributesKey[] CLASS_NAME_BLOCK_KEYS = new TextAttributesKey[]{CLASS_NAME_BLOCK};
    private static final TextAttributesKey[] DEF_BLOCK_KEYS = new TextAttributesKey[]{DEF_BLOCK};
    private static final TextAttributesKey[] ELEM_KEYS = new TextAttributesKey[]{ELEM};
    private static final TextAttributesKey[] EXTEND_BLOCK_KEYS = new TextAttributesKey[]{EXTEND_BLOCK};
    private static final TextAttributesKey[] FILE_LIST_KEYS = new TextAttributesKey[]{FILE_LIST};
    private static final TextAttributesKey[] FKEY_KEYS = new TextAttributesKey[]{FKEY};
    private static final TextAttributesKey[] FKEYWORD_ITEM_KEYS = new TextAttributesKey[]{FKEYWORD_ITEM};
    private static final TextAttributesKey[] FKEY_KEYWORD_BLOCK_KEYS = new TextAttributesKey[]{FKEY_KEYWORD_BLOCK};
    private static final TextAttributesKey[] FPROPS_KEYS = new TextAttributesKey[]{FPROPS};
    private static final TextAttributesKey[] IKEYWORD_ITEM_KEYS = new TextAttributesKey[]{IKEYWORD_ITEM};
    private static final TextAttributesKey[] IMPORT_BLOCK_KEYS = new TextAttributesKey[]{IMPORT_BLOCK};
    private static final TextAttributesKey[] INCLUDE_BLOCK_KEYS = new TextAttributesKey[]{INCLUDE_BLOCK};
    private static final TextAttributesKey[] INDEX_KEYS = new TextAttributesKey[]{INDEX};
    private static final TextAttributesKey[] INDEX_BODY_KEYS = new TextAttributesKey[]{INDEX_BODY};
    private static final TextAttributesKey[] INDEX_KEYWORD_BLOCK_KEYS = new TextAttributesKey[]{INDEX_KEYWORD_BLOCK};
    private static final TextAttributesKey[] INDEX_PROPS_KEYS = new TextAttributesKey[]{INDEX_PROPS};
    private static final TextAttributesKey[] KEYWORDS_BLOCK_KEYS = new TextAttributesKey[]{KEYWORDS_BLOCK};
    private static final TextAttributesKey[] KEYWORD_ITEM_KEYS = new TextAttributesKey[]{KEYWORD_ITEM};
    private static final TextAttributesKey[] METHOD_KEYS = new TextAttributesKey[]{METHOD};
    private static final TextAttributesKey[] METHOD_ARGS_KEYS = new TextAttributesKey[]{METHOD_ARGS};
    private static final TextAttributesKey[] METHOD_BODY_KEYS = new TextAttributesKey[]{METHOD_BODY};
    private static final TextAttributesKey[] METHOD_KEYWORDS_BLOCK_KEYS = new TextAttributesKey[]{METHOD_KEYWORDS_BLOCK};
    private static final TextAttributesKey[] MKEYWORD_ITEM_KEYS = new TextAttributesKey[]{MKEYWORD_ITEM};
    private static final TextAttributesKey[] RET_PARAM_KEYS = new TextAttributesKey[]{RET_PARAM};
    private static final TextAttributesKey[] RET_PARAMS_KEYS = new TextAttributesKey[]{RET_PARAMS};
    private static final TextAttributesKey[] RET_VAL_KEYS = new TextAttributesKey[]{RET_VAL};
    private static final TextAttributesKey[] ARGDEFAULT_KEYS = new TextAttributesKey[]{ARGDEFAULT};
    private static final TextAttributesKey[] ARGKEYWORD_KEYS = new TextAttributesKey[]{ARGKEYWORD};
    private static final TextAttributesKey[] ARGMODIFIER_KEYS = new TextAttributesKey[]{ARGMODIFIER};
    private static final TextAttributesKey[] ARGNAME_KEYS = new TextAttributesKey[]{ARGNAME};
    private static final TextAttributesKey[] AS_KEYS = new TextAttributesKey[]{AS};
    private static final TextAttributesKey[] CLASS_KEYS = new TextAttributesKey[]{CLASS};
    private static final TextAttributesKey[] CLASSNAME_KEYS = new TextAttributesKey[]{CLASSNAME};
    private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{COMMA};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] DESCRIPTION_KEYS = new TextAttributesKey[]{DESCRIPTION};
    private static final TextAttributesKey[] EQ_KEYS = new TextAttributesKey[]{EQ};
    private static final TextAttributesKey[] EXTENDS_KEYS = new TextAttributesKey[]{EXTENDS};
    private static final TextAttributesKey[] FILENAME_KEYS = new TextAttributesKey[]{FILENAME};
    private static final TextAttributesKey[] FKEYWORD_KEYS = new TextAttributesKey[]{FKEYWORD};
    private static final TextAttributesKey[] FKEY_KEYWORD_KEYS = new TextAttributesKey[]{FKEY_KEYWORD};
    private static final TextAttributesKey[] FKEY_NAME_KEYS = new TextAttributesKey[]{FKEY_NAME};
    private static final TextAttributesKey[] FPROP_KEYS = new TextAttributesKey[]{FPROP};
    private static final TextAttributesKey[] FREF_CLASS_KEYS = new TextAttributesKey[]{FREF_CLASS};
    private static final TextAttributesKey[] FREF_WORD_KEYS = new TextAttributesKey[]{FREF_WORD};
    private static final TextAttributesKey[] IKEYWORD_KEYS = new TextAttributesKey[]{IKEYWORD};
    private static final TextAttributesKey[] IMPORT_KEYS = new TextAttributesKey[]{IMPORT};
    private static final TextAttributesKey[] INCLUDE_KEYS = new TextAttributesKey[]{INCLUDE};
    private static final TextAttributesKey[] INDEXNAME_KEYS = new TextAttributesKey[]{INDEXNAME};
    private static final TextAttributesKey[] INDEXPROP_KEYS = new TextAttributesKey[]{INDEXPROP};
    private static final TextAttributesKey[] INDEXWORD_KEYS = new TextAttributesKey[]{INDEXWORD};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] LBRACE_KEYS = new TextAttributesKey[]{LBRACE};
    private static final TextAttributesKey[] LBRACKET_KEYS = new TextAttributesKey[]{LBRACKET};
    private static final TextAttributesKey[] LPAR_KEYS = new TextAttributesKey[]{LPAR};
    private static final TextAttributesKey[] METHODCODE_KEYS = new TextAttributesKey[]{METHODCODE};
    private static final TextAttributesKey[] METHODNAME_KEYS = new TextAttributesKey[]{METHODNAME};
    private static final TextAttributesKey[] METHODWORD_KEYS = new TextAttributesKey[]{METHODWORD};
    private static final TextAttributesKey[] MKEYWORD_KEYS = new TextAttributesKey[]{MKEYWORD};
    private static final TextAttributesKey[] ON_KEYS = new TextAttributesKey[]{ON};
    private static final TextAttributesKey[] PARAM_KEYS = new TextAttributesKey[]{PARAM};
    private static final TextAttributesKey[] PROJECTION_KEYS = new TextAttributesKey[]{PROJECTION};
    private static final TextAttributesKey[] PROPERTY_KEYS = new TextAttributesKey[]{PROPERTY};
    private static final TextAttributesKey[] QUERY_KEYS = new TextAttributesKey[]{QUERY};
    private static final TextAttributesKey[] RBRACE_KEYS = new TextAttributesKey[]{RBRACE};
    private static final TextAttributesKey[] RBRACKET_KEYS = new TextAttributesKey[]{RBRACKET};
    private static final TextAttributesKey[] RETKEYWORD_KEYS = new TextAttributesKey[]{RETKEYWORD};
    private static final TextAttributesKey[] RPAR_KEYS = new TextAttributesKey[]{RPAR};
    private static final TextAttributesKey[] SEMICOLON_KEYS = new TextAttributesKey[]{SEMICOLON};
    private static final TextAttributesKey[] TRIGGER_KEYS = new TextAttributesKey[]{TRIGGER};
    private static final TextAttributesKey[] XDATA_KEYS = new TextAttributesKey[]{XDATA};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];


    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FlexAdapter(new CacheObjectScriptClsLexer((Reader) null));
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.ARG_CLASS)) {
            return ARG_CLASS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.ARG_PARAM)) {
            return ARG_PARAM_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.ARG_PARAMS)) {
            return ARG_PARAMS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.CLASSDEF)) {
            return CLASSDEF_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.CLASS_ENTRY)) {
            return CLASS_ENTRY_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.CLASS_LIST)) {
            return CLASS_LIST_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.CLASS_NAME_BLOCK)) {
            return CLASS_NAME_BLOCK_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.DEF_BLOCK)) {
            return DEF_BLOCK_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.ELEM)) {
            return ELEM_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.EXTEND_BLOCK)) {
            return EXTEND_BLOCK_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FILE_LIST)) {
            return FILE_LIST_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FKEY)) {
            return FKEY_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FKEYWORD_ITEM)) {
            return FKEYWORD_ITEM_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FKEY_KEYWORD_BLOCK)) {
            return FKEY_KEYWORD_BLOCK_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FPROPS)) {
            return FPROPS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.IKEYWORD_ITEM)) {
            return IKEYWORD_ITEM_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.IMPORT_BLOCK)) {
            return IMPORT_BLOCK_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.INCLUDE_BLOCK)) {
            return INCLUDE_BLOCK_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.INDEX)) {
            return INDEX_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.INDEX_BODY)) {
            return INDEX_BODY_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.INDEX_KEYWORD_BLOCK)) {
            return INDEX_KEYWORD_BLOCK_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.INDEX_PROPS)) {
            return INDEX_PROPS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.KEYWORDS_BLOCK)) {
            return KEYWORDS_BLOCK_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.KEYWORD_ITEM)) {
            return KEYWORD_ITEM_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.METHOD)) {
            return METHOD_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.METHOD_ARGS)) {
            return METHOD_ARGS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.METHOD_BODY)) {
            return METHOD_BODY_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.METHOD_KEYWORDS_BLOCK)) {
            return METHOD_KEYWORDS_BLOCK_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.MKEYWORD_ITEM)) {
            return MKEYWORD_ITEM_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.RET_PARAM)) {
            return RET_PARAM_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.RET_PARAMS)) {
            return RET_PARAMS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.RET_VAL)) {
            return RET_VAL_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.ARGDEFAULT)) {
            return ARGDEFAULT_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.ARGKEYWORD)) {
            return ARGKEYWORD_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.ARGMODIFIER)) {
            return ARGMODIFIER_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.ARGNAME)) {
            return ARGNAME_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.AS)) {
            return AS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.CLASS)) {
            return CLASS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.CLASSNAME)) {
            return CLASSNAME_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.COMMA)) {
            return COMMA_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.DESCRIPTION)) {
            return DESCRIPTION_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.EQ)) {
            return EQ_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.EXTENDS)) {
            return EXTENDS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FILENAME)) {
            return FILENAME_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FKEYWORD)) {
            return FKEYWORD_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FKEY_KEYWORD)) {
            return FKEY_KEYWORD_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FKEY_NAME)) {
            return FKEY_NAME_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FPROP)) {
            return FPROP_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FREF_CLASS)) {
            return FREF_CLASS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.FREF_WORD)) {
            return FREF_WORD_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.IKEYWORD)) {
            return IKEYWORD_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.IMPORT)) {
            return IMPORT_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.INCLUDE)) {
            return INCLUDE_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.INDEXNAME)) {
            return INDEXNAME_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.INDEXPROP)) {
            return INDEXPROP_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.INDEXWORD)) {
            return INDEXWORD_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.KEYWORD)) {
            return KEYWORD_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.LBRACE)) {
            return LBRACE_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.LBRACKET)) {
            return LBRACKET_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.LPAR)) {
            return LPAR_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.METHODCODE)) {
            return METHODCODE_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.METHODNAME)) {
            return METHODNAME_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.METHODWORD)) {
            return METHODWORD_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.MKEYWORD)) {
            return MKEYWORD_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.ON)) {
            return ON_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.PARAM)) {
            return PARAM_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.PROJECTION)) {
            return PROJECTION_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.PROPERTY)) {
            return PROPERTY_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.QUERY)) {
            return QUERY_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.RBRACE)) {
            return RBRACE_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.RBRACKET)) {
            return RBRACKET_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.RETKEYWORD)) {
            return RETKEYWORD_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.RPAR)) {
            return RPAR_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.SEMICOLON)) {
            return SEMICOLON_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.TRIGGER)) {
            return TRIGGER_KEYS;
        } else if (tokenType.equals(CacheObjectScriptClsTypes.XDATA)) {
            return XDATA_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}

