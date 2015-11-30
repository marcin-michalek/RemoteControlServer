package pl.michalek.marcin.remotecontrol.server.app;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.michalek.marcin.remotecontrol.server.dto.MouseMoveParamsDto;
import pl.michalek.marcin.remotecontrol.server.dto.PointDto;
import pl.michalek.marcin.remotecontrol.server.dto.ResponseDto;
import pl.michalek.marcin.remotecontrol.server.utils.PointUtil;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Controller responsible for doing key press events via Robot class.
 */
@RestController
@RequestMapping("/mouse")
public class MouseController {
    private Robot robot;

    @RequestMapping("/lmb")
    @ResponseBody
    ResponseDto pressLmb() {
        pressMouse(InputEvent.BUTTON1_DOWN_MASK);
        return ResponseDto.success();
    }

    @RequestMapping("/lmb2x")
    @ResponseBody
    ResponseDto pressLmb2x() {
        pressMouse(InputEvent.BUTTON1_DOWN_MASK);
        pressMouse(InputEvent.BUTTON1_DOWN_MASK);
        return ResponseDto.success();
    }

    @RequestMapping("/rmb")
    @ResponseBody
    ResponseDto pressRmb() {
        pressMouse(InputEvent.BUTTON3_DOWN_MASK);
        return ResponseDto.success();
    }

    @RequestMapping("/move")
    @ResponseBody
    ResponseDto pressForward(@RequestBody MouseMoveParamsDto mouseMoveParamsDto) {
        Point currentMouseLocation = MouseInfo.getPointerInfo().getLocation();
        int receivedDistance = PointUtil.distance(mouseMoveParamsDto.getDown(), mouseMoveParamsDto.getUp());
        int receivedAngle = PointUtil.angleInDegrees(mouseMoveParamsDto.getDown(), mouseMoveParamsDto.getUp());
        // ignored for now
        long receivedSpeed = receivedDistance * 100 /
                (mouseMoveParamsDto.getTimestampUp() - mouseMoveParamsDto.getTimestampDown());

        moveMouse(calculateEndPoint(currentMouseLocation, receivedDistance, receivedAngle));
        return ResponseDto.success();
    }

    private PointDto calculateEndPoint(Point currentMouseLocation, int receivedDistance, int receivedAngle) {
        int xLength = (int) (Math.cos(Math.toRadians(receivedAngle)) * receivedDistance);
        int yLength = (int) (Math.sin(Math.toRadians(receivedAngle)) * receivedDistance);
        return new PointDto((int) currentMouseLocation.getX() + yLength, (int) currentMouseLocation.getY() + xLength);
    }

    private void pressMouse(int mouseButtonMask) {
        try {
            robot = getRobot();
            robot.mousePress(mouseButtonMask);
            robot.mouseRelease(mouseButtonMask);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void moveMouse(PointDto point) {
        try {
            robot = getRobot();
            robot.mouseMove(point.getX(), point.getY());
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public Robot getRobot() throws AWTException {
        return robot != null ? robot : (robot = new Robot());
    }
}
