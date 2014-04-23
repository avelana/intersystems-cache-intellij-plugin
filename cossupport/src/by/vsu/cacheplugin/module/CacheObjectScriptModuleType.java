package by.vsu.cacheplugin.module;

import by.vsu.cacheplugin.lang.common.CacheObjectScriptIcons;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.projectRoots.Sdk;

import javax.swing.*;

public final class CacheObjectScriptModuleType extends ModuleType<CacheObjectScriptModuleBuilder> {

    public static final CacheObjectScriptModuleType INSTANCE = new CacheObjectScriptModuleType();

    public CacheObjectScriptModuleType() {
        super("CACHE_OBJECTSCRIPT_MODULE");
    }

    public CacheObjectScriptModuleBuilder createModuleBuilder() {
        return new CacheObjectScriptModuleBuilder();
    }

    public String getName() {
        return "    CacheObjectScript Module";
    }

    public String getDescription() {
        return "<b>CacheObjectScript</b> module similar to Cache Studio project"; // todo: more wordy!
    }

    public Icon getBigIcon() {
        return CacheObjectScriptIcons.LARGEICON;
    }

    public Icon getNodeIcon(boolean isOpened) {
        return CacheObjectScriptIcons.LARGEICON; // todo: another icon?
    }

    @Override
    public boolean isValidSdk(Module module, Sdk projectSdk) {
        return true; // todo
    }

    public static ModuleType<?> get(Module module) {
        return ModuleType.get(module);
    }
}
