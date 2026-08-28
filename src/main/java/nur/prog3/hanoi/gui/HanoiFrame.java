package nur.prog3.hanoi.gui;

import nur.prog3.hanoi.objetos.Hanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HanoiFrame extends JFrame  implements PropertyChangeListener {
    private HanoiPanel panel;
    private Hanoi modelo;
    private final int n = 4;

    public HanoiFrame() {
        modelo = new Hanoi(n);
        modelo.addObserver(this);
        modelo.setEsperarDespuesDeMovimiento(true);
        this.panel = new HanoiPanel(modelo);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);

        JButton btn =  new JButton("Hacer");
        btn.addActionListener(e -> btnHacer_click());
        this.getContentPane().add(btn, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    private void btnHacer_click() {
        Runnable worker = () -> modelo.hacerHanoi(0,2,1,n);
        Thread t = new Thread(worker);
        t.start();
    }

    public static void main(String[] args) {
        new HanoiFrame();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(modelo);
    }
}
