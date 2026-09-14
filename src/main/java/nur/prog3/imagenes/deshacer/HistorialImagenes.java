package nur.prog3.imagenes.deshacer;

import nur.prog3.imagenes.objetos.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Stack;

public class HistorialImagenes {
    private static final Logger logger = LogManager.getRootLogger();
    private Stack<Memento> listaUndo;
    private Stack<Memento> listaRedo;
    private static HistorialImagenes instancia;

    public static HistorialImagenes getInstance() {
        if (instancia == null) {
            instancia = new HistorialImagenes();
        }
        return instancia;
    }

    private HistorialImagenes(){
        listaUndo = new Stack<Memento>();
        listaRedo = new Stack<Memento>();
    }

    public void guardarMemento(Imagen src) {
        listaUndo.push(new Memento(src));
        logger.info("Coloca memento en la lista undo");
    }

    public void restaurarMementoEnImagen(Memento memento, Imagen imagen) {
        imagen.copiarImagen(memento.getImagen());
    }

    public void undo(Imagen imagen) {
        if (listaUndo.isEmpty()) {
            logger.warn("La lista de undo esta vacia");
            return;
        }
        Memento memento = listaUndo.pop();
        listaRedo.add(memento);
        imagen.copiarImagen(memento.getImagen());
    }

    public void redo(Imagen imagen) {
        if (listaRedo.isEmpty()) {
            logger.warn("La lista redo esta vacia");
            return;
        }
        Memento memento = listaRedo.pop();
        listaUndo.add(memento);
        imagen.copiarImagen(memento.getImagen());
    }
}
