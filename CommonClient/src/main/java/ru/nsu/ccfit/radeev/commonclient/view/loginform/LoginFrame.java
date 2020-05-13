package ru.nsu.ccfit.radeev.commonclient.view.loginform;

import net.miginfocom.swing.MigLayout;
import ru.nsu.ccfit.radeev.commonclient.config.ClientConfig;
import ru.nsu.ccfit.radeev.commonclient.config.MainFrameConfig;
import ru.nsu.ccfit.radeev.commonclient.view.mainform.MainFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame {
    private final JPanel loginPanel;
    private JButton loginButton;
    private JTextField loginField;

    public LoginFrame(){
        super();

        setTitle("Авторизация");
        setSize(MainFrameConfig.MINIMUM_DIMENSION);
        setMinimumSize(MainFrameConfig.MINIMUM_DIMENSION);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });

        setLocationRelativeTo(null);
        loginPanel = new JPanel();
        loginPanel.setLayout(new MigLayout("align 50% 50%, btt"));
        add(loginPanel);
        initPanel();
    }

    private void initPanel(){
        JLabel loginText = new JLabel("Введите ваш ID: ");
        loginPanel.add(loginText, "");
        loginField = new JTextField(1);
        loginPanel.add(loginField, "width 15%");
        loginButton = new JButton("Войти");
        initButton();
        loginPanel.add(loginButton, "");
    }

    private void initButton(){
        loginButton.addActionListener(e -> {
            int id = Integer.parseInt(loginField.getText());
            ClientConfig.setId(id);
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            dispose();
        });
    }
}
