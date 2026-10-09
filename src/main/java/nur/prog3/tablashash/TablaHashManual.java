package nur.prog3.tablashash;

public class TablaHashManual {
    private Object[] tabla;

    public TablaHashManual() {
        tabla = new Object[1000];
    }

    public void insertar(Object o) {
        // Funcion de hash
        int lugar = funcionHash(o);

        tabla[lugar] = o;
    }

    public Object encontrar(Object o) {
        int dondeEstara = funcionHash(o);
        return tabla[dondeEstara];
    }

    public int funcionHash(Object o) {
        return o.hashCode() % 1000;
    }
}
