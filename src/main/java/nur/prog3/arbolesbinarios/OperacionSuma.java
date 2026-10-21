package nur.prog3.arbolesbinarios;

public class OperacionSuma extends Operacion {

    public OperacionSuma() {
        simbolo = "+";
    }

    public Numero calcular(Numero izq, Numero der) {
        return new Numero(izq.getValor() + der.getValor());
    }
}
