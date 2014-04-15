package by.vsu.cacheplugin.action.dialog;

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
    private String host, namespase, username, password;
    private Integer port;
    private boolean isOk;

    public boolean isOk() {
        return isOk;
    }

    public Integer getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getNamespase() {
        return namespase;
    }

    public String getHost() {
        return host;
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
// add your code here
        host = hostField.getText();
        port = Integer.parseInt(portField.getText());
        namespase = namespaceField.getText();
        username = usernameField.getText();
        password = String.valueOf(passwordField.getPassword());
        isOk = true;
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        isOk = false;
        dispose();
    }

   /* public static void main(String[] args) {
        CacheConnectionDialog dialog = new CacheConnectionDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }  */
}
