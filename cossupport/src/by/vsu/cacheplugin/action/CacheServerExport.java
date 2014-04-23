package by.vsu.cacheplugin.action;

import by.vsu.cacheplugin.action.dialog.CacheConnectionDialog;
import by.vsu.cacheplugin.service.ConnectionStorage;
import by.vsu.cacheplugin.service.NamePathGenerator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intersys.xep.XEPException;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by mmaya on 15.04.2014.
 */
public class CacheServerExport extends AnAction {

    public void actionPerformed(AnActionEvent e) {
        CacheConnectionDialog dialog = new CacheConnectionDialog();
        dialog.pack();
        dialog.setVisible(true);
        if (dialog.isOk()) {
            ConnectionStorage connStorage = ConnectionStorage.getInstance();
            try {
                connStorage.connectToPersister();
                try {
                    JOptionPane.showMessageDialog(null, "Connection established!", "Success!", JOptionPane.INFORMATION_MESSAGE);

                    if (JOptionPane.showConfirmDialog(null, "Do you want to export your project to namespace "
                                    + connStorage.getNamespace() + " ?", "Confirm",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE
                    ) == JOptionPane.OK_OPTION) {
                        //todo change when the bug in method will be removed
                        String vfs = DataKeys.MODULE.getData(e.getDataContext()).getModuleFilePath();
                        vfs = vfs.replace("/", File.separator);
                        vfs = vfs.substring(0, vfs.lastIndexOf(File.separator))
                                + File.separator + "src";
                        File f = new File(vfs);
                        if (f.isDirectory()) {
                            exportFiles(f, connStorage, vfs);
                        }
                        JOptionPane.showMessageDialog(null, "Export completed!", "Success!", JOptionPane.CLOSED_OPTION);
                    }
                    DataKeys.PROJECT.getData(e.getDataContext()).getWorkspaceFile().getFileSystem().refresh(true);
                } catch (XEPException ex) {
                    System.out.println("export failed:\n" + ex);
                    JOptionPane.showMessageDialog(null, "export failed:" + ex.getMessage(), "Error!", JOptionPane.OK_OPTION);
                }
                connStorage.disconnectFromPersister();
            } catch (XEPException ex) {
                System.out.println("Event storage failed:\n" + ex);
                JOptionPane.showMessageDialog(null, "Event storage failed:" + ex.getMessage(), "Error!", JOptionPane.OK_OPTION);
            }
        }
    }

    private void exportFiles(File f, ConnectionStorage connStorage, String home) {
        for (File child : f.listFiles()) {
            System.out.println(child.getName());
            if (child.isDirectory()) {
                exportFiles(child, connStorage, home);
            } else {
                String name = child.getAbsolutePath();
                String clsPath = child.getAbsolutePath().
                        substring(child.getAbsolutePath().indexOf(home),
                                child.getAbsolutePath().length());
                if (name.substring(name.length() - 3, name.length()).equals("cls")) {
                    try {
                        FileReader br = new FileReader(name);
                        StringBuilder text = new StringBuilder();
                        while (br.ready()) {
                            text.append(Character.toChars(br.read()));
                        }
                        System.out.println(connStorage.getPersister()
                                .callClassMethod("%Compiler.UDL.TextServices", "SetTextFromString",
                                        connStorage.getNamespace(), NamePathGenerator.pathToName(clsPath),
                                        new String(text.toString().getBytes(), Charset.forName("UTF-8")), null));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}