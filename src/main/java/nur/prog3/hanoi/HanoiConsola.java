package nur.prog3.hanoi;

import nur.prog3.hanoi.objetos.Hanoi;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HanoiConsola implements PropertyChangeListener {

    private Hanoi hanoi;

    public static void main(String[] args) {
        HanoiConsola c =  new HanoiConsola();
        c.hacer();

    }

    private void hacer() {
        hanoi = new Hanoi(3);
        hanoi.addObserver(this);

        System.out.println("ANTES");
        System.out.println(hanoi);
        hanoi.hacerHanoi(0,2,1, 3);
        System.out.println("DESPUES");
        System.out.println(hanoi);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(hanoi);
    }
}
