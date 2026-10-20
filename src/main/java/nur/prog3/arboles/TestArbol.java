package nur.prog3.arboles;

public class TestArbol {
    public static void main(String[] args) {
        Arbol<String> arbol = new Arbol<>();



        arbol.insertar("M", "McPato", null);
        arbol.insertar("D", "Donald", "M");
        arbol.insertar("S", "Daisy", "M");
        arbol.insertar("H", "Hugo", "D");
        arbol.insertar("P", "Paco", "D");
        arbol.insertar("L", "Luis", "D");

        arbol.insertar("N", "Susana", "S");

        arbol.insertar("P1", "Noe","P");
        arbol.insertar("P2", "Noelia","P");

        System.out.println(arbol);

    }
}
