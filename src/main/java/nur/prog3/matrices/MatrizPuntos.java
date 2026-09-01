package nur.prog3.matrices;

import java.awt.*;

public class MatrizPuntos {
    private int ancho;
    private int alto;
    private int[][] puntos;

    public MatrizPuntos(int w, int h) {
        puntos = new int[w][h];
        ancho = w;
        alto = h;
    }

    public void llenar() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                puntos[i][j] = 2199480;
            }
        }
    }

    public void dibujar(Graphics g) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                g.setColor(new Color(puntos[i][j]));
                g.drawLine(i,j,i,j);
            }
        }
    }
}
