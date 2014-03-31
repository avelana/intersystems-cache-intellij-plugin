package by.vsu.cacheplugin.module;

import by.vsu.cacheplugin.parser.importer.CacheProjectParser;
import by.vsu.cacheplugin.parser.importer.filetype.BaseParser;
import by.vsu.cacheplugin.parser.importer.filetype.ClassParser;
import by.vsu.cacheplugin.parser.importer.filetype.RoutineParser;
import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.module.ModifiableModuleModel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleWithNameAlreadyExists;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.vfs.VirtualFile;
import org.jdom.JDOMException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

// todo: setup page?
public final class CacheObjectScriptModuleBuilder extends JavaModuleBuilder {

    @Override
    public CacheObjectScriptModuleType getModuleType() {
        return CacheObjectScriptModuleType.INSTANCE;
    }

    @Override
    public void setupRootModel(ModifiableRootModel rootModel) throws ConfigurationException {
        ProjectJdkTable table = ProjectJdkTable.getInstance();
        Sdk[] sdks = table.getAllJdks();
        Sdk ghc = sdks[0];//todo remove this magic!!!
        if (ghc != null) {
            Project project = rootModel.getProject();
            // todo: do not reset if overriden by user?
            ProjectRootManager.getInstance(project).setProjectSdk(ghc);
            setModuleJdk(ghc); // todo: inherit SDK from project?
        }
        // todo: do not use tabs in project
        super.setupRootModel(rootModel);
    }

    @NotNull
    @Override
    public Module createModule(@NotNull ModifiableModuleModel moduleModel) throws InvalidDataException, IOException, ModuleWithNameAlreadyExists, JDOMException, ConfigurationException {
        Module createdModule = super.createModule(moduleModel);
        FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, false, false, false, false);
        VirtualFile file = null;
        file = FileChooser.chooseFile(descriptor, createdModule.getProject(), file);
        if (file != null) {
            String xmlURL = file.getPath();
            String workspaceURL = createdModule.getModuleFilePath() + File.separator + "src";
            CacheProjectParser listSax = new CacheProjectParser(workspaceURL);
            listSax.buildFileTree(xmlURL);
            if (listSax.isHasRoutine()) {
                BaseParser routineSax = new RoutineParser(workspaceURL, xmlURL);
                routineSax.run();
            }
            if (listSax.isHasClass()) {
                BaseParser classSax = new ClassParser(workspaceURL, xmlURL);
                classSax.run();
            }
        }
        return createdModule;
    }
}
