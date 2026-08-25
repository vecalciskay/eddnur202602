package nur.prog3.hanoi.objetos;

import java.util.Stack;

public class Columna {
    private Stack<Aro> aros;

    public Columna() {
        this.aros = new Stack<>();
    }

    /**
     * Este constructor hara que la coluna tenga n aros desde el principio. Los aros cambian de tamano.
     * @param n El numero de aros, (el tamano mas grande de los aros)
     */
    public Columna(int n) {
        this.aros = new Stack<>();
        for (int i = n; i >= 1; i--) {
            Aro a = new Aro(i);
            this.insertar(a);
        }
    }

    public void insertar(Aro a) {
        aros.push(a);
    }

    public Aro sacar() {
        return aros.pop();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|-");
        for (Aro a :aros) {
            sb.append(a).append("-");
        }
        return sb.toString();
    }

    public Stack<Aro> getAros() {
        return aros;
    }
}
