package by.vsu.cacheplugin.action;

import by.vsu.cacheplugin.action.dialog.CacheConnectionDialog;
import by.vsu.cacheplugin.service.NamePathGenerator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.CacheQuery;
import com.intersys.objects.Database;
import com.intersys.xep.EventPersister;
import com.intersys.xep.PersisterFactory;
import com.intersys.xep.XEPException;

import javax.swing.*;
import java.io.File;
import java.sql.ResultSet;

/**
 * Created by mmaya on 15.04.2014.
 */
public class CacheConnection extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        String workspaceURL = DataKeys.PROJECT.getData(e.getDataContext()).getBasePath() + File.separator + "src";
        CacheConnectionDialog dialog = new CacheConnectionDialog();
        dialog.pack();
        dialog.setVisible(true);
        if (dialog.isOk()) {
            try {
                EventPersister myPersister = PersisterFactory.createPersister();
                myPersister.connect(dialog.getHost(), dialog.getPort(),
                        dialog.getNamespase(), dialog.getUsername(), dialog.getPassword());
                try {
                    JOptionPane.showMessageDialog(null, "Connection established!", "Success!", JOptionPane.CLOSED_OPTION);
                    String connString = "jdbc:Cache://" + dialog.getHost() + ":" + dialog.getPort() + "/" + dialog.getNamespase();
                    Database db = null;
                    try {
                        db = CacheDatabase.getDatabase(connString, dialog.getUsername(), dialog.getPassword());
                    } catch (CacheException e1) {
                        e1.printStackTrace();
                    }
                    CacheQuery qrRoutines = null;
                    try {
                        qrRoutines = new CacheQuery(db, "%Dictionary.ClassDefinitionQuery", "Summary");
                    } catch (CacheException e1) {
                        e1.printStackTrace();
                    }
                    final ResultSet rsClassess;
                    try {
                        rsClassess = qrRoutines.execute();

                        while (rsClassess.next()) {
                            String clsName = rsClassess.getString("Name");
                            boolean hidden = rsClassess.getBoolean("Hidden");
                            if (hidden) {
                                continue;
                            }
                            if (clsName.charAt(0) == '%') {
                                continue;
                            }
                            System.out.println(myPersister.callClassMethod("%Compiler.UDL.TextServices",
                                    "GetTextAsFile", dialog.getNamespase(), clsName,
                                    NamePathGenerator.createPathFromName(clsName + ".cls", workspaceURL)));
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "import failed:" + e1.getMessage(), "Error!", JOptionPane.OK_OPTION);
                    }
                    JOptionPane.showMessageDialog(null, "Import completed!", "Success!", JOptionPane.CLOSED_OPTION);
                } catch (XEPException ex) {
                    System.out.println("import failed:\n" + ex);
                    JOptionPane.showMessageDialog(null, "import failed:" + ex.getMessage(), "Error!", JOptionPane.OK_OPTION);
                }
                myPersister.close();
            } catch (XEPException ex) {
                System.out.println("Event storage failed:\n" + ex);
                JOptionPane.showMessageDialog(null, "Event storage failed:" + ex.getMessage(), "Error!", JOptionPane.OK_OPTION);
            }
        }
    }
}
