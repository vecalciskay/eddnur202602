package nur.prog3.imagenes.operaciones;

import nur.prog3.imagenes.objetos.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConvertirBN extends OperacionImagen {
    private static final Logger logger = LogManager.getRootLogger();
    public ConvertirBN(Imagen modelo) {
        this.modelo = modelo;
    }

    @Override
    public void ejecutar() {
        logger.info("CAmbiando iagen a BN");
        for (int i = 0; i < modelo.getAncho(); i++) {
            for (int j = 0; j < modelo.getAlto(); j++) {
                int[] rgb = modelo.getRgb(i,j);
                int promedio = (rgb[0] + rgb[1] + rgb[2]) / 3;
                if (promedio > 50)
                    modelo.set(i, j, 255, 255, 255);
                else
                    modelo.set(i,j,0,0,0);
            }
        }
        modelo.notificarCambios();
    }
}
