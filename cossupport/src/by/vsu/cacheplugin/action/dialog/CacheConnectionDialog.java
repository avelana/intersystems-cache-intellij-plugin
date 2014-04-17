package by.vsu.cacheplugin.action.dialog;

import by.vsu.cacheplugin.service.ConnectionStorage;
import com.intersys.objects.CacheException;

import javax.swing.*;
import java.awt.event.*;

public class CacheConnectionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JTextField namespaceField;
    private JTextField portField;
    private JTextField hostField;
    private boolean isOk;

    public boolean isOk() {
        return isOk;
    }

    public CacheConnectionDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        try {
            ConnectionStorage.getInstance().setParams(hostField.getText(),
                    namespaceField.getText(),
                    usernameField.getText(),
                    String.valueOf(passwordField.getPassword()),
                    Integer.parseInt(portField.getText()));
            isOk = true;
            dispose();
        } catch (CacheException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection haven't been established: " + e1.getMessage(),
                    "Error!", JOptionPane.OK_OPTION);
        }
    }

    private void onCancel() {
        isOk = false;
        dispose();
    }

}
