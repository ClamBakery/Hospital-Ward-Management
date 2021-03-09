package gui;

import containers.DoctorSetAccess;
import entities.Doctor;

import javax.swing.*;

/**
 * The frame for the window to display the information for a doctor, and accept operations on the
 * doctor.
 */
public class DoctorFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 400;

    /**
     * Create the frame to display the information of a doctor
     * @param name the name of the doctor
     * @precond name is the name of a doctor
     */
    public DoctorFrame(String name) {
        Doctor doctor = DoctorSetAccess.dictionary().get(name);
        if (doctor != null) {
            setTitle("Doctor " + doctor.getName());
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            DoctorPanel panel = new DoctorPanel(doctor);
            add(panel);
        }
        else
            throw new RuntimeException("Invalid name");
    }
    public static final long serialVersionUID = 1;
}
