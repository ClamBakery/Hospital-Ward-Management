package gui;

import commands.AddDoctorCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel to obtain data for the creation of a new doctor and to cause the creation of the doctor
 */
public class DoctorAddPanel extends JPanel {
    /**
     * A text area to be used to display an error if one should occur.
     */
    JTextArea error = null;

    /**
     * A panel for the entry of the name of a new doctor.
     */
    ValueEntryPanel namePanel;

    /**
     * A panel for the entry of whether or not the doctor is a surgeon
     */
    ValueEntryPanel surgeonPanel;

    /**
     * Create the panel to obtain data for the creation of a doctor, and to cause the doctor to be
     * created.
     */
    public DoctorAddPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        // add a label with a prompt to enter the doctor data
        JLabel prompt = new JLabel("Enter Doctor Information");
        prompt.setMaximumSize(prompt.getPreferredSize());
        add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a panel with the field for the entry of the doctor's name
        namePanel = new ValueEntryPanel("name");
        namePanel.setMaximumSize(namePanel.getPreferredSize());
        add(namePanel);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a panel with the field for the entry of whether or not the doctor is a surgeon
        surgeonPanel = new ValueEntryPanel("is surgeon (y/n)");
        surgeonPanel.setMaximumSize(surgeonPanel.getPreferredSize());
        add(surgeonPanel);
        surgeonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a button to submit the information and create the doctor
        JButton submitButton = new JButton("Submit");
        submitButton.setMaximumSize(submitButton.getPreferredSize());
        add(submitButton);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new SubmitListener());
        add(Box.createVerticalGlue());
    }

    /**
     * The class listening for the press of the submit button. It accesses the name entered and whether or not
     * the doctor is a Surgeon, and uses the info to add a new Doctor to the system.
     */
    private class SubmitListener implements ActionListener {
        /**
         * When the submit button is pressed, access the name and whether or not the doctor is a Surgeon entered, and use
         * them to add a new Doctor to the system.
         */
        public void actionPerformed(ActionEvent event) {
            if (error != null) {
                // remove error from the previous submission
                remove(error);
                error = null;
            }
            String name = namePanel.getValueAsString();

            String isSurgeon = surgeonPanel.getValueAsString();

            AddDoctorCommand addDoctor = new AddDoctorCommand();

            if (isSurgeon.charAt(0) == 'y' || isSurgeon.charAt(0) == 'Y')
                addDoctor.addDoctor(name, true);
            else
                addDoctor.addDoctor(name, false);

            if (addDoctor.wasSuccessful()) {
                getTopLevelAncestor().setVisible(false);
            } else {
                error = new JTextArea(SplitString.at(addDoctor.getErrorMessage(), 40));
                error.setMaximumSize(error.getPreferredSize());
                add(error);
                error.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(Box.createVerticalGlue());
                revalidate(); // redraw the window as it has changed
            }
        }
    }
    public static final long serialVersionUID = 1;
}
