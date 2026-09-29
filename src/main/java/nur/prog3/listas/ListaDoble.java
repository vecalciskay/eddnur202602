package nur.prog3.listas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class ListaDoble<E>  implements Iterable<E> {
    private static final Logger logger = LogManager.getRootLogger();
    protected Nodo<E> raiz;
    protected Nodo<E> cola;
    protected int total;

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


    /**
     * Busca desde el principio y el final al mismo tiempo
     * @param target
     * @return
     */
    public E buscar(E target) {
        if (total == 1) {
            for(E o : this) {
                if (o.equals(target)) {
                    return o;
                }
            }
            return null;
        }
        Nodo<E> actualInicio = raiz;
        Nodo<E> actualFinal = cola;

        int mitad = total / 2;
        int actualPos = 1;

        while(actualPos > mitad) {
            if (actualInicio.getDato().equals(target))
                return actualInicio.getDato();
            if (actualFinal.getDato().equals(target))
                return actualFinal.getDato();
            actualInicio = actualInicio.getSiguiente();
            actualFinal = actualFinal.getAnterior();
            actualPos++;
        }
        return null;
    }

    /**
     *
     * @param pos 0 indica la primera posicion
     */
    public void eliminar(int pos) {
        if (total == 0)
            throw new ArrayIndexOutOfBoundsException("Lista vacia");
        if (total < (pos + 1))
            throw new ArrayIndexOutOfBoundsException("Posicion esta mas alla del ultimo elemento");
        if (pos < 0)
            throw new ArrayIndexOutOfBoundsException("No acepta posiciones negativas");

        int mitad = total / 2;

        if (pos == 0) {
            raiz =  raiz.getSiguiente();
            if (raiz != null)
                raiz.setAnterior(null);
            else
                cola = null;
            total--;
            return;
        }

        if (pos < mitad) {
            logger.info("Vaos desde la raiz");
            int posActual = 0;
            Nodo<E> actual = raiz;
            while (posActual < (pos - 1)) {
                actual = actual.getSiguiente();
                posActual++;
            }

            if (actual.getSiguiente().getSiguiente() != null)
                actual.getSiguiente().getSiguiente().setAnterior(actual);

            actual.setSiguiente(actual.getSiguiente().getSiguiente());
            total--;
        } else {
            logger.info("Vaos desde la cola");
            int posActual = total-1;
            Nodo<E> actual = cola;
            while (posActual > (pos + 1)) {
                actual = actual.getAnterior();
                posActual--;
            }

            if (actual.getAnterior().getAnterior() != null)
                actual.getAnterior().getAnterior().setSiguiente(actual);

            actual.setAnterior(actual.getAnterior().getAnterior());
            total--;
        }
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

    static class Nodo<E> {
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
