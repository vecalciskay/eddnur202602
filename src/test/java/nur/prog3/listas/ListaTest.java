package nur.prog3.listas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListaTest {
    @Test
    public void tamanoListaVacia() {
        // Arrange
        Lista<String> lista = new Lista<>();

        // Act
        int resultado = lista.cantidad();

        // Assert
        int esperado = 0;
        assertEquals(esperado, resultado);
    }

    @Test
    public void tamanoLista3items() {
        // Arrange
        Lista<String> lista = new Lista<>();
        lista.insertar("Hugo");
        lista.insertar("Paco");
        lista.insertar("Luis");
        // Act
        int resultado = lista.cantidad();

        // Assert
        int esperado = 3;
        assertEquals(esperado, resultado);
    }

    @Test
    public void insertar3itemsTest() {
        // Arrange
        Lista<String> lista = new Lista<>();
        lista.insertar("Hugo");
        lista.insertar("Paco");
        lista.insertar("Luis");
        // Act
        String resultado = lista.toString();

        // Assert
        String esperado = "Luis->Paco->Hugo->";
        assertEquals(esperado, resultado);
    }

    @Test
    public void adicionar3itemsTest() {
        // Arrange
        Lista<String> lista = new Lista<>();
        lista.adicionar("Hugo");
        lista.adicionar("Paco");
        lista.adicionar("Luis");
        // Act
        String resultado = lista.toString();

        // Assert
        String esperado = "Hugo->Paco->Luis->";
        assertEquals(esperado, resultado);
    }
}
