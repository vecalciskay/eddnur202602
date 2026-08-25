package nur.prog3.hanoi.gui;

import nur.prog3.hanoi.objetos.Aro;
import nur.prog3.hanoi.objetos.Columna;
import nur.prog3.hanoi.objetos.Hanoi;

import java.awt.*;

public class DibujadorHanoi {
    private Hanoi modelo;
    public DibujadorHanoi(Hanoi hanoi) {
        modelo = hanoi;
    }

    public void dibujar(Graphics g) {
        Columna col = modelo.getColumna(0);
        dibujarColumna(col, g, 100);
        col = modelo.getColumna(1);
        dibujarColumna(col, g, 300);
        col = modelo.getColumna(2);
        dibujarColumna(col, g, 500);
    }

    private void dibujarColumna(Columna col, Graphics g, int x) {
        g.setColor(Color.green);
        g.fillRect(x,50,10,300);

        int i = 0;
        for (Aro a: col.getAros()) {
            dibujarAro(a, g, x, 350 - i*30);
            i++;
        }
    }

    private void dibujarAro(Aro a, Graphics g, int x, int y) {
        g.setColor(Color.blue);
        g.fillRect(x - 10*a.getTamano(),y,20*a.getTamano(),10);
    }
}
