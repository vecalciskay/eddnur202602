package nur.prog3.imagenes.deshacer;

import nur.prog3.imagenes.objetos.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Memento {
    private static final Logger logger = LogManager.getRootLogger();
    private Imagen imagen;

    public Memento(Imagen imagen) {
        this.imagen = new Imagen(10,10);
        this.imagen.copiarImagen(imagen);
        logger.info("Imagen copiada, memento creado");
    }

    public Imagen getImagen() {
        return imagen;
    }
}
