package by.vsu.cacheplugin.lang.cls.psi;

import by.vsu.cacheplugin.lang.cls.CacheDefinitionLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CacheObjectScriptClsTokenType extends IElementType {
    public CacheObjectScriptClsTokenType(@NotNull @NonNls String debugName) {
        super(debugName, CacheDefinitionLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "CacheObjectScriptClsTokenType." + super.toString();
    }
}