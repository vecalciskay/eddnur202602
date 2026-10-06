package nur.prog3.listas;

public class TestListaOrdenada {
    public static void main(String[] args) {
        String[] nombres = {
                "Ana", "Carlos", "Elena", "David", "Lucia",
                "Mateo", "Sofia", "Juan", "Valentina", "Pedro"
        };

        ListaDobleOrdenada<String> lista = new ListaDobleOrdenada<>();
        for (String nombre:nombres) {
            lista.insertar(nombre);
        }

        System.out.println(lista);
    }
}
