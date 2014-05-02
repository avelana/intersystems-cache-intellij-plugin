package by.vsu.cacheplugin.lang.inc.psi;

import by.vsu.cacheplugin.lang.inc.CacheObjectScriptIncLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CacheObjectScriptIncTokenType extends IElementType {
    public CacheObjectScriptIncTokenType(@NotNull @NonNls String debugName) {
        super(debugName, CacheObjectScriptIncLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "CacheObjectScriptIncTokenType." + super.toString();
    }
}