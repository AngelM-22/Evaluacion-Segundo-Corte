package maajpixup.gui.ventana;

import javax.swing.*;

public class Ventana {
    private static Ventana ventana;

    private Ventana() {
        JFrame frame = new JFrame("PixUp - Ventana");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static Ventana getInstance() {
        if (ventana == null) {
            ventana = new Ventana();
        }
        return ventana;
    }
}
