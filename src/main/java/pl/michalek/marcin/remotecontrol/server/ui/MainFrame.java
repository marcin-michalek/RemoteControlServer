package pl.michalek.marcin.remotecontrol.server.ui;

import pl.michalek.marcin.remotecontrol.server.utils.IpGetterUtil;

import javax.swing.*;
import java.awt.*;

/**
 * MainFrame displaying basic application information.
 */
public class MainFrame extends JFrame {
    private JButton button1;
    private JTextField textField1;

    public MainFrame() {
        String ipAddress = "Your local IP address is: \n" + IpGetterUtil.getIpInLocalNetwork();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new TextField(ipAddress));

        add(panel);
        pack();
    }

}
