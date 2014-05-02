package by.vsu.cacheplugin.lang.inc;

import com.intellij.lang.Language;

/**
 * Created by mmaya on 02.05.2014.
 */
public class CacheObjectScriptIncLanguage extends Language {
    public static final CacheObjectScriptIncLanguage INSTANCE = new CacheObjectScriptIncLanguage();

    private CacheObjectScriptIncLanguage() {
        super("COSINC");
    }
}
