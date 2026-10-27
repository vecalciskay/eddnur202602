package nur.prog3.arbolesbinarios;

public class OperacionSustraccion extends Operacion {
    public OperacionSustraccion() {
        simbolo = "-";
    }

    public Numero calcular(Numero izq, Numero der) {

        return new Numero(izq.getValor() - der.getValor());
    }
}
