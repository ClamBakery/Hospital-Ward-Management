package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel for the creation of the Main menu. Contains 4 buttons: the Patient Operations button
 * which when clicked opens the patient operations window, the Doctor Operations button which when
 * clicked opens the doctor operations window, the Ward Info button which when clicked displays the
 * ward's current state, and a Exit button which closes the program.
 */
public class CreateMainMenuPanel extends JPanel{

    /**
     * Create the panel for the operations for the Main menu. There is a button to start patient operations,
     * a button to start doctor operations, a button to display the ward and a exit button
     */
    public CreateMainMenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        JButton patientButton = new JButton("Patient Operations");
        patientButton.setMaximumSize(patientButton.getPreferredSize());
        add(patientButton);
        patientButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        patientButton.addActionListener(new PatientListener());
        add(Box.createHorizontalGlue());

        JButton doctorsButton = new JButton("Doctor Operations");
        doctorsButton.setMaximumSize(doctorsButton.getPreferredSize());
        add(doctorsButton);
        doctorsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        doctorsButton.addActionListener(new DoctorListener());
        add(Box.createHorizontalGlue());

        JButton wardButton = new JButton("Ward Info");
        wardButton.setMaximumSize(wardButton.getPreferredSize());
        add(wardButton);
        wardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        wardButton.addActionListener(new WardListener());
        add(Box.createHorizontalGlue());

        final JButton exitButton = new JButton("Exit");
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        add(exitButton);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        add(Box.createVerticalGlue());
    }

    /**
     * Listener for the Patient Operations button
     */
    private class PatientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PatientOpsFrame frame = new PatientOpsFrame(); // Used for assign5 */
            frame.setLocation(300, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }

    /**
     * Listener for the Doctor Operations button
     */
    private class DoctorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DoctorOpsFrame frame = new DoctorOpsFrame();
            frame.setLocation(300,300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }

    /**
     * Listener for the Ward Info button
     */
    private class WardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            WardFrame frame = new WardFrame();
            frame.setLocation(300,300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }
    public static final long serialVersionUID = 1;
}
