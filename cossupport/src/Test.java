import by.vsu.cacheplugin.parser.importer.CacheProjectParser;
import by.vsu.cacheplugin.parser.importer.filetype.BaseParser;
import by.vsu.cacheplugin.parser.importer.filetype.RoutineParser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Test {
    public static void main(String[] args) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
        JFileChooser fileChooser = new JFileChooser("");
        fileChooser.setFileFilter(filter);
        int retval = fileChooser.showOpenDialog(fileChooser.getParent());
        if (retval == JFileChooser.APPROVE_OPTION) {
            String xmlURL = fileChooser.getSelectedFile().getName();
            String workspaceURL = fileChooser.getSelectedFile().getParent() + File.separatorChar + "test";
            CacheProjectParser listSax = new CacheProjectParser(workspaceURL);
            listSax.buildFileTree(xmlURL);
            if (listSax.isHasRoutine()) {
                BaseParser routineSax = new RoutineParser(workspaceURL, xmlURL);
                routineSax.run();
            }
        }
    }
}