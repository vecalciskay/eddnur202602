package nur.prog3.arboles;

import nur.prog3.listas.ListaDoble;

public class Arbol<E> {

    private Nodo<E> raiz;

    public Arbol() {
        raiz = null;
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }

    public void insertar(String id, E o, String idPadre) {
        Nodo<E> nuevo = new Nodo<>(id, o);
        if (idPadre == null) {
            raiz = nuevo;
            return;
        }
        if (raiz == null) {
            throw new IllegalArgumentException("El arbol esta vacio, no hay id " + idPadre);
        }
        if (raiz.existe(id)) {
            throw new IllegalArgumentException("El id " + id + " ya existe, no pueden haber duplicados");
        }

        Nodo<E> padre = raiz.buscar(idPadre);
        if (padre == null) {
            throw new IllegalArgumentException("NO se encontro el nodo padre " + idPadre);
        }

        padre.getHijos().insertar(nuevo);
    }

    @Override
    public String toString() {
        if (raiz == null)
            return "[VACIO]";
        return raiz.toString();
    }

    public class Nodo<E> {
        private E dato;
        private String id;
        private ListaDoble<Nodo<E>> hijos;

        public Nodo(String id, E o) {
            this.id = id;
            dato = o;
            hijos = new ListaDoble<>();
        }

        public E getDato() {
            return dato;
        }

        public String getId() {
            return id;
        }

        public ListaDoble<Nodo<E>> getHijos() {
            return hijos;
        }

        public Nodo<E> buscar(String id) {
            if (this.id.equals(id)) {
                return this;
            }
            Nodo<E> encontrado = null;
            for (Nodo<E> hijo: hijos ) {
                encontrado = hijo.buscar(id);
                if (encontrado != null)
                    return encontrado;
            }
            return null;
        }

        @Override
        public String toString() {
            if (hijos.cantidad() == 0) {
                return this.dato.toString();
            }
            StringBuilder sb = new StringBuilder();
            String separador = "";
            sb.append(dato).append(" -> (");
            for (Nodo<E> hijo: hijos ) {
                sb.append(separador).append(hijo);
                separador = ",";
            }
            sb.append(")");
            return sb.toString();
        }

        public boolean existe(String id) {
            if (this.id.equals(id)) {
                return true;
            }
            Nodo<E> encontrado = null;
            for (Nodo<E> hijo: hijos ) {
                encontrado = hijo.buscar(id);
                if (encontrado != null)
                    return true;
            }
            return false;
        }
    }
}
