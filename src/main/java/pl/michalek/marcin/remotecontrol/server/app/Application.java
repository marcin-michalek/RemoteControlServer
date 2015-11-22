package pl.michalek.marcin.remotecontrol.server.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.michalek.marcin.remotecontrol.server.ui.MainFrame;
import pl.michalek.marcin.remotecontrol.server.utils.TrayIconUtil;

/**
 * Main Application class.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        TrayIconUtil.initTrayIcon("/rect_yellow.png");
        new SpringApplicationBuilder(Application.class)
                .headless(false)
                .run(args);

        TrayIconUtil.setFrame(frame());
        TrayIconUtil.setTrayIconImage("/rect_green.png");
    }

    @Bean
    public static MainFrame frame() {
        return new MainFrame();
    }
}
