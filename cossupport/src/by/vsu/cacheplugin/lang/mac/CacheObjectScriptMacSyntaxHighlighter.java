package by.vsu.cacheplugin.lang.mac;

import by.vsu.cacheplugin.lang.mac.psi.CacheObjectScriptMacTypes;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Reader;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class CacheObjectScriptMacSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey LABEL = createTextAttributesKey("Mac_LABEL", new TextAttributes(Color.GREEN, null, null, null, Font.BOLD));
    public static final TextAttributesKey COMMAND = createTextAttributesKey("Mac_COMMAND", new TextAttributes(Color.BLUE, null, null, null, Font.BOLD));
    public static final TextAttributesKey LOCAL = createTextAttributesKey("Mac_LOCAL", new TextAttributes(Color.ORANGE.darker(), null, null, null, Font.BOLD));
    public static final TextAttributesKey GLOBAL = createTextAttributesKey("Mac_GLOBAL", new TextAttributes(Color.cyan, null, null, null, Font.BOLD));
    public static final TextAttributesKey SYS = createTextAttributesKey("Mac_SYS", new TextAttributes(Color.magenta, null, null, null, Font.BOLD));
    public static final TextAttributesKey GLOBALSYS = createTextAttributesKey("Mac_GLOBALSYS", new TextAttributes(Color.PINK, null, null, null, Font.BOLD));
    public static final TextAttributesKey NUMBER = createTextAttributesKey("Mac_NUMBER", SyntaxHighlighterColors.NUMBER);
    public static final TextAttributesKey STRING = createTextAttributesKey("Mac_STRING", new TextAttributes(Color.darkGray, null, null, null, Font.BOLD));
    public static final TextAttributesKey OPERATION = createTextAttributesKey("Mac_OPERATION", new TextAttributes(Color.BLUE.darker(), null, null, null, Font.ITALIC));
    public static final TextAttributesKey COMMENT = createTextAttributesKey("Mac_COMMENT", new TextAttributes(Color.GRAY, null, null, null, Font.BOLD));

    static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("Mac_BAD_CHARACTER",
            new TextAttributes(Color.RED, null, null, null, Font.BOLD));

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] LABEL_KEYS = new TextAttributesKey[]{LABEL};
    private static final TextAttributesKey[] COMMAND_KEYS = new TextAttributesKey[]{COMMAND};
    private static final TextAttributesKey[] LOCAL_KEYS = new TextAttributesKey[]{LOCAL};
    private static final TextAttributesKey[] GLOBAL_KEYS = new TextAttributesKey[]{GLOBAL};
    private static final TextAttributesKey[] SYS_KEYS = new TextAttributesKey[]{SYS};
    private static final TextAttributesKey[] GLOBALSYS_KEYS = new TextAttributesKey[]{GLOBALSYS};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] OPERATION_KEYS = new TextAttributesKey[]{OPERATION};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];


    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FlexAdapter(new CacheObjectScriptMacLexer((Reader) null));
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(CacheObjectScriptMacTypes.LABEL)) {
            return LABEL_KEYS;
        } else if (tokenType.equals(CacheObjectScriptMacTypes.COMMAND)) {
            return COMMAND_KEYS;
        } else if (tokenType.equals(CacheObjectScriptMacTypes.LOCAL)) {
            return LOCAL_KEYS;
        } else if (tokenType.equals(CacheObjectScriptMacTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else if (tokenType.equals(CacheObjectScriptMacTypes.GLOBAL)) {
            return GLOBAL_KEYS;
        } else if (tokenType.equals(CacheObjectScriptMacTypes.SYS)) {
            return SYS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptMacTypes.GLOBALSYS)) {
            return GLOBALSYS_KEYS;
        } else if (tokenType.equals(CacheObjectScriptMacTypes.NUMBER)) {
            return NUMBER_KEYS;
        } else if (tokenType.equals(CacheObjectScriptMacTypes.STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(CacheObjectScriptMacTypes.OPERATION)) {
            return OPERATION_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}

