package by.vsu.cacheplugin.lang.cls;

import by.vsu.cacheplugin.lang.common.CacheObjectScriptIcons;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class CacheObjectScriptClsColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("ARG_CLASS", CacheObjectScriptClsSyntaxHighlighter.ARG_CLASS),
            new AttributesDescriptor("ARG_PARAM", CacheObjectScriptClsSyntaxHighlighter.ARG_PARAM),
            new AttributesDescriptor("ARG_PARAMS", CacheObjectScriptClsSyntaxHighlighter.ARG_PARAMS),
            new AttributesDescriptor("CLASSDEF", CacheObjectScriptClsSyntaxHighlighter.CLASSDEF),
            new AttributesDescriptor("CLASS_ENTRY", CacheObjectScriptClsSyntaxHighlighter.CLASS_ENTRY),
            new AttributesDescriptor("CLASS_LIST", CacheObjectScriptClsSyntaxHighlighter.CLASS_LIST),
            new AttributesDescriptor("CLASS_NAME_BLOCK", CacheObjectScriptClsSyntaxHighlighter.CLASS_NAME_BLOCK),
            new AttributesDescriptor("DEF_BLOCK", CacheObjectScriptClsSyntaxHighlighter.DEF_BLOCK),
            new AttributesDescriptor("ELEM", CacheObjectScriptClsSyntaxHighlighter.ELEM),
            new AttributesDescriptor("EXTEND_BLOCK", CacheObjectScriptClsSyntaxHighlighter.EXTEND_BLOCK),
            new AttributesDescriptor("FILE_LIST", CacheObjectScriptClsSyntaxHighlighter.FILE_LIST),
            new AttributesDescriptor("FKEY", CacheObjectScriptClsSyntaxHighlighter.FKEY),
            new AttributesDescriptor("FKEYWORD_ITEM", CacheObjectScriptClsSyntaxHighlighter.FKEYWORD_ITEM),
            new AttributesDescriptor("FKEY_KEYWORD_BLOCK", CacheObjectScriptClsSyntaxHighlighter.FKEY_KEYWORD_BLOCK),
            new AttributesDescriptor("FPROPS", CacheObjectScriptClsSyntaxHighlighter.FPROPS),
            new AttributesDescriptor("IKEYWORD_ITEM", CacheObjectScriptClsSyntaxHighlighter.IKEYWORD_ITEM),
            new AttributesDescriptor("IMPORT_BLOCK", CacheObjectScriptClsSyntaxHighlighter.IMPORT_BLOCK),
            new AttributesDescriptor("INCLUDE_BLOCK", CacheObjectScriptClsSyntaxHighlighter.INCLUDE_BLOCK),
            new AttributesDescriptor("INDEX", CacheObjectScriptClsSyntaxHighlighter.INDEX),
            new AttributesDescriptor("INDEX_BODY", CacheObjectScriptClsSyntaxHighlighter.INDEX_BODY),
            new AttributesDescriptor("INDEX_KEYWORD_BLOCK", CacheObjectScriptClsSyntaxHighlighter.INDEX_KEYWORD_BLOCK),
            new AttributesDescriptor("INDEX_PROPS", CacheObjectScriptClsSyntaxHighlighter.INDEX_PROPS),
            new AttributesDescriptor("KEYWORDS_BLOCK", CacheObjectScriptClsSyntaxHighlighter.KEYWORDS_BLOCK),
            new AttributesDescriptor("KEYWORD_ITEM", CacheObjectScriptClsSyntaxHighlighter.KEYWORD_ITEM),
            new AttributesDescriptor("METHOD", CacheObjectScriptClsSyntaxHighlighter.METHOD),
            new AttributesDescriptor("METHOD_ARGS", CacheObjectScriptClsSyntaxHighlighter.METHOD_ARGS),
            new AttributesDescriptor("METHOD_BODY", CacheObjectScriptClsSyntaxHighlighter.METHOD_BODY),
            new AttributesDescriptor("METHOD_KEYWORDS_BLOCK", CacheObjectScriptClsSyntaxHighlighter.METHOD_KEYWORDS_BLOCK),
            new AttributesDescriptor("MKEYWORD_ITEM", CacheObjectScriptClsSyntaxHighlighter.MKEYWORD_ITEM),
            new AttributesDescriptor("RET_PARAM", CacheObjectScriptClsSyntaxHighlighter.RET_PARAM),
            new AttributesDescriptor("RET_PARAMS", CacheObjectScriptClsSyntaxHighlighter.RET_PARAMS),
            new AttributesDescriptor("RET_VAL", CacheObjectScriptClsSyntaxHighlighter.RET_VAL),
            new AttributesDescriptor("ARGDEFAULT", CacheObjectScriptClsSyntaxHighlighter.ARGDEFAULT),
            new AttributesDescriptor("ARGKEYWORD", CacheObjectScriptClsSyntaxHighlighter.ARGKEYWORD),
            new AttributesDescriptor("ARGMODIFIER", CacheObjectScriptClsSyntaxHighlighter.ARGMODIFIER),
            new AttributesDescriptor("ARGNAME", CacheObjectScriptClsSyntaxHighlighter.ARGNAME),
            new AttributesDescriptor("AS", CacheObjectScriptClsSyntaxHighlighter.AS),
            new AttributesDescriptor("CLASS", CacheObjectScriptClsSyntaxHighlighter.CLASS),
            new AttributesDescriptor("CLASSNAME", CacheObjectScriptClsSyntaxHighlighter.CLASSNAME),
            new AttributesDescriptor("COMMA", CacheObjectScriptClsSyntaxHighlighter.COMMA),
            new AttributesDescriptor("COMMENT", CacheObjectScriptClsSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("DESCRIPTION", CacheObjectScriptClsSyntaxHighlighter.DESCRIPTION),
            new AttributesDescriptor("EQ", CacheObjectScriptClsSyntaxHighlighter.EQ),
            new AttributesDescriptor("EXTENDS", CacheObjectScriptClsSyntaxHighlighter.EXTENDS),
            new AttributesDescriptor("FILENAME", CacheObjectScriptClsSyntaxHighlighter.FILENAME),
            new AttributesDescriptor("FKEYWORD", CacheObjectScriptClsSyntaxHighlighter.FKEYWORD),
            new AttributesDescriptor("FKEY_KEYWORD", CacheObjectScriptClsSyntaxHighlighter.FKEY_KEYWORD),
            new AttributesDescriptor("FKEY_NAME", CacheObjectScriptClsSyntaxHighlighter.FKEY_NAME),
            new AttributesDescriptor("FPROP", CacheObjectScriptClsSyntaxHighlighter.FPROP),
            new AttributesDescriptor("FREF_CLASS", CacheObjectScriptClsSyntaxHighlighter.FREF_CLASS),
            new AttributesDescriptor("FREF_WORD", CacheObjectScriptClsSyntaxHighlighter.FREF_WORD),
            new AttributesDescriptor("IKEYWORD", CacheObjectScriptClsSyntaxHighlighter.IKEYWORD),
            new AttributesDescriptor("IMPORT", CacheObjectScriptClsSyntaxHighlighter.IMPORT),
            new AttributesDescriptor("INCLUDE", CacheObjectScriptClsSyntaxHighlighter.INCLUDE),
            new AttributesDescriptor("INDEXNAME", CacheObjectScriptClsSyntaxHighlighter.INDEXNAME),
            new AttributesDescriptor("INDEXPROP", CacheObjectScriptClsSyntaxHighlighter.INDEXPROP),
            new AttributesDescriptor("INDEXWORD", CacheObjectScriptClsSyntaxHighlighter.INDEXWORD),
            new AttributesDescriptor("KEYWORD", CacheObjectScriptClsSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("LBRACE", CacheObjectScriptClsSyntaxHighlighter.LBRACE),
            new AttributesDescriptor("LBRACKET", CacheObjectScriptClsSyntaxHighlighter.LBRACKET),
            new AttributesDescriptor("LPAR", CacheObjectScriptClsSyntaxHighlighter.LPAR),
            new AttributesDescriptor("METHODCODE", CacheObjectScriptClsSyntaxHighlighter.METHODCODE),
            new AttributesDescriptor("METHODNAME", CacheObjectScriptClsSyntaxHighlighter.METHODNAME),
            new AttributesDescriptor("METHODWORD", CacheObjectScriptClsSyntaxHighlighter.METHODWORD),
            new AttributesDescriptor("MKEYWORD", CacheObjectScriptClsSyntaxHighlighter.MKEYWORD),
            new AttributesDescriptor("ON", CacheObjectScriptClsSyntaxHighlighter.ON),
            new AttributesDescriptor("PARAM", CacheObjectScriptClsSyntaxHighlighter.PARAM),
            new AttributesDescriptor("PROJECTION", CacheObjectScriptClsSyntaxHighlighter.PROJECTION),
            new AttributesDescriptor("PROPERTY", CacheObjectScriptClsSyntaxHighlighter.PROPERTY),
            new AttributesDescriptor("QUERY", CacheObjectScriptClsSyntaxHighlighter.QUERY),
            new AttributesDescriptor("RBRACE", CacheObjectScriptClsSyntaxHighlighter.RBRACE),
            new AttributesDescriptor("RBRACKET", CacheObjectScriptClsSyntaxHighlighter.RBRACKET),
            new AttributesDescriptor("RETKEYWORD", CacheObjectScriptClsSyntaxHighlighter.RETKEYWORD),
            new AttributesDescriptor("RPAR", CacheObjectScriptClsSyntaxHighlighter.RPAR),
            new AttributesDescriptor("SEMICOLON", CacheObjectScriptClsSyntaxHighlighter.SEMICOLON),
            new AttributesDescriptor("TRIGGER", CacheObjectScriptClsSyntaxHighlighter.TRIGGER),
            new AttributesDescriptor("XDATA", CacheObjectScriptClsSyntaxHighlighter.XDATA),
            new AttributesDescriptor("Error", CacheObjectScriptClsSyntaxHighlighter.BAD_CHARACTER)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return CacheObjectScriptIcons.MACFILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new CacheObjectScriptClsSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "Include csp \n" +
                "///hgvrtvhvt \n" +
                "Import  model.Address Class model.Addr Extends %SerialObject [ sad,ifdubh=(%SqlNameb,%skdhvg) ] \n" +
                "{ \n" +
                "ForeignKey EmpKey(EmpId) References a(b) [ sad=ggg,ifdubh=(%SqlNameb,%skdhvg)]; \n\n" +
                "Index cityIndex On city; \n" +
                "Index ThisIndex on SomeData [ Final ] \n" +
                "{ \n" +
                "} \n\n" +
                "ClassMethod MyMethod() As %Integer [SqlProc=dkfjvndfjv] \n" +
                "{\n " +
                "}\n\n" +
                "}\n";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "CDL";
    }
}
