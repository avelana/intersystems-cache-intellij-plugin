package by.vsu.cacheplugin.lang.inc.psi;

import by.vsu.cacheplugin.lang.common.CacheObjectScriptLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CacheObjectScriptIncTokenType extends IElementType {
    public CacheObjectScriptIncTokenType(@NotNull @NonNls String debugName) {
        super(debugName, CacheObjectScriptLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "CacheObjectScriptIncTokenType." + super.toString();
    }
}