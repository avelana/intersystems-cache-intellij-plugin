package by.vsu.cacheplugin.module;

import com.intellij.openapi.roots.ui.configuration.BuildElementsEditor;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationState;
import com.intellij.openapi.roots.ui.configuration.ModuleElementsEditor;
import org.jetbrains.annotations.Nls;

import javax.swing.*;

final class OutputEditor extends ModuleElementsEditor {

    private final BuildElementsEditor compilerOutputEditor;

    OutputEditor(ModuleConfigurationState state) {
        super(state);
        compilerOutputEditor = new BuildElementsEditor(state) {
        };
    }

    protected JComponent createComponentImpl() {
        return compilerOutputEditor.createComponentImpl();
    }

    public void saveData() {
        compilerOutputEditor.saveData();
    }

    @Nls
    public String getDisplayName() {
        return "Paths";
    }

    public String getHelpTopic() {
        return compilerOutputEditor.getHelpTopic();
    }
}
