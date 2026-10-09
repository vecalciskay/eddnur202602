package nur.prog3.tablashash;

import nur.prog3.listas.Lista;
import nur.prog3.listas.ListaDoble;

public class TablaHashColision {
    private ListaDoble[] tabla;

    public TablaHashColision() {
        tabla = new ListaDoble[1000];
    }

    public void insertar(Object o) {
        // Funcion de hash
        int lugar = funcionHash(o);

        tabla[lugar].insertar(o);
    }

    public Object encontrar(Object o) {
        int dondeEstara = funcionHash(o);
        return tabla[dondeEstara].buscar(o);
    }

    public int funcionHash(Object o) {
        return o.hashCode() % 1000;
    }
}
