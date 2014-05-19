package by.vsu.cacheplugin.action;

import by.vsu.cacheplugin.parser.exporter.CacheProjectAssembler;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intersys.objects.CacheException;

import javax.swing.*;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: mmaya
 * Date: 21.11.13
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class CacheXMLExport extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        String name = null;
        if ((name = JOptionPane.showInputDialog(null, "Enter XML name:", "")) != "") {
            //todo change when the bug in method will be removed
            String root = DataKeys.MODULE.getData(e.getDataContext()).getModuleFilePath();
            root = root.replace("/", File.separator);
            root = root.substring(0, root.lastIndexOf(File.separator))
                    + File.separator + "src";
            File f = new File(root + File.separator + "programs");
            if (f.isDirectory()) {
                try {
                    exportRoutines(f, name);
                } catch (CacheException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "Export completed!", "Success!", JOptionPane.CLOSED_OPTION);
        }
        DataKeys.PROJECT.getData(e.getDataContext()).getWorkspaceFile().getFileSystem().refresh(true);
    }

    private void exportRoutines(File rootDir, String filename) throws CacheException, IOException {
        CacheProjectAssembler listSax = new CacheProjectAssembler(rootDir, filename);
        try {
            listSax.writeFile();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
