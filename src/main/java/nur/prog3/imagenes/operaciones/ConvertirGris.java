package nur.prog3.imagenes.operaciones;

import nur.prog3.imagenes.objetos.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConvertirGris extends OperacionImagen {
    private static final Logger logger = LogManager.getRootLogger();
    public ConvertirGris(Imagen modelo) {
        this.modelo = modelo;
    }

    @Override
    public void ejecutar() {
        logger.info("CAmbiando iagen a Grises");
        for (int i = 0; i < modelo.getAncho(); i++) {
            for (int j = 0; j < modelo.getAlto(); j++) {
                int[] rgb = modelo.getRgb(i, j);
                //Px (1184,216): 17 17 16
                int promedio = (rgb[0] + rgb[1] + rgb[2]) / 3;
                modelo.set(i, j, promedio, promedio, promedio);
            }
        }
        logger.info("Operacion tonos de gris realizada");
        modelo.notificarCambios();
    }
}