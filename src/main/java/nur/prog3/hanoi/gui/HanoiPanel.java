package nur.prog3.hanoi.gui;

import nur.prog3.hanoi.objetos.Hanoi;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HanoiPanel extends JPanel implements PropertyChangeListener {

    private Hanoi hanoi;

    public HanoiPanel(Hanoi m) {
        this.hanoi = m;
        hanoi.addObserver(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        DibujadorHanoi d = new DibujadorHanoi(hanoi);
        d.dibujar(g);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
