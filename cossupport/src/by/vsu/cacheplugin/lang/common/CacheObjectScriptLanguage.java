package by.vsu.cacheplugin.lang.common;

import com.intellij.lang.Language;

public class CacheObjectScriptLanguage extends Language {
    public static final CacheObjectScriptLanguage INSTANCE = new CacheObjectScriptLanguage();

    private CacheObjectScriptLanguage() {
        super("COS");
    }
}