package com.gitlab.muhammadkholidb.desktopcreator;

import com.gitlab.muhammadkholidb.desktopcreator.example.DesktopCreatorFrame;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ovo
 */
public class Application extends JFrame implements ActionListener {
    
    private JTextField tfIcon;
    private JTextField tfExecutable;
    private JButton btnChooseIcon;
    private JButton btnChooseExecutable;
    private JButton btnExit;
    private JButton btnCreate;
    private JFileChooser fileChooser;
    
    public Application() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Desktop Creator");
        setSize(500, 200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        
        tfIcon = new JTextField();
        tfExecutable = new JTextField();
        btnChooseIcon = new JButton("Choose ...");
        btnChooseExecutable = new JButton("Choose ...");
        btnExit = new JButton("Exit");
        btnCreate = new JButton("Create");
        fileChooser = new JFileChooser(System.getProperty("user.home"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        btnChooseIcon.addActionListener(this);
        btnChooseExecutable.addActionListener(this);
        btnExit.addActionListener(this);
        btnCreate.addActionListener(this);
        
        GridBagConstraints constraints = new GridBagConstraints();
	constraints.fill = GridBagConstraints.HORIZONTAL;
	constraints.weightx = 0.5;
	constraints.gridx = 0;
	constraints.gridy = 0;
        pane.add(new JLabel("Executable location", JLabel.RIGHT), constraints);
        
	constraints.fill = GridBagConstraints.HORIZONTAL;
	constraints.weightx = 0.5;
	constraints.gridx = 1;
	constraints.gridy = 0;
        pane.add(tfExecutable, constraints);
        
	constraints.fill = GridBagConstraints.HORIZONTAL;
	constraints.weightx = 0.5;
	constraints.gridx = 2;
	constraints.gridy = 0;
        pane.add(btnChooseExecutable, constraints);
        
	constraints.fill = GridBagConstraints.HORIZONTAL;
	constraints.weightx = 0.5;
	constraints.gridx = 0;
	constraints.gridy = 1;
        pane.add(new JLabel("Icon location", JLabel.RIGHT), constraints);
        
	constraints.fill = GridBagConstraints.HORIZONTAL;
	constraints.weightx = 0.5;
	constraints.gridx = 1;
	constraints.gridy = 1;
        pane.add(tfIcon, constraints);
        
	constraints.fill = GridBagConstraints.HORIZONTAL;
	constraints.weightx = 0.5;
	constraints.gridx = 2;
	constraints.gridy = 1;
        pane.add(btnChooseIcon, constraints);
        
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(btnCreate);
        panel.add(btnExit);
        
	constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 2;
        pane.add(panel, constraints);
        
    }
    
    // Example from http://zetcode.com/articles/springbootswing/
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new DesktopCreatorFrame().setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object sourceObject = e.getSource();
        if (btnExit == sourceObject) {
            System.out.println("Exiting ...");
            System.exit(0);
        } else if (btnCreate == sourceObject) {
            System.out.println("Create .desktop file ...");
        } else if (btnChooseIcon == sourceObject) {
            System.out.println("Open dialog choose icon ...");
            int val = fileChooser.showOpenDialog(this);
            System.out.println("Return val: " + val);
            if (JFileChooser.APPROVE_OPTION == val) {
                File selected = fileChooser.getSelectedFile();
                System.out.println("Icon location: " + selected.getAbsolutePath());
            }
        } else if (btnChooseExecutable == sourceObject) {
            System.out.println("Open dialog choose executable ...");
            int val = fileChooser.showOpenDialog(this);
            System.out.println("Return val: " + val);
            if (JFileChooser.APPROVE_OPTION == val) {
                File selected = fileChooser.getSelectedFile();
                System.out.println("Executable location: " + selected.getAbsolutePath());
            }
        }
    }

    // Promise, Callback
    // Mutable, immutable
    // Thread safety
    // Async, sync
    // Predicate, specification
    // Repository, dao
    // Optional
    
}
