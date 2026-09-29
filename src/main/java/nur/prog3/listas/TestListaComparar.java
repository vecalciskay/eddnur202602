package nur.prog3.listas;

import java.util.ArrayList;

public class TestListaComparar {
    public static void main(String[] args) {
        ArrayList<Perro> lista = new ArrayList<>();
        lista.add(new Perro("O1","Boki","Labrador"));
        lista.add(new Perro("AD4","Puka","Border collie"));
        lista.add(new Perro("T67","Chicho","Chihuahua"));

        lista.add(new Perro("F2267","Mula","Gran Danes"));
        lista.add(new Perro("Z887","Toro","Doberman"));

        System.out.println(lista);
    }
}
