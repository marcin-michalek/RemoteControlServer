package pl.michalek.marcin.remotecontrol.server.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.michalek.marcin.remotecontrol.server.dto.ResponseDto;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Controller responsible for doing key press events via Robot class.
 */
@RestController
@RequestMapping("/keyboard")
public class KeyboardController {
    private Robot robot;

    @RequestMapping("/rewind")
    @ResponseBody
    ResponseDto pressRewind() {
        pressKey(KeyEvent.VK_LEFT);
        return ResponseDto.success();
    }

    @RequestMapping("/space")
    @ResponseBody
    ResponseDto pressSpace() {
        pressKey(KeyEvent.VK_SPACE);
        return ResponseDto.success();
    }

    @RequestMapping("/forward")
    @ResponseBody
    ResponseDto pressForward() {
        pressKey(KeyEvent.VK_RIGHT);
        return ResponseDto.success();
    }

    private void pressKey(int keyMask) {
        try {
            robot = getRobot();
            robot.keyPress(keyMask);
            robot.keyRelease(keyMask);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public Robot getRobot() throws AWTException {
        return robot != null ? robot : (robot = new Robot());
    }
}
