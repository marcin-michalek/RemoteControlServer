package pl.michalek.marcin.remotecontrol.server.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Class responsible for getting host IP in local network.
 */
public class IpGetterUtil {
    private static final String LOCAL_ADDRESS_REGEX = "192\\.168\\..*";

    public static String getIpInLocalNetwork() {
        Enumeration<NetworkInterface> networkInterfaces = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> address = networkInterface.getInetAddresses();
                while (address.hasMoreElements()) {
                    InetAddress inetAddress = address.nextElement();
                    if (inetAddress.getHostAddress().matches(LOCAL_ADDRESS_REGEX)){
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return null;
    }
}
