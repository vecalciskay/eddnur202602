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
        System.out.println(lista.cantidad());

        String s = lista.buscar("Hugo");
        System.out.println("Encontro: " + s);

        lista.eliminar(1);
        System.out.println(lista);

        Lista<String> lista2 = new Lista<>();
        lista2.adicionar("Hugo");
        lista2.adicionar("Paco");
        lista2.adicionar("Luis");
        System.out.println(lista2);
    }
}
