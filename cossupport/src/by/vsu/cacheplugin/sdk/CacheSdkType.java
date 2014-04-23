package by.vsu.cacheplugin.sdk;

import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SimpleJavaSdkType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by mmaya on 23.04.2014.
 */
public class CacheSdkType extends SimpleJavaSdkType {

    public CacheSdkType() {
        super();
    }

    @Nullable
    @Override
    public String suggestHomePath() {
        return System.getenv("GLOBALS_HOME");
    }

    @Override
    public boolean isValidSdkHome(String s) {
        return true;
    }

    @Override
    public String suggestSdkName(String s, String s2) {
        return "Cache";
    }

    @Override
    public String getPresentableName() {
        return "CacheSDK";
    }

    @Nullable
    @Override
    public String getVersionString(@NotNull Sdk sdk) {
        return "1.1";
    }
}
