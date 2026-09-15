package nur.prog3.listas;

import java.util.Iterator;

public class TetLista {
    public static void main(String[] args) {
        Lista<String> lista = new Lista<>();
        lista.insertar("Hugo");
        lista.insertar("Paco");
        lista.insertar("Luis");
        System.out.println(lista);

        Iterator<String> i = lista.iterator();
        while(i.hasNext()) {
            String obj = i.next();
            System.out.println(obj);
        }

        for (String obj : lista) {
            System.out.println(obj);
        }
    }
}
