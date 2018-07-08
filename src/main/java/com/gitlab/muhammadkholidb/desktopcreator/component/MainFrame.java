package com.gitlab.muhammadkholidb.desktopcreator.component;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author muhammad
 */
public class MainFrame extends JFrame {

    private static MainFrame instance;

    private MainFrame() {
        initComponents();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private void initComponents() {

        JButton quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        createLayout(quitButton);

        setTitle("Quit button");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createLayout(JComponent... arg) {

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0};
        gbl.columnWeights = new double[]{1.0};
        gbl.rowHeights = new int[]{0};
        gbl.rowWeights = new double[]{1.0};

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.anchor = GridBagConstraints.FIRST_LINE_START;
        constraint.insets = new Insets(10, 10, 0, 10);
        constraint.gridx = 0;
        constraint.gridy = 0;

        Container pane = getContentPane();
        pane.setLayout(gbl);
        pane.add(arg[0], constraint);
        
    }

}
