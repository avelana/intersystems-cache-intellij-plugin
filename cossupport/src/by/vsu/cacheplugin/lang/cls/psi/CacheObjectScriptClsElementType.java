package by.vsu.cacheplugin.lang.cls.psi;

import by.vsu.cacheplugin.lang.cls.CacheDefinitionLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CacheObjectScriptClsElementType extends IElementType {
    public CacheObjectScriptClsElementType(@NotNull @NonNls String debugName) {
        super(debugName, CacheDefinitionLanguage.INSTANCE);
    }
}
