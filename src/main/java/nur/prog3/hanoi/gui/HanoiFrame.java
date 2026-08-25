package nur.prog3.hanoi.gui;

import nur.prog3.hanoi.objetos.Hanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HanoiFrame extends JFrame {
    private HanoiPanel panel;
    private Hanoi modelo;

    public HanoiFrame() {
        modelo = new Hanoi(3);
        modelo.setEsperarDespuesDeMovimiento(true);
        this.panel = new HanoiPanel(modelo);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);

        JButton btn =  new JButton("Hacer");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnHacer_click();
            }
        });
        this.getContentPane().add(btn, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    private void btnHacer_click() {
        Runnable worker = new Runnable() {

            @Override
            public void run() {
                modelo.hacerHanoi(0,2,1,3);
            }
        };
        Thread t = new Thread(worker);
        t.start();
    }

    public static void main(String[] args) {
        new HanoiFrame();
    }
}
