package nur.prog3.arbolesbinarios;

/**
 * Esta clase es exclusivamente para hacer calculos aritmeticos
 */
public class ArbolBinario {

    private Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public void insertar(ElementoAritmetico obj, LadoNodo donde  , Nodo padre) {
        if (padre == null) {
            raiz = new Nodo(obj);
            return;
        }

        if (donde == LadoNodo.Izquierda)
            padre.setIzquierdo(new Nodo(obj));
        if (donde == LadoNodo.Derecha)
            padre.setDerecho(new Nodo(obj));
    }

    @Override
    public String toString() {
        if (raiz == null)
            return "[VACIO]";
        String arbolEnString = raiz.toString();
        Numero resultado = calcularResultado();
        return arbolEnString + " = " + resultado.toString();
    }

    private Numero calcularResultado() {
        if (raiz == null)
            return new Numero(0);
        return raiz.calcularResultado();
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public static class Nodo {
        private ElementoAritmetico dato;
        private Nodo izquierdo;
        private Nodo derecho;

        public Nodo(ElementoAritmetico obj) {
            dato = obj;
            izquierdo = null;
            derecho = null;
        }

        public Nodo getIzquierdo() {
            return izquierdo;
        }

        public void setIzquierdo(Nodo izquierdo) {
            this.izquierdo = izquierdo;
        }

        public Nodo getDerecho() {
            return derecho;
        }

        public void setDerecho(Nodo derecho) {
            this.derecho = derecho;
        }

        public ElementoAritmetico getDato() {
            return dato;
        }

        @Override
        public String toString() {
            if (izquierdo == null)
                return dato.toString();
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(izquierdo.toString());
            sb.append(dato.toString());
            sb.append(derecho.toString());
            sb.append(")");

            return sb.toString();
        }

        public Numero calcularResultado() {
            if (dato instanceof  Numero)
                return (Numero)dato;
            Numero calculoIzquierda = izquierdo.calcularResultado();
            Numero calculoDerecha = derecho.calcularResultado();

            Operacion op = (Operacion) dato;
            return op.calcular(calculoIzquierda, calculoDerecha);
        }
    }
}
