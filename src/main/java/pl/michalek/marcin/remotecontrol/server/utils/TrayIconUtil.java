package pl.michalek.marcin.remotecontrol.server.utils;

import pl.michalek.marcin.remotecontrol.server.utils.IpGetterUtil;

import java.awt.*;

/**
 * Class responsible for showing tray icon.
 * Logs error if tray icon showing is not supported.
 */
public class TrayIconUtil {

    public static void showTrayIcon(String path) {
        if (!systemTrayIconSupported()) {
            return;
        }

        final TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(path));
        String localIPAddress = IpGetterUtil.getIpInLocalNetwork();
        trayIcon.setToolTip(localIPAddress != null ? localIPAddress : "Could not acquire local IP address");
        trayIcon.setImageAutoSize(true);
        final SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("TrayIcon could not be added.");
        }
    }

    private static boolean systemTrayIconSupported() {
        boolean supported = SystemTray.isSupported();
        if (!supported) {
            System.err.println("SystemTray is not supported");
        }
        return supported;
    }
}
