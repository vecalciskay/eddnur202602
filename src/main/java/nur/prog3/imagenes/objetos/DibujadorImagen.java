package nur.prog3.imagenes.objetos;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DibujadorImagen {
    private final Imagen modelo;

    public DibujadorImagen(Imagen modelo) {
        this.modelo = modelo;
    }

    public void dibujar(Graphics g) {
        BufferedImage imagen = new BufferedImage(modelo.getAncho(),
                modelo.getAlto(), BufferedImage.TYPE_INT_RGB);
        Graphics buffer = imagen.getGraphics();
        for (int i = 0; i < modelo.getAncho(); i++) {
            for (int j = 0; j < modelo.getAlto(); j++) {
                buffer.setColor(new Color(modelo.get(i,j)));
                buffer.drawLine(i,j,i,j);
            }
        }

        g.drawImage(imagen, 0, 0, null);
    }
}
