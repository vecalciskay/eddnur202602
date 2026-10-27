package nur.prog3.arbolesbinarios;

public class OperacionDivision extends Operacion {
    public OperacionDivision() {
        simbolo = "/";
    }


    @Override
    public Numero calcular(Numero izq, Numero der) {
        if (der.getValor() == 0.0f) {
            throw new ArithmeticException("No se puede dividir entre 0");
        }
        return new Numero(izq.getValor() / der.getValor());
    }
}
