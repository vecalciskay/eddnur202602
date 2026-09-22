package nur.prog3.listas;

import java.util.Iterator;

public class ListaDoble<E>  implements Iterable<E> {

    private Nodo<E> raiz;
    private Nodo<E> cola;
    private int total;

    @Override
    public Iterator<E> iterator() {
        return new IteradorListaDoble(raiz);
    }

    public void insertar(E o) {
        Nodo<E> nuevo = new Nodo<>(o);
        if (total == 0) {
            raiz = nuevo;
            cola = nuevo;
            total++;
            return;
        }

        nuevo.setSiguiente(raiz);
        raiz.setAnterior(nuevo);
        raiz = nuevo;
        total++;
    }

    public void adicionar(E o) {
        if (total == 0) {
            insertar(o);
            return;
        }

        Nodo<E> nuevo = new Nodo<>(o);
        nuevo.setAnterior(cola);
        cola.setSiguiente(nuevo);
        cola = nuevo;
        total++;
    }

    @Override
    public String toString() {
        if (raiz == null) {
            return "[VACIA]";
        }
        Nodo<E> actual = raiz;
        StringBuilder sb = new StringBuilder();
        while(actual != null) {
            sb.append(actual.getDato()).append("->");
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }

    class Nodo<E> {
        private E dato;
        private Nodo<E> siguiente;
        private Nodo<E> anterior;

        public Nodo(E dato) {
            this.dato = dato;
            siguiente = null;
            anterior = null;
        }

        public Nodo<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }

        public Nodo<E> getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo<E> anterior) {
            this.anterior = anterior;
        }

        public E getDato() {
            return dato;
        }
    }

    class IteradorListaDoble<E> implements Iterator<E> {
        private Nodo<E> siguiente;

        public IteradorListaDoble(Nodo<E> primero) {
            this.siguiente = primero;
        }

        @Override
        public boolean hasNext() {
            return siguiente != null;
        }

        @Override
        public E next() {
            E objeto = siguiente.getDato();
            siguiente = siguiente.getSiguiente();
            return objeto;
        }
    }
}
