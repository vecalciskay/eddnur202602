package nur.prog3.arboles;

import javax.swing.*;
import java.awt.*;

public class PanelArbol extends JPanel {
    private Arbol<String> modelo;
    public PanelArbol(Arbol<String> m) {
        modelo = m;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (modelo == null)
            return;
        DibujoArbol dibujador = new DibujoArbol(modelo);
        dibujador.dibujar(g, 10,10);
    }
}
