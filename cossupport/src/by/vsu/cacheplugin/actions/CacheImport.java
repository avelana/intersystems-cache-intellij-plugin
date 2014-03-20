package by.vsu.cacheplugin.actions;

import by.vsu.cacheplugin.parsers.importers.ProjectListSax;
import by.vsu.cacheplugin.parsers.importers.filetypes.AbstractObjectSax;
import by.vsu.cacheplugin.parsers.importers.filetypes.ClassSax;
import by.vsu.cacheplugin.parsers.importers.filetypes.RoutineSax;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class CacheImport extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
        JFileChooser fileChooser = new JFileChooser("");
        fileChooser.setFileFilter(filter);
        int retval = fileChooser.showOpenDialog(fileChooser.getParent());
        if (retval == JFileChooser.APPROVE_OPTION) {
            String xmlURL = fileChooser.getSelectedFile().getPath();
            String workspaceURL = fileChooser.getSelectedFile().getParent()+ File.separatorChar+"test";
            ProjectListSax listSax = new ProjectListSax(workspaceURL);
            listSax.buildFileTree(xmlURL);
            if(listSax.isHasRoutine()){
                AbstractObjectSax routineSax = new RoutineSax(workspaceURL, xmlURL);
                routineSax.run();
            }
            if(listSax.isHasClass()){
                AbstractObjectSax classSax = new ClassSax(workspaceURL, xmlURL);
                classSax.run();
            }
        }
    }
}
