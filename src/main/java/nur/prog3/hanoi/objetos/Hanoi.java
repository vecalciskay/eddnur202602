package nur.prog3.hanoi.objetos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Hanoi {
    private Columna[] columnas;
    private PropertyChangeSupport observado;
    private boolean esperarDespuesDeMovimiento;
    public Hanoi(int n) {
        this.columnas = new Columna[3];
        this.columnas[0] = new Columna(n);
        this.columnas[1] = new Columna();
        this.columnas[2] = new Columna();
        observado = new PropertyChangeSupport(this);
        esperarDespuesDeMovimiento = false;
    }

    public void setEsperarDespuesDeMovimiento(boolean esperarDespuesDeMovimiento) {
        this.esperarDespuesDeMovimiento = esperarDespuesDeMovimiento;
    }

    public void addObserver(PropertyChangeListener observador) {
        observado.addPropertyChangeListener(observador);
    }

    public void hacerHanoi(int de, int a, int pp, int n) {
        if (n == 1) {
            // SOlamente mover el anillo
            Aro aro = this.columnas[de].sacar();
            this.columnas[a].insertar(aro);
            observado.firePropertyChange("HANOI",true, false);
            if (esperarDespuesDeMovimiento) {
                try {
                    Thread.currentThread().wait(200);
                } catch (InterruptedException e) {
                }
            }
            return;
        }
        hacerHanoi(de,pp,a,n-1);
        hacerHanoi(de,a,pp,1);
        hacerHanoi(pp,a,de,n-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(columnas[i].toString()).append("\n");
        }
        return sb.toString();
    }

    public Columna getColumna(int i) {
        return columnas[i];
    }
}
