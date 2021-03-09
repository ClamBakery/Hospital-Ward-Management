package gui;

import javax.swing.*;

/**
 * The frame for the window to select main menu options
 */
public class CreateMainMenuFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Create the frame for the selection of main menu options
     */
    public CreateMainMenuFrame() {
        setTitle("Ward Menu");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        CreateMainMenuPanel panel = new CreateMainMenuPanel();
        add(panel);
    }
    public static final long serialVersionUID = 1;
}
