package gui;

import javax.swing.*;

/**
 * The frame for the window to display the operations that involve doctors.
 */
public class DoctorOpsFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Create the frame for the operations that involve doctors.
     */
    public DoctorOpsFrame() {
        setTitle("Doctor Operations");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        DoctorOpsPanel panel = new DoctorOpsPanel();
        add(panel);
    }
    public static final long serialVersionUID = 1;
}
