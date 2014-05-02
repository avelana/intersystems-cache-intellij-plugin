package by.vsu.cacheplugin.lang.cls;

import com.intellij.lang.Language;

/**
 * Created by mmaya on 02.05.2014.
 */
public class CacheDefinitionLanguage extends Language {
    public static final CacheDefinitionLanguage INSTANCE = new CacheDefinitionLanguage();

    private CacheDefinitionLanguage() {
        super("CDL");
    }
}
