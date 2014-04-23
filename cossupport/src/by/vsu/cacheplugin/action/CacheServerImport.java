package by.vsu.cacheplugin.action;

import by.vsu.cacheplugin.action.dialog.CacheConnectionDialog;
import by.vsu.cacheplugin.service.ConnectionStorage;
import by.vsu.cacheplugin.service.NamePathGenerator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intersys.objects.CacheException;
import com.intersys.objects.CacheQuery;
import com.intersys.xep.XEPException;

import javax.swing.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mmaya on 15.04.2014.
 */
public class CacheServerImport extends AnAction {

    public void actionPerformed(AnActionEvent e) {
        String workspaceURL = DataKeys.MODULE.getData(e.getDataContext()).getModuleFilePath();
        workspaceURL = workspaceURL.substring(0,
                workspaceURL.indexOf(DataKeys.MODULE.getData(e.getDataContext()).getModuleFile().getName()) - 1);
        workspaceURL += File.separator + "src";
        File f = new File(workspaceURL);
        f.mkdir();
        CacheConnectionDialog dialog = new CacheConnectionDialog();
        dialog.pack();
        dialog.setVisible(true);
        if (dialog.isOk()) {
            ConnectionStorage connStorage = ConnectionStorage.getInstance();
            try {
                connStorage.connectToPersister();
                try {
                    JOptionPane.showMessageDialog(null, "Connection established!", "Success!", JOptionPane.INFORMATION_MESSAGE);

                    if (JOptionPane.showConfirmDialog(null, "Do you want to import namespace "
                                    + connStorage.getNamespace() + " to your project?", "Confirm",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE
                    ) == JOptionPane.OK_OPTION) {
                        CacheQuery qrCls = null;
                        try {
                            qrCls = new CacheQuery(connStorage.getDb(), "%Dictionary.ClassDefinitionQuery", "Summary");
                        } catch (CacheException e1) {
                            e1.printStackTrace();
                        }
                        final ResultSet rsClassess;
                        try {
                            rsClassess = qrCls.execute();

                            while (rsClassess.next()) {
                                String clsName = rsClassess.getString("Name");
                                boolean hidden = rsClassess.getBoolean("Hidden");
                                if (hidden) {
                                    continue;
                                }
                                if (clsName.charAt(0) == '%') {
                                    continue;
                                }
                                System.out.println(connStorage.getPersister()
                                        .callClassMethod("%Compiler.UDL.TextServices", "GetTextAsFile",
                                                connStorage.getNamespace(), clsName,
                                                NamePathGenerator.createPathFromName(clsName + ".cls", workspaceURL)));
                                System.out.println(workspaceURL);
                            }
                        } catch (CacheException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, "import failed:" + e1.getMessage(), "Error!", JOptionPane.OK_OPTION);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, "import failed, sql error:"
                                    + e1.getMessage(), "Error!", JOptionPane.OK_OPTION);
                        }
                        JOptionPane.showMessageDialog(null, "Import completed!", "Success!", JOptionPane.CLOSED_OPTION);
                    }
                    DataKeys.PROJECT.getData(e.getDataContext()).getWorkspaceFile().getFileSystem().refresh(true);
                } catch (XEPException ex) {
                    System.out.println("import failed:\n" + ex);
                    JOptionPane.showMessageDialog(null, "import failed:" + ex.getMessage(), "Error!", JOptionPane.OK_OPTION);
                }
                connStorage.disconnectFromPersister();
            } catch (XEPException ex) {
                System.out.println("Event storage failed:\n" + ex);
                JOptionPane.showMessageDialog(null, "Event storage failed:" + ex.getMessage(), "Error!", JOptionPane.OK_OPTION);
            }
        }
    }
}