package gui;

import commands.AddPatientCommand;
import commands.AssignDoctorCommand;
import commands.DropAssocCommand;
import entities.Doctor;
import entities.Patient;
import entities.Surgeon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel to display the information for a doctor and accept operations on the doctor. The
 * panel gives the doctor's name and whether or not they are a surgeon. It has a option to add a
 * patient to the doctor's patient list and a option to remove a patient. Also a option to search
 * for a specific patient assigned to the doctor using their health number which opens the patient's
 * patient window.
 */
public class DoctorPanel extends JPanel {

    /**
     * Create the panel to display the doctor's information and accept operations on the doctor.
     * @param doctor the doctor whose info is displayed and on which operations can be done
     */
    public DoctorPanel(Doctor doctor) {
        build(doctor);
    }

    /**
     * Fill in the panel to display the doctor's information and accept operations on the doctor.
     *
     * @param doctor the doctor whose information is to be displayed and on whom operations can be
     *        done
     */
    private void build(Doctor doctor) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel("Name: " + doctor.getName()));
        if (doctor.getClass() == Surgeon.class) {
            add(new JLabel("Is a Surgeon\n"));
        }

        add(new JLabel("  ")); // blank line in the panel for spacing
        JPanel accessPatientPanel = accessPatientPanel(doctor);
        add(accessPatientPanel);
        accessPatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(new JLabel("  ")); // blank line in the panel for spacing
        JPanel addPatientPanel = addPatientPanel(doctor);
        add(addPatientPanel);
        addPatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(new JLabel("  ")); // blank line in the panel for spacing
        JPanel removePatientPanel = removePatientPanel(doctor);
        add(removePatientPanel);
        removePatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JButton exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exitButton.getTopLevelAncestor().setVisible(false);
            }
        });
    }

    /**
     * A panel to access a specific patient of a doctor by searching with their health number.
     * When a doctor has the patient with the inputted health number it opens that patient's Patient
     * frame with all the operations you can do on that patient.
     *
     * @param doctor The doctor who's info is to be displayed and on whom operations can be conducted
     * @return the panel to access a patient of the doctor
     */
    private JPanel accessPatientPanel(Doctor doctor) {
        JPanel accessPatientPanel = new JPanel();
        JLabel promptLabel = new JLabel("Access patient");
        accessPatientPanel.add(promptLabel);
        final JTextField textField = new JTextField(10);
        accessPatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String valueAsString = textField.getText();
                int healthNum = -1;
                if (valueAsString != null && valueAsString.length() > 0) {
                    try {
                        healthNum = Integer.parseInt(valueAsString);
                    } catch (NumberFormatException e) {
                        textField.setText("Not int: " + textField.getText());
                        textField.revalidate();
                        return;
                    }
                    if (doctor.hasPatient(healthNum)) {
                        PatientFrame frame = null;
                        try {
                            frame = new PatientFrame(healthNum);
                        } catch (RuntimeException e) {
                            textField.setText("Invalid id: " + textField.getText());
                            textField.revalidate();
                            return;
                        }
                        frame.setLocation(300, 300);
                        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        frame.setVisible(true);
                        textField.setText("");
                        textField.revalidate();
                    }
                    else {
                        JOptionPane.showMessageDialog(DoctorPanel.this, "This patient is not assigned to this doctor");
                    }
                } else {
                    textField.setText("Empty field: " + textField.getText());
                    textField.revalidate();
                }
            }
        });
        return accessPatientPanel;
    }

    /**
     * A panel that allows the assignment of a patient to the doctor via the patient's
     * health number.
     *
     * @param doctor The doctor who's info is to be displayed and on whom operations can be conducted
     * @return the panel that allows assignment of patients to the doctor
     */
    private JPanel addPatientPanel(Doctor doctor) {
        JPanel addPatientPanel = new JPanel();
        addPatientPanel.add(new JLabel("Add patient"));
        final JTextField textField = new JTextField(10);
        addPatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String valueAsString = textField.getText();
                int healthNum = -1;
                if (valueAsString != null && valueAsString.length() > 0) {
                    try {
                        healthNum = Integer.parseInt(valueAsString);
                    } catch (NumberFormatException ex) {
                        textField.setText("Not int: " + textField.getText());
                        textField.revalidate();
                        return;
                    }

                    AssignDoctorCommand addPatient = new AssignDoctorCommand();
                    addPatient.assignDoctor(doctor.getName(), healthNum);

                    if (!addPatient.wasSuccessful()) {
                        JOptionPane.showMessageDialog(DoctorPanel.this, addPatient.getErrorMessage());
                    }
                    else {
                        JOptionPane.showMessageDialog(DoctorPanel.this,"Patient with health number " + healthNum + " successfully assigned");
                    }
                }
            }
        });
        return addPatientPanel;
    }

    /**
     * A panel that allows the removal of a specific patient from a doctor if that patient
     * is currently assigned to the doctor.
     *
     * @param doctor The doctor who's info is to be displayed and on whom operations can be conducted
     * @return the panel that allows the removal of a patient from the doctor
     */
    private JPanel removePatientPanel(Doctor doctor) {
        JPanel remDoctorPanel = new JPanel();
        final JTextField textField = new JTextField(10);
        remDoctorPanel.add(new JLabel("Remove patient"));
        remDoctorPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String valueAsString = textField.getText();
                int healthNum = -1;
                if (valueAsString != null && valueAsString.length() > 0) {
                    try {
                        healthNum = Integer.parseInt(valueAsString);
                    } catch (NumberFormatException ex) {
                        textField.setText("Not int: " + textField.getText());
                        textField.revalidate();
                        return;
                    }
                    DropAssocCommand dropAssoc = new DropAssocCommand();
                    dropAssoc.dropAssociation(doctor.getName(), healthNum);
                    if (dropAssoc.wasSuccessful()) {
                        JOptionPane.showMessageDialog(DoctorPanel.this, "Patient " + healthNum + " successfully removed");
                    }
                    else {
                        JOptionPane.showMessageDialog(DoctorPanel.this, dropAssoc.getErrorMessage());
                    }
                }
                else {
                    textField.setText("Empty field: " + textField.getText());
                    textField.revalidate();
                }
            }
        });
        return remDoctorPanel;
    }
    public static final long serialVersionUID = 1;
}
