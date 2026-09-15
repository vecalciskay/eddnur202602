package nur.prog3.listas;

import java.util.Iterator;

public class Lista<E> implements Iterable<E> {

    private Nodo<E> raiz;

    public Lista() {
        raiz = null;
    }

    public void insertar(E o) {
        Nodo<E> nuevo = new Nodo<>(o);
        nuevo.setSiguiente(raiz);
        raiz = nuevo;
    }

    public Iterator<E> iterator() {
        return new IteradorLista<>(raiz);
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

        public Nodo(E o) {
            this.dato = o;
            siguiente = null;
        }

        public E getDato() {
            return dato;
        }

        public void setDato(E dato) {
            this.dato = dato;
        }

        public Nodo<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }
    }


    class IteradorLista<E> implements Iterator<E> {

        private Nodo<E> siguiente;

        public IteradorLista(Nodo<E> primero) {
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
