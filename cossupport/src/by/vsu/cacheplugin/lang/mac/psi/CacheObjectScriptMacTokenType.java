package by.vsu.cacheplugin.lang.mac.psi;

import by.vsu.cacheplugin.lang.mac.CacheObjectScriptLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CacheObjectScriptMacTokenType extends IElementType {
    public CacheObjectScriptMacTokenType(@NotNull @NonNls String debugName) {
        super(debugName, CacheObjectScriptLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "CacheObjectScriptMacTokenType." + super.toString();
    }
}