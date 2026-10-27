package nur.prog3.arbolesbinarios;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Esta clase es exclusivamente para hacer calculos aritmeticos
 */
public class ArbolBinario {
    private static final Logger logger = LogManager.getRootLogger();

    private Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public ArbolBinario(String expr) {
        raiz = construirNodo(expr);
    }

    private Nodo construirNodo(String exprEspacios) {
        Nodo resultado = null;
        String expr = exprEspacios.trim();
        int numParentesis = 0;
        char c; // caracter actual que estamos leyendo, vamos uno por uno
        int posicion = 0; // posicion del caracter actual
        while(posicion < expr.length()) {
            c = expr.charAt(posicion);
            if (c == ' ') {
                logger.debug("Ignoro el espacio en la posicion " + posicion);
                posicion++;
                continue;
            }
            if (c == '(') {
                numParentesis++;
                logger.debug("Parentesis abierto, numPärentesis = " + numParentesis);
                posicion++;
                continue;
            }
            if (c == ')') {
                numParentesis--;
                logger.debug("Parentesis cerrado, numPärentesis = " + numParentesis);
                posicion++;
                continue;
            }

            if ((c == '+' || c == '-' || c == '*' || c == '/') &&
                numParentesis == 0) {
                resultado = new Nodo(Operacion.leer(c));
                Nodo izquierda = construirNodo(expr.substring(0, posicion));
                Nodo derecha = construirNodo(expr.substring(posicion + 1));
                resultado.setIzquierdo(izquierda);
                resultado.setDerecho(derecha);
                break;
            }

            // c es un numero entre 0 y 9
            // Leer un numero
            if (c >= 48 && c <= 57 && numParentesis == 0) {
                String numero = String.valueOf(c);
                int posNumero = posicion + 1;
                if (posNumero < expr.length())
                    c = expr.charAt(posNumero);
                while(c >= 48 && c <= 57 && posNumero < expr.length()) {
                    numero += String.valueOf(c);
                    posNumero++;
                }
                float numerof = Float.valueOf(numero);
                resultado = new Nodo(new Numero(numerof));
                logger.debug("En teoria hemos leido el numero: " + numero);
                posicion = posNumero;
                continue;
            }

            posicion++;
        }
        if (numParentesis == 0 && expr.startsWith("(") && expr.endsWith(")"))
            return construirNodo(expr.substring(1, expr.length() - 1));

        return resultado;
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
