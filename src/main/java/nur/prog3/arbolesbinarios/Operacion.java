package nur.prog3.arbolesbinarios;

public abstract class Operacion extends  ElementoAritmetico{
    protected String simbolo;

    public static ElementoAritmetico leer(char c) {
        switch(c) {
            case '+': return new OperacionSuma();
            case '-': return new OperacionSustraccion();
            case '*': return new OperacionMultiplicacion();
            case '/': return new OperacionDivision();
            default: return null;
        }
    }

    @Override
    public String toString() {
        return simbolo;
    }

    public abstract Numero calcular(Numero izq, Numero der) ;
}
