package by.vsu.cacheplugin.lang.mac;

import by.vsu.cacheplugin.lang.common.CacheObjectScriptIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CacheObjectScriptMacFileType extends LanguageFileType {
    public static final CacheObjectScriptMacFileType INSTANCE = new CacheObjectScriptMacFileType();

    private CacheObjectScriptMacFileType() {
        super(CacheObjectScriptLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Cache ObjectScript mac file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Cache ObjectScript command file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "mac";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return CacheObjectScriptIcons.MACFILE;
    }
}