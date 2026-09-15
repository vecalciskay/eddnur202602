package nur.prog3.listas;

public class TestListaSimple {
    public static void main(String[] args) {
        ListaSimple lista = new ListaSimple();
        lista.insertar("Hugo");
        lista.insertar("Paco");
        lista.insertar("Luis");
        System.out.println(lista);
    }
}
