package pl.michalek.marcin.remotecontrol.server.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible returning server information (Version etc.)
 */
@RestController
@RequestMapping("/server")
public class VersionInfoController {

    @RequestMapping("/version")
    @ResponseBody
    int returnServerVersion() {
        // TODO find a way to automate this, or get from build.gradle
        return 102;
    }
}
