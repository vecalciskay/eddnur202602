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

        Nodo<E> nuevo = new Nodo<>(o);
        // El siguiente de nuevo debe ir al siguiente de actual
        nuevo.setSiguiente(actual.getSiguiente());
        // El anterior del siguiente de actul apunta a nuevo (solametne si el sgte de actual no es nulo)
        if (actual.getSiguiente() != null)
            actual.getSiguiente().setAnterior(nuevo);
        // El anterior de nuevo apunta a actual
        nuevo.setAnterior(actual);
        // El siguiente de actual apunta a nuevo
        actual.setSiguiente(nuevo);

        total++;
    }
}
