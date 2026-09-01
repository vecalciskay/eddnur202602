package nur.prog3.imagenes;

/**
 * <ul>
 *     <li>Que es un punto de una imagen?
 *  Es un entero</li>
 *  <li>Que es un entero en Java?
 *  Es un campo en memoria que ocupa 4 bytes
 *  </li>
 *  <li>Cuantos bits tiene 1 byte?
 *  8 bits</li>
 *  <li>Cuantos bits tiene un entero en Java?
 *  32</li>
 * </ul>
 *
 * 4 bytes
 * 256 valores en el 1er byte - 256 2do - 256 3ero - 256 4to
 *
 * 0000 0000  0010 0001  1000 1111  1011 1000 = 2199480
 * 00 21 8F B8
 * 00  33  143  184
 * 33 * 256*256 + 143*256 + 184 = 2199480
 *
 * Tablas de Verdad
 * v | v = v
 * v | f = v
 * f | v = v
 * f | f = f
 *
 * v & v = v
 * v & f = f
 * f & v = f
 * f & f = f
 */
public class Imagen {
    private int ancho;
    private int alto;
    private int[][] puntos;

    public Imagen(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.puntos = new int[ancho][alto];
    }

    public void set(int x, int y, int color) {
        puntos[x][y] = color;
    }
    public void set(int x, int y, int r, int g, int b) {
        //puntos[x][y] = r * 256 *256 + g * 256 + b;
        puntos[x][y] = r << 16 | g << 8 | b;
    }

    public int get(int x, int y) {
        return puntos[x][y];
    }

    /**
     * Devuelve un arreglo donde
     * arreglo[0] = rojo
     * arreglo[1] = verde
     * arreglo[2] = azul
     * @param x
     * @param y
     * @return
     */
    public int[] getRgb(int x, int y) {
        int b = puntos[x][y] & 0x00000011;
        int g = (puntos[x][y] & 0x00001100) >> 8;
        int r = (puntos[x][y] & 0x00110000) >> 16;

        int[] color = new int[3];
        color[0] = r;
        color[1] = g;
        color[2] = b;

        return color;
    }
}
