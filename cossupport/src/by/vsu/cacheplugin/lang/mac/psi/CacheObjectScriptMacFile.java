package by.vsu.cacheplugin.lang.mac.psi;

import by.vsu.cacheplugin.lang.mac.CacheObjectScriptLanguage;
import by.vsu.cacheplugin.lang.mac.CacheObjectScriptMacFileType;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class CacheObjectScriptMacFile extends PsiFileBase {
    public CacheObjectScriptMacFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, CacheObjectScriptLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return CacheObjectScriptMacFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return ".mac File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}