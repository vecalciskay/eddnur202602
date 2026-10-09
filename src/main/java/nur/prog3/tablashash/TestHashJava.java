package nur.prog3.tablashash;

import nur.prog3.listas.Perro;

import java.util.HashMap;

public class TestHashJava {
    public static void main(String[] args) {
        HashMap<String, Perro> tabla = new HashMap<>();

        Perro p = new Perro("PP001","Pepe","caniche");
        tabla.put(p.getIdentificador(), p);
        p = new Perro("PP002","Lau","caniche");
        tabla.put(p.getIdentificador(), p);
        p = new Perro("PP003","Teo","caniche");
        tabla.put(p.getIdentificador(), p);
        p = new Perro("PP004","Rex","caniche");
        tabla.put(p.getIdentificador(), p);

        Perro otro = tabla.get("PP003");

        System.out.println(otro);
    }
}
