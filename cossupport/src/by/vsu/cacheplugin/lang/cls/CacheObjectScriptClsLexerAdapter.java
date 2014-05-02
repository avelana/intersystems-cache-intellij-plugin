package by.vsu.cacheplugin.lang.cls;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Created by mmaya on 02.05.2014.
 */
public class CacheObjectScriptClsLexerAdapter extends FlexAdapter {
    public CacheObjectScriptClsLexerAdapter() {
        super(new CacheObjectScriptClsLexer((Reader) null));
    }
}
