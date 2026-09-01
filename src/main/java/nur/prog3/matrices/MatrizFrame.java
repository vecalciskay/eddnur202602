package nur.prog3.matrices;

import javax.swing.*;
import java.awt.*;

public class MatrizFrame extends JFrame {
    public MatrizFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MatrizPuntos puntos = new MatrizPuntos(300,300);
        puntos.llenar();

        MatrizPanel panel = new MatrizPanel(puntos);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MatrizFrame();
    }
}
