package nur.prog3.listas;

public class TestListaPerro {

    public static void main(String[] args) {

        Lista<Perro> lista = new Lista<>();

        lista.insertar(new Perro("O1","Boki","Labrador"));
        lista.insertar(new Perro("AD4","Puka","Border collie"));
        lista.insertar(new Perro("T67","Chicho","Chihuahua"));

        System.out.println(lista);

        Perro p = new Perro("AD4","","");
        Perro encontrado = lista.buscar(p);
        if (encontrado == null) {
            System.out.println("Nada");
        } else {
            System.out.println(encontrado);
        }
    }
}
