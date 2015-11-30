package pl.michalek.marcin.remotecontrol.server.utils;

import pl.michalek.marcin.remotecontrol.server.dto.PointDto;

/**
 * Contains utility methods for Point calculations.
 */
public class PointUtil {

    private PointUtil() {
    }

    public static int distance(PointDto first, PointDto second) {
        return (int) Math.sqrt(
            Math.pow(second.getX() - first.getX(), 2) +
            Math.pow(second.getY() - first.getY(), 2));
    }

    public static int angleInDegrees(PointDto first, PointDto second) {
        return (int) Math.toDegrees(Math.atan2(second.getX() - first.getX(), second.getY() - first.getY()));
    }
}
