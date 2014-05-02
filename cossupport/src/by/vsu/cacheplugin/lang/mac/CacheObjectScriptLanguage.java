package by.vsu.cacheplugin.lang.mac;

import com.intellij.lang.Language;

/**
 * Created by mmaya on 02.05.2014.
 */
public class CacheObjectScriptLanguage extends Language {
    public static final CacheObjectScriptLanguage INSTANCE = new CacheObjectScriptLanguage();

    private CacheObjectScriptLanguage() {
        super("COS");
    }
}
