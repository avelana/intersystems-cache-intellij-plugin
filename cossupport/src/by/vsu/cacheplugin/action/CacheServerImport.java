package by.vsu.cacheplugin.action;

import by.vsu.cacheplugin.action.dialog.CacheConnectionDialog;
import by.vsu.cacheplugin.service.ConnectionStorage;
import by.vsu.cacheplugin.service.NamePathGenerator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intersys.classes.RoutineMgr;
import com.intersys.objects.CacheException;
import com.intersys.objects.CacheQuery;
import com.intersys.objects.DatabaseUtilities;
import com.intersys.xep.XEPException;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mmaya on 15.04.2014.
 */
public class CacheServerImport extends AnAction {
    private ConnectionStorage connStorage;
    private String workspaceURL;

    public void actionPerformed(AnActionEvent e) {
        workspaceURL = DataKeys.MODULE.getData(e.getDataContext()).getModuleFilePath();
        workspaceURL = workspaceURL.substring(0,
                workspaceURL.indexOf(DataKeys.MODULE.getData(e.getDataContext()).getModuleFile().getName()) - 1);
        workspaceURL += File.separator + "src";
        File f = new File(workspaceURL);
        f.mkdir();
        CacheConnectionDialog dialog = new CacheConnectionDialog();
        dialog.pack();
        dialog.setVisible(true);
        if (dialog.isOk()) {
            connStorage = ConnectionStorage.getInstance();
            try {
                connStorage.connectToPersister();
                try {
                    JOptionPane.showMessageDialog(null, "Connection established!", "Success!", JOptionPane.INFORMATION_MESSAGE);

                    if (JOptionPane.showConfirmDialog(null, "Do you want to import namespace "
                                    + connStorage.getNamespace() + " to your project?", "Confirm",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE
                    ) == JOptionPane.OK_OPTION) {
                        importClasses();
                        importRoutines();
                        JOptionPane.showMessageDialog(null, "Import completed!", "Success!", JOptionPane.CLOSED_OPTION);
                    }
                    DataKeys.PROJECT.getData(e.getDataContext()).getWorkspaceFile().getFileSystem().refresh(true);
                } catch (XEPException ex) {
                    System.out.println("import failed:\n" + ex);
                    JOptionPane.showMessageDialog(null, "import failed:" + ex.getMessage(), "Error!", JOptionPane.OK_OPTION);
                } catch (CacheException ex) {
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

    private void importClasses() throws CacheException {
        File file = new File(workspaceURL + File.separator + "classes");
        file.mkdir();
        DatabaseUtilities dbu = new DatabaseUtilities(connStorage.getDb());
        String[] str = dbu.listAllClasses();
        for (String clsName : str) {
            System.out.println(connStorage.getPersister()
                    .callClassMethod("%Compiler.UDL.TextServices", "GetTextAsFile",
                            connStorage.getNamespace(), clsName,
                            NamePathGenerator.createPathFromName(clsName + ".cls", file.getAbsolutePath())));
        }
    }

    private void importRoutines() throws CacheException {
        CacheQuery qrRoutineList = null;
        try {
            qrRoutineList = new CacheQuery(connStorage.getDb(), "%Library.Routine", "RoutineList");
        } catch (CacheException e1) {
            e1.printStackTrace();
        }
        final ResultSet rsRoutineList;
        Object[] argRoutineList = new Object[]{"*.*", 1, 0, connStorage.getNamespace()};
        rsRoutineList = qrRoutineList.execute(argRoutineList);
        File progRoot = new File(workspaceURL + File.separator + "programs");
        progRoot.mkdir();
        try {
            while (rsRoutineList.next()) {
                String routineName = rsRoutineList.getString("Name");
                RoutineMgr routineMgr = new RoutineMgr(connStorage.getDb(), routineName);
                Reader r = routineMgr.getCodeIn();
                BufferedReader reader = new BufferedReader(r);
                File file = new File(progRoot.getAbsolutePath() + File.separator + routineName);
                file.createNewFile();
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8"));
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                while (reader.ready()) {
                    bufferedWriter.write(reader.readLine());
                    bufferedWriter.newLine();
                }
                reader.close();
                r.close();
                bufferedWriter.close();
                writer.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}