package pl.michalek.marcin.remotecontrol.server.utils;

import pl.michalek.marcin.remotecontrol.server.ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class responsible for showing tray icon.
 * Logs error if tray icon showing is not supported.
 */
public class TrayIconUtil {
    private static MainFrame mainFrame;
    private static TrayIcon trayIcon;

    public static void setFrame(MainFrame frame) {
        mainFrame = frame;
    }

    public static void initTrayIcon(String path) {
        if (!systemTrayIconSupported()) {
            return;
        }

        trayIcon = new TrayIcon(new ImageIcon(TrayIconUtil.class.getResource(path)).getImage());
        String localIPAddress = IpGetterUtil.getIpInLocalNetwork();
        trayIcon.setToolTip(localIPAddress != null ? localIPAddress : "Could not acquire local IP address");
        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(mouseListener);
        final SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("TrayIcon could not be added.");
        }
    }

    public static void setTrayIconImage(String path) {
        trayIcon.setImage(new ImageIcon(TrayIconUtil.class.getResource(path)).getImage());
    }

    private static boolean systemTrayIconSupported() {
        boolean supported = SystemTray.isSupported();
        if (!supported) {
            System.err.println("SystemTray is not supported");
        }
        return supported;
    }

    private static MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // not interested
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (!e.isPopupTrigger()) {
                if (mainFrame.isVisible()) {
                    mainFrame.setVisible(false);
                } else {
                    mainFrame.setVisible(true);
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // not interested
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // not interested
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // not interested
        }
    };
}
