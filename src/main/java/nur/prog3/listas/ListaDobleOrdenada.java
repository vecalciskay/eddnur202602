package nur.prog3.listas;

public class ListaDobleOrdenada<E> extends ListaDoble<E> {

    public ListaDobleOrdenada() {
        super();
    }

    public void insertar(E o) {
        if (!(o instanceof Comparable)) {
            super.insertar(o);
            return;
        }

        if (total == 0) {
            super.insertar(o);
            return;
        }

        Comparable c = (Comparable)o;
        if (c.compareTo(raiz.getDato()) < 0) {
            super.insertar(o);
            return;
        }

        Nodo<E> actual = raiz;
        while(actual.getSiguiente() != null &&
                c.compareTo(actual.getSiguiente().getDato()) > 0) {
            actual = actual.getSiguiente();
        }

        
    }
}
