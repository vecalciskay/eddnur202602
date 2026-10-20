package nur.prog3.arboles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelArbol extends JPanel implements MouseListener {
    private Arbol<String> modelo;
    public PanelArbol(Arbol<String> m) {

        modelo = m;
        addMouseListener(this);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        String nombre = modelo.getObjetoPosicion(x, y);
        if (nombre != null) {
            JOptionPane.showMessageDialog(this, nombre);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
