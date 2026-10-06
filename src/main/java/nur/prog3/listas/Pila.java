package nur.prog3.listas;

/**
 * Lo que se coloca de ultimo es lo primero que se saca
 * LIFO: Last in First out
 * metodos: push, pop
 */
public class Pila<E> extends ListaDoble<E> {
    public void push(E o) {
        insertar(o);
    }

    public E pop() {
        E o = raiz.getDato();
        eliminar(0);
        return o;
    }
}
