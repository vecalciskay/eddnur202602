package nur.prog3.imagenes.gui;

import nur.prog3.imagenes.objetos.DibujadorImagen;
import nur.prog3.imagenes.objetos.Imagen;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AnalisisImagenPanel extends JPanel implements PropertyChangeListener {
    private final Imagen modelo;

    public AnalisisImagenPanel(Imagen m) {
        this.modelo = m;
        this.modelo.addObserver(this);
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
        repaint();
    }
}
