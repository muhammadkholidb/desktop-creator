package com.gitlab.muhammadkholidb.desktopcreator.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author muhammad
 */
public class MainFrame extends JFrame {

    private final static Logger LOG = Logger.getLogger(MainFrame.class.getName());
    
    private final JFileChooser fileChooser = new JFileChooser();
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == btnChooseIcon) {
                onClickBtnChooseIcon();
            } else if (source == btnChooseExecutable) {
                onClickBtnChooseExecutable();
            } else if (source == btnCreate) {
                onClickBtnCreate();
            } else if (source == btnExit) {
                LOG.info("Exiting ...");
                System.exit(0);
            }
        }
    };
    
    public MainFrame() {
        if (!isUnix()) {
            LOG.severe("OS is not Linux");
            JOptionPane.showMessageDialog(null, "This application is intended for use on Linux OS only.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return;
        }
        initComponents();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        btnChooseExecutable.addActionListener(actionListener);
        btnChooseIcon.addActionListener(actionListener);
        btnExit.addActionListener(actionListener);
        btnCreate.addActionListener(actionListener);
    }

    public static boolean isUnix() {
        String os = System.getProperty("os.name");
        return (os.contains("nix") || os.contains("nux") || os.indexOf("aix") > 0);
    }

    private void onClickBtnChooseIcon() {
        int val = fileChooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            tfIconLocation.setText(file.getAbsolutePath());
        }
    }
    
    private void onClickBtnChooseExecutable() {
        int val = fileChooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            tfExecutableLocation.setText(file.getAbsolutePath());
        }
    }
    
    private void onClickBtnCreate() {
        String valName = tfName.getText();
        String valDescription = tfDescription.getText();
        String valExecutable = tfExecutableLocation.getText();
        String valIcon = tfIconLocation.getText();
        if (validateInputs(valName, valExecutable)) {
            try {
                String desktopFileName = valName.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
                String desktopFileLocation = "/usr/share/applications/" + desktopFileName + ".desktop";
                File defaultIconFile = new File("/usr/share/icons/desktop-creator/default-icon.png");
                if (valIcon.isEmpty()) {
                    if (!defaultIconFile.exists()) {
                        InputStream isDefaultIcon = MainFrame.class.getResourceAsStream("/default-icon.png");
                        String defaultIconLocation = "/usr/share/icons/desktop-creator/default-icon.png";
                        FileUtils.copyInputStreamToFile(isDefaultIcon, new File(defaultIconLocation));
                        LOG.log(Level.INFO, "Default icon copied to {0}", defaultIconLocation);
                    }
                    LOG.info("Using default icon");
                    valIcon = defaultIconFile.getAbsolutePath();
                }
                InputStream isContent = MainFrame.class.getResourceAsStream("/desktop-content.txt");
                String desktopFileContent = IOUtils.toString(isContent);
                String desktopFileContentFormatted = String.format(desktopFileContent, valName, valDescription, valExecutable, valIcon);
                FileUtils.writeStringToFile(new File(desktopFileLocation), desktopFileContentFormatted);
                LOG.log(Level.INFO, "File {0} created successfully", desktopFileLocation);
                JOptionPane.showMessageDialog(this, "Desktop file created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearInputs();
            } catch (IOException e) {
                LOG.log(Level.SEVERE, e.toString(), e);
                JOptionPane.showMessageDialog(this, "Failed to create desktop file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean validateInputs(String name, String executable) {
        if (name.isEmpty()) {
            LOG.severe("Name is empty");
            JOptionPane.showMessageDialog(this, "Please fill in application name.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        if (executable.isEmpty()) {
            LOG.severe("Executable location is empty");
            JOptionPane.showMessageDialog(this, "Please fill in executable file location.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void clearInputs() {
        tfName.setText("");
        tfDescription.setText("");
        tfExecutableLocation.setText("");
        tfIconLocation.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfExecutableLocation = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfIconLocation = new javax.swing.JTextField();
        btnChooseExecutable = new javax.swing.JButton();
        btnChooseIcon = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfDescription = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Executable Location :");

        jLabel2.setText("Icon Location :");

        btnChooseExecutable.setText("Choose ...");

        btnChooseIcon.setText("Choose ...");

        btnExit.setText("Exit");
        btnExit.setToolTipText("");

        btnCreate.setText("Create");

        jLabel3.setText("Description :");

        jLabel4.setText("Name :");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel5.setText("DESKTOP FILE CREATOR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfName)
                    .addComponent(tfExecutableLocation, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfIconLocation, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfDescription, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChooseExecutable)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnChooseIcon)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 422, Short.MAX_VALUE)
                        .addComponent(btnCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(tfExecutableLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChooseExecutable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(tfIconLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChooseIcon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(btnCreate))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseExecutable;
    private javax.swing.JButton btnChooseIcon;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField tfDescription;
    private javax.swing.JTextField tfExecutableLocation;
    private javax.swing.JTextField tfIconLocation;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables

}
