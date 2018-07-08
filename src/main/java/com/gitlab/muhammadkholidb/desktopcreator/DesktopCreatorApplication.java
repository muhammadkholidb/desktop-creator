package com.gitlab.muhammadkholidb.desktopcreator;

import com.gitlab.muhammadkholidb.desktopcreator.component.Forms;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author muhammad
 */
@SpringBootApplication
public class DesktopCreatorApplication {

    // Example from http://zetcode.com/articles/springbootswing/
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(DesktopCreatorApplication.class)
                .headless(false)
                .web(false)
                .run(args);
        DesktopCreatorApplication app = context.getBean(DesktopCreatorApplication.class);
        DesktopApplicationContext.main(Forms.class, args);
    }

}
