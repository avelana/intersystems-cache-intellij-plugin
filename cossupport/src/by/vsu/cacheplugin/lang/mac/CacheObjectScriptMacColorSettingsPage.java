package by.vsu.cacheplugin.lang.mac;

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

public class CacheObjectScriptMacColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Command", CacheObjectScriptMacSyntaxHighlighter.COMMAND),
            new AttributesDescriptor("Local variables", CacheObjectScriptMacSyntaxHighlighter.LOCAL),
            new AttributesDescriptor("Globals", CacheObjectScriptMacSyntaxHighlighter.GLOBAL),
            new AttributesDescriptor("System variables", CacheObjectScriptMacSyntaxHighlighter.SYS),
            new AttributesDescriptor("System globals", CacheObjectScriptMacSyntaxHighlighter.GLOBALSYS),
            new AttributesDescriptor("String", CacheObjectScriptMacSyntaxHighlighter.STRING),
            new AttributesDescriptor("Number", CacheObjectScriptMacSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Operation", CacheObjectScriptMacSyntaxHighlighter.OPERATION),
            new AttributesDescriptor("Label", CacheObjectScriptMacSyntaxHighlighter.LABEL),
            new AttributesDescriptor("Error", CacheObjectScriptMacSyntaxHighlighter.BAD_CHARACTER),
            new AttributesDescriptor("Comment", CacheObjectScriptMacSyntaxHighlighter.COMMENT)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return CacheObjectScriptIcons.MACFILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new CacheObjectScriptMacSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "/* здесь\n" +
                " * типичный\n" +
                " * многострочный\n" +
                " * комментарий\n" +
                " */\n" +
                "//однострочный комментарий\n" +
                ";однострочный Cache комментарий\n" +
                "label: SET %X = 523\n" +
                "       DO WHILE INT^%SQROOT\n" +
                "       WRITE %Y\n" +
                "       WRITE $HOROLOG\n" +
                "       WRITE ^$cache\n" +
                "       GOTO label2\n" +
                "label2: QUIT\n" +
                "*-*-*errortext\n" +
                "DO";
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
        return "COS";
    }
}
