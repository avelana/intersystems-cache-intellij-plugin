package by.vsu.cacheplugin.action;

import by.vsu.cacheplugin.parser.importer.CacheProjectParser;
import by.vsu.cacheplugin.parser.importer.filetype.BaseParser;
import by.vsu.cacheplugin.parser.importer.filetype.ClassParser;
import by.vsu.cacheplugin.parser.importer.filetype.RoutineParser;
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
            String workspaceURL = fileChooser.getSelectedFile().getParent() + File.separatorChar + "test";
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
    }
}
