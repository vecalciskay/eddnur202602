package nur.prog3.arboles;

import javax.swing.*;
import java.awt.*;

public class FrameArbol extends JFrame {
    private final PanelArbol panel;
    private Arbol<String> modelo;
    public FrameArbol() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new BorderLayout());

        modelo = armarArbol();

        panel = new PanelArbol(modelo);
        this.getContentPane().add(panel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    private Arbol<String> armarArbol() {
        Arbol<String> arbol = new Arbol<>();

        arbol.insertar("M", "McPato", null);
        arbol.insertar("D", "Donald", "M");
        arbol.insertar("S", "Daisy", "M");
        arbol.insertar("H", "Hugo", "D");
        arbol.insertar("P", "Paco", "D");
        arbol.insertar("L", "Luis", "D");

        arbol.insertar("N", "Susana", "S");

        return arbol;
    }

    public static void main(String[] args) {
        new FrameArbol();
    }
}
