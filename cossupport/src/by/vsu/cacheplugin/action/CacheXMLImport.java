package by.vsu.cacheplugin.action;

import by.vsu.cacheplugin.parser.importer.CacheProjectParser;
import by.vsu.cacheplugin.parser.importer.filetype.BaseParser;
import by.vsu.cacheplugin.parser.importer.filetype.RoutineParser;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class CacheXMLImport extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
        JFileChooser fileChooser = new JFileChooser("");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Choose .xml file to import");
        int retval = fileChooser.showOpenDialog(fileChooser.getParent());
        if (retval == JFileChooser.APPROVE_OPTION) {
            String xmlURL = fileChooser.getSelectedFile().getPath();
            String workspaceURL = DataKeys.PROJECT.getData(e.getDataContext()).getBasePath() + File.separator + "src";
            CacheProjectParser listSax = new CacheProjectParser(workspaceURL);
            listSax.buildFileTree(xmlURL);
            if (listSax.isHasRoutine()) {
                BaseParser routineSax = new RoutineParser(workspaceURL, xmlURL);
                routineSax.run();
            }
           /* if (listSax.isHasClass()) {
                BaseParser classSax = new ClassParser(workspaceURL, xmlURL);
                classSax.run();
            } */
        }
    }
}