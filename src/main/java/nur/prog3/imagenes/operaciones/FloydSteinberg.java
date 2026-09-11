package nur.prog3.imagenes.operaciones;

import nur.prog3.imagenes.objetos.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FloydSteinberg extends OperacionImagen {
    private static final Logger logger = LogManager.getRootLogger();
    public FloydSteinberg(Imagen m) {
        this.modelo = m;
    }
    @Override
    public void ejecutar() {
        int[][] grises = new int[modelo.getAncho()+1][modelo.getAlto()+1];
        for (int i = 0; i < modelo.getAncho(); i++) {
            for (int j = 0; j < modelo.getAlto(); j++) {
                int[] color = modelo.getRgb(i,j);
                int promedio = (color[0] + color[1] + color[2])/3;
                grises[i][j] = promedio;
            }
        }

        // Difusion de error
        // PAra un pixel en i.j tenemos un valor, por ejemplo 97
        // Como es menor que 127 entonces ese pixel va a ser negro, o sea 0
        // La diferencia entre el valor que le colocamos (0) y su valor real (97)
        // le he QUITADO 97 tonos de color
        // Entonces los 97 los tengo que repartir en los pixeles adyacentes
        // 7/16 al de la derecha  i+1,j = lo que había en ese pixel + (97 * 7 / 16)
        // 3/16 al de abajo izq  i-1,j+1 = lo que había en ese pixel + (97 * 3 / 16)
        // 5/16 al de abajo  i,j+1 = lo que había en ese pixel + (97 * 5 / 16)
        // 1/16 al de abajo der  i+1,j+1 = lo que había en ese pixel + (97 * 1 / 16)
        for (int i = 0; i < modelo.getAncho(); i++) {
            for (int j = 0; j < modelo.getAlto(); j++) {
                int error = 0;
                if (grises[i][j] < 127) {
                    error = grises[i][j];
                    grises[i][j] = 0;
                } else {
                    error = grises[i][j] - 255;
                    grises[i][j] = 255;
                }
                grises[i+1][j] = grises[i+1][j] + (int)(error * 7.0 / 16.0);
                if (i > 0)
                    grises[i-1][j+1] = grises[i-1][j+1] + (int)(error * 3.0 / 16.0);
                grises[i][j+1] = grises[i][j+1] + (int)(error * 5.0 / 16.0);
                grises[i+1][j+1] = grises[i+1][j+1] + (int)(error * 1.0 / 16.0);
            }
        }

        for (int i = 0; i < modelo.getAncho(); i++) {
            for (int j = 0; j < modelo.getAlto(); j++) {
                modelo.set(i,j,grises[i][j], grises[i][j], grises[i][j]);
            }
        }

        logger.info("Operacion dithering Floyd Steinberg realizada");
        modelo.notificarCambios();
    }
}
