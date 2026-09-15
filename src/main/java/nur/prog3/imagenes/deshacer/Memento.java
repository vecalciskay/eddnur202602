package nur.prog3.imagenes.deshacer;

import nur.prog3.imagenes.objetos.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Memento {
    private static final Logger logger = LogManager.getRootLogger();
    private Imagen imagen;
    private String descripcion;
    private static int proximoId = 1;
    public Memento(Imagen imagen) {
        this.imagen = new Imagen(10,10);
        this.imagen.copiarImagen(imagen);
        this.descripcion = "Memento de la operacion: " + proximoId;
        proximoId++;
        logger.info("Imagen copiada, memento creado");
    }

    public Imagen getImagen() {
        return imagen;
    }
}
