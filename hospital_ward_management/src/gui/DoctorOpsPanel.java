package gui;

import containers.DoctorSetAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel for the operations involving doctors. There is a button to add a new doctor, a field
 * to access a specific doctor, a button to list all doctors, and an exit button to hide the
 * window with this frame.
 */
public class DoctorOpsPanel extends JPanel {
    /**
     * Create the panel for the operations involving doctors. There is a button to add a new doctor, a field
     * to access a specific doctor, a button to list all doctors, and an exit button to hide the
     * window with this frame.
     */
    public DoctorOpsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        // add a button to add a new doctor
        JButton addButton = new JButton("Add doctor");
        addButton.setMaximumSize(addButton.getPreferredSize());
        add(addButton);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                DoctorAddFrame frame = new DoctorAddFrame();
                frame.setLocation(300, 300);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
            }
        });

        // add a panel with a field to access a specific doctor
        DoctorAccessPanel accessPanel = new DoctorAccessPanel();
        add(accessPanel);
        add(Box.createVerticalGlue());

        // add a button to display all the doctors
        JButton listAllButton = new JButton("List all");
        listAllButton.setMaximumSize(listAllButton.getPreferredSize());
        add(listAllButton);
        listAllButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        listAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(null, DoctorSetAccess.dictionary().values());
            }
        });
        add(Box.createVerticalGlue());

        // add a button to exit from the window containing this panel
        final JButton exitButton = new JButton("Exit");
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        add(exitButton);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exitButton.getTopLevelAncestor().setVisible(false);
            }
        });
        add(Box.createVerticalGlue());
    }
    public static final long serialVersionUID = 1;
}
