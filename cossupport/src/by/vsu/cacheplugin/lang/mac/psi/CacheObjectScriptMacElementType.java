package by.vsu.cacheplugin.lang.mac.psi;

import by.vsu.cacheplugin.lang.mac.CacheObjectScriptLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CacheObjectScriptMacElementType extends IElementType {
    public CacheObjectScriptMacElementType(@NotNull @NonNls String debugName) {
        super(debugName, CacheObjectScriptLanguage.INSTANCE);
    }
}
