package com.gitlab.muhammadkholidb.desktopcreator;

import com.gitlab.muhammadkholidb.desktopcreator.component.MainFrame;
import java.awt.EventQueue;
import java.util.logging.Logger;

/**
 *
 * @author muhammad
 */
public class Application {

    // To read https://askubuntu.com/questions/674403/when-creating-a-desktop-file-what-are-valid-categories
    // To read https://specifications.freedesktop.org/desktop-entry-spec/desktop-entry-spec-latest.html
    // To read https://specifications.freedesktop.org/menu-spec/latest/apa.html
    
    private static final Logger LOG = Logger.getLogger(Application.class.getName());
    
    public static void main(String[] args) {
        LOG.info("Application started ...");
        EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    // Promise, Callback
    // Mutable, immutable
    // Thread safety
    // Async, sync
    // Predicate, specification
    // Repository, dao
    // Optional
}
