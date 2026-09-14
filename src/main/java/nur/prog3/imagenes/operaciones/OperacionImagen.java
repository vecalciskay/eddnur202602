package nur.prog3.imagenes.operaciones;

import nur.prog3.imagenes.deshacer.HistorialImagenes;
import nur.prog3.imagenes.objetos.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class OperacionImagen  {
    private static final Logger logger = LogManager.getRootLogger();
    protected Imagen modelo;
    protected String nombre;
    public void ejecutar() {
        logger.info("Ejecutando operacion: " + nombre);
        HistorialImagenes historial = HistorialImagenes.getInstance();
        historial.guardarMemento(modelo);
    }
}
