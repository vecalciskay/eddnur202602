package nur.prog3.listas;

/**
 * Lo que se coloca de primero es lo primero que se saca
 * FIFO: First in First out
 * metodos: push, pull
 */
public class Cola<E> extends ListaDoble<E> {
    public void push(E o) {
        adicionar(o);
    }

    public E pull() {
        E o = raiz.getDato();
        eliminar(0);
        return o;
    }
}
