package by.vsu.cacheplugin.lang.cls.psi;

import by.vsu.cacheplugin.lang.cls.CacheDefinitionLanguage;
import by.vsu.cacheplugin.lang.cls.CacheObjectScriptClsFileType;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class CacheObjectScriptClsFile extends PsiFileBase {
    public CacheObjectScriptClsFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, CacheDefinitionLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return CacheObjectScriptClsFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return ".cls File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}