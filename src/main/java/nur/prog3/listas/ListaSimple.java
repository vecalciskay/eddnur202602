package nur.prog3.listas;

public class ListaSimple {
    private Vagon primerVagon;

    public ListaSimple() {
        primerVagon = null;
    }

    @Override
    public String toString() {
        if (primerVagon == null) {
            return "[Vacia]";
        }
        StringBuilder sb = new StringBuilder();
        Vagon actual = primerVagon;
        while(actual != null) {
            sb.append(actual.getDato()).append(" --> ");
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }

    public void insertar(Object o) {
        Vagon nuevo = new Vagon(o);
        nuevo.setSiguiente(primerVagon);
        primerVagon = nuevo;
    }
}
