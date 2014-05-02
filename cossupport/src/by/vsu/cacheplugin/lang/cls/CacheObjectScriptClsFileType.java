package by.vsu.cacheplugin.lang.cls;

import by.vsu.cacheplugin.lang.common.CacheObjectScriptIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CacheObjectScriptClsFileType extends LanguageFileType {
    public static final CacheObjectScriptClsFileType INSTANCE = new CacheObjectScriptClsFileType();

    private CacheObjectScriptClsFileType() {
        super(CacheDefinitionLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Cache ObjectScript cls file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Cache ObjectScript class file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "cls";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return CacheObjectScriptIcons.CLSFILE;
    }
}