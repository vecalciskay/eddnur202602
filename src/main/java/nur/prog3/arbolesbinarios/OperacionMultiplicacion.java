package nur.prog3.arbolesbinarios;

public class OperacionMultiplicacion extends Operacion {
    public OperacionMultiplicacion() {
        simbolo = "*";
    }


    @Override
    public Numero calcular(Numero izq, Numero der) {

        return new Numero(izq.getValor() * der.getValor());
    }
}
