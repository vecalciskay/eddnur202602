package nur.prog3.arboles;

import nur.prog3.listas.ListaDoble;

import java.awt.*;

public class DibujoArbol {

    private static final int ESPACIO_HORIZONTAL = 20;
    private static final int ESPACIO_VERTICAL = 50;
    private Arbol<String> modelo;
    public DibujoArbol(Arbol<String> modelo) {
        this.modelo = modelo;
    }

    public void dibujar(Graphics g, int x, int y) {
        Arbol.Nodo raiz = modelo.getRaiz();
        dibujarNodo(g, raiz, x, y);
    }

    private void dibujarNodo(Graphics g, Arbol.Nodo nodo, int x, int y) {
        if (nodo.getHijos().cantidad() == 0) {
            dibujarNodoSuelto(g, nodo, x, y);
            return;
        }

        // Si tiene hijos
        int ancho = calcularAnchoRectanguloSubArbol(g, nodo);
        int anchoLetras = calcularAnchoLetras(g, nodo);

        int yHijo = y + ESPACIO_VERTICAL;
        int xHijo = x;
        ListaDoble<Arbol.Nodo> lista = nodo.getHijos();
        for(Arbol.Nodo hijo : lista) {
            int anchoHijo = calcularAnchoRectanguloSubArbol(g, hijo);

            g.drawLine(x + ancho/2, y, xHijo + anchoHijo /2, yHijo);

            dibujarNodo(g, hijo, xHijo, yHijo);
            xHijo = xHijo + anchoHijo + ESPACIO_HORIZONTAL;
        }

        dibujarNodoSuelto(g, nodo, x + ancho/2 - anchoLetras/2 - 4, y);
    }

    private int calcularAnchoLetras(Graphics g, Arbol.Nodo nodo) {
        Font f = new Font("Arial", Font.PLAIN, 14);
        g.setFont(f);
        int width = g.getFontMetrics().stringWidth(nodo.getDato().toString());
        return width;
    }

    private void dibujarNodoSuelto(Graphics g, Arbol.Nodo nodo, int x, int y) {
        Font f = new Font("Arial", Font.PLAIN, 14);
        g.setFont(f);
        int width = g.getFontMetrics().stringWidth(nodo.getDato().toString());

        g.setColor(Color.white);
        g.fillRoundRect(x, y, width + 8, 18, 5, 5);
        g.setColor(Color.black);
        g.drawRoundRect(x, y, width + 8, 18, 5, 5);
        g.drawString(nodo.getDato().toString(), x + 4, y + 15);
    }

    private int calcularAnchoRectanguloSubArbol(Graphics g, Arbol.Nodo nodo) {
        if (nodo.getHijos().cantidad() == 0) {
            Font f = new Font("Arial", Font.PLAIN, 14);
            g.setFont(f);
            int width = g.getFontMetrics().stringWidth(nodo.getDato().toString());

            return width + 8;
        }

        // Si tenemos hijos
        int anchoTotal = 0;
        int separacion = 0;
        ListaDoble<Arbol.Nodo> lista = nodo.getHijos();
        for(Arbol.Nodo hijo : lista) {
            int anchoHijo = calcularAnchoRectanguloSubArbol(g, hijo);
            anchoTotal = anchoTotal + separacion + anchoHijo;
            separacion = ESPACIO_HORIZONTAL;
        }
        return anchoTotal;
    }
}
