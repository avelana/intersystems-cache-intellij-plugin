package by.vsu.cacheplugin.action;

import by.vsu.cacheplugin.action.dialog.CacheConnectionDialog;
import by.vsu.cacheplugin.service.ConnectionStorage;
import by.vsu.cacheplugin.service.NamePathGenerator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intersys.classes.RoutineMgr;
import com.intersys.objects.BooleanHolder;
import com.intersys.objects.CacheException;
import com.intersys.xep.XEPException;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mmaya on 15.04.2014.
 */
public class CacheServerExport extends AnAction {
    private ConnectionStorage connStorage;

    public void actionPerformed(AnActionEvent e) {
        CacheConnectionDialog dialog = new CacheConnectionDialog();
        dialog.pack();
        dialog.setVisible(true);
        if (dialog.isOk()) {
            connStorage = ConnectionStorage.getInstance();
            try {
                connStorage.connectToPersister();
                try {
                    JOptionPane.showMessageDialog(null, "Connection established!", "Success!", JOptionPane.INFORMATION_MESSAGE);

                    if (JOptionPane.showConfirmDialog(null, "Do you want to export your project to namespace "
                                    + connStorage.getNamespace() + " ?", "Confirm",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE
                    ) == JOptionPane.OK_OPTION) {
                        //todo change when the bug in method will be removed
                        String root = DataKeys.MODULE.getData(e.getDataContext()).getModuleFilePath();
                        root = root.replace("/", File.separator);
                        root = root.substring(0, root.lastIndexOf(File.separator))
                                + File.separator + "src";
                        File f = new File(root + File.separator + "classes");
                        if (f.isDirectory()) {
                            exportClasses(f, f.getAbsolutePath());
                        }
                        f = new File(root + File.separator + "programs");
                        if (f.isDirectory()) {
                            exportRoutines(f);
                        }
                        JOptionPane.showMessageDialog(null, "Export completed!", "Success!", JOptionPane.CLOSED_OPTION);
                    }
                    DataKeys.PROJECT.getData(e.getDataContext()).getWorkspaceFile().getFileSystem().refresh(true);
                } catch (Exception ex) {
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

    private void exportRoutines(File rootDir) throws CacheException, IOException {
        List<File> allFiles = Arrays.asList(rootDir.listFiles());
        for (File child : allFiles) {
            System.out.println(child.getName());
            RoutineMgr routineMgr = new RoutineMgr(connStorage.getDb(),
                    child.getName());
            writeRoutine(routineMgr, child);
            BooleanHolder refresh = new BooleanHolder(false);
            routineMgr.sys_Save(refresh);
        }
    }

    private void writeRoutine(RoutineMgr routineMgr, File child) throws CacheException, IOException {
        Writer out = routineMgr.getCodeOut();
        BufferedWriter bout = new BufferedWriter(out);
        FileReader in = new FileReader(child);
        BufferedReader bin = new BufferedReader(in);
        while (bin.ready()) {
            bout.write(new String(bin.readLine().getBytes(), Charset.forName("UTF-8")));
            bout.newLine();
        }
        bin.close();
        in.close();
        bout.close();
        out.close();
    }

    private void exportClasses(File f, String home) {
        for (File child : f.listFiles()) {
            System.out.println(child.getName());
            if (child.isDirectory()) {
                exportClasses(child, home);
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