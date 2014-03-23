package by.vsu.cacheplugin.lang.mac;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Created by Admin on 23.03.14.
 */
public class CacheObjectScriptMacLexerAdapter extends FlexAdapter {
    public CacheObjectScriptMacLexerAdapter() {
        super(new CacheObjectScriptMacLexer((Reader) null));
    }
}
