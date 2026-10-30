package nur.prog3.arbolesbinarios;

public class TestArbolBinario {
    public static void main(String[] args) {
        ArbolBinario a = new ArbolBinario();

        a.insertar(new OperacionSuma(), LadoNodo.Izquierda, null);
        ArbolBinario.Nodo op = a.getRaiz();
        a.insertar(new Numero(3.0f), LadoNodo.Izquierda, op);

        ArbolBinario.Nodo op2 = new ArbolBinario.Nodo(new OperacionMultiplicacion());
        op.setDerecho(op2);
        a.insertar(new Numero(5.0f), LadoNodo.Izquierda,  op2);
        a.insertar(new Numero(4.0f), LadoNodo.Derecha,  op2);

        /*
        ArbolBinario b = new ArbolBinario("(3.0+(5.0*4.0))");
        (3.0+(5.0*4.0))
        3.0+(5.0*4.0)
        (((4 - 6) + 5) * 7) / 4
         */

        System.out.println(a);

        //ArbolBinario b = new ArbolBinario("( 3   +   4)  ");
        //System.out.println(b);
        ArbolBinario b = new ArbolBinario("((4 - 1)*(6.45 + (35 / 2))) * 11");
        System.out.println(b);
    }
}
