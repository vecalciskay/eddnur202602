package nur.prog3.arbolesbinarios;

public class Numero extends ElementoAritmetico {
    private float valor;

    public Numero(float n) {
        valor = n;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }

    public float getValor() {
        return valor;
    }
}
