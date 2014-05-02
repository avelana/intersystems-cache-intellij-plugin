package by.vsu.cacheplugin.lang.inc.psi;

import by.vsu.cacheplugin.lang.inc.CacheObjectScriptIncFileType;
import by.vsu.cacheplugin.lang.inc.CacheObjectScriptIncLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class CacheObjectScriptIncFile extends PsiFileBase {
    public CacheObjectScriptIncFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, CacheObjectScriptIncLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return CacheObjectScriptIncFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return ".inc File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}