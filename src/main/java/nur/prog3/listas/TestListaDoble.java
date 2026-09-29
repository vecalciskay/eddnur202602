package nur.prog3.listas;

public class TestListaDoble {
    public static void main(String[] args) {
        ListaDoble<String> lista = new ListaDoble<>();

        lista.insertar("Hugo");
        lista.insertar("Paco");
        lista.insertar("Luis");
        lista.insertar("Donald");
        lista.insertar("Daisy");

        System.out.println(lista);

        lista.eliminar(3);
        System.out.println(lista);
    }
}
