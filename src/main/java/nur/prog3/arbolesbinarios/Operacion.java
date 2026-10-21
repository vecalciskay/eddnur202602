package nur.prog3.arbolesbinarios;

public abstract class Operacion extends  ElementoAritmetico{
    protected String simbolo;

    @Override
    public String toString() {
        return simbolo;
    }

    public abstract Numero calcular(Numero izq, Numero der) ;
}
