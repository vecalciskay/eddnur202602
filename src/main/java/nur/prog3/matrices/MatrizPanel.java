package nur.prog3.matrices;

import javax.swing.*;
import java.awt.*;

public class MatrizPanel extends JPanel {
    private MatrizPuntos modelo;

    public MatrizPanel(MatrizPuntos modelo) {
        this.modelo = modelo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        modelo.dibujar(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,600);
    }
}
