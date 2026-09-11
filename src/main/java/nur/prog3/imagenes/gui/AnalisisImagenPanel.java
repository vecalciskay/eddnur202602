package nur.prog3.imagenes.gui;

import nur.prog3.imagenes.objetos.DibujadorImagen;
import nur.prog3.imagenes.objetos.Imagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AnalisisImagenPanel extends JPanel
        implements PropertyChangeListener, MouseMotionListener {
    private final Imagen modelo;

    public AnalisisImagenPanel(Imagen m) {
        this.modelo = m;
        this.modelo.addObserver(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(modelo.getAncho(), modelo.getAlto());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        DibujadorImagen dibujador = new DibujadorImagen(modelo);
        dibujador.dibujar(g);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("IMAGEN")) {
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (x >= modelo.getAncho() || y >= modelo.getAlto()) {
            return;
        }

        modelo.setMensaje("Px(" + x + "," + y + ") = " + modelo.get(x,y));
    }
}
