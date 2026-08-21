package nur.prog3.hanoi.objetos;

public class Hanoi {
    private Columna[] columnas;

    public Hanoi(int n) {
        this.columnas = new Columna[3];
        this.columnas[0] = new Columna(n);
        this.columnas[1] = new Columna();
        this.columnas[2] = new Columna();
    }

    public void hacerHanoi(int de, int a, int pp, int n) {

    }
}
