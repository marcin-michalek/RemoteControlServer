package pl.michalek.marcin.remotecontrol.server.dto;

/**
 * Class for transferring coordinates.
 */
public class PointDto {

    private int x;
    private int y;

    public PointDto() {
    }

    public PointDto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
