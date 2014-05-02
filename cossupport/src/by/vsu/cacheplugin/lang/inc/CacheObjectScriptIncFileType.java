package by.vsu.cacheplugin.lang.inc;

import by.vsu.cacheplugin.lang.common.CacheObjectScriptIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CacheObjectScriptIncFileType extends LanguageFileType {
    public static final CacheObjectScriptIncFileType INSTANCE = new CacheObjectScriptIncFileType();

    private CacheObjectScriptIncFileType() {
        super(CacheObjectScriptIncLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Cache ObjectScript inc file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Cache ObjectScript included commands file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "inc";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return CacheObjectScriptIcons.INCFILE;
    }
}