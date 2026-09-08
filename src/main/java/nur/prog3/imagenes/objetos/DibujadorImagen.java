package nur.prog3.imagenes.objetos;

import java.awt.*;

public class DibujadorImagen {
    private final Imagen modelo;

    public DibujadorImagen(Imagen modelo) {
        this.modelo = modelo;
    }

    public void dibujar(Graphics g) {
        for (int i = 0; i < modelo.getAncho(); i++) {
            for (int j = 0; j < modelo.getAlto(); j++) {
                g.setColor(new Color(modelo.get(i,j)));
                g.drawLine(i,j,i,j);
            }
        }
    }
}
