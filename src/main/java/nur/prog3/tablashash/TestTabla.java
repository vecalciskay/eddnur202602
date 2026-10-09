package nur.prog3.tablashash;

import nur.prog3.listas.Perro;

public class TestTabla {
    public static void main(String[] args) {
        Perro p = new Perro("PP001","Pepe","caniche");
        TablaHashManual tabla = new TablaHashManual();
        tabla.insertar(p);

        Perro otro = (Perro)tabla.encontrar(p);

        if (otro == p) {
            System.out.println("Encontramos el correcto");
        } else {
            System.out.println("Pasa algo raro");
        }
    }
}
