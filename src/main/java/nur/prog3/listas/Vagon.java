package nur.prog3.listas;

public class Vagon {
    private Object dato;
    private Vagon siguiente;

    public Vagon(Object o) {
        dato = o;
        siguiente = null;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Vagon getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Vagon siguiente) {
        this.siguiente = siguiente;
    }
}
