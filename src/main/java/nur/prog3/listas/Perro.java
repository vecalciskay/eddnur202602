package nur.prog3.listas;

public class Perro {
    private String identificador;
    private String nombre;
    private String raza;

    public Perro(String identificador, String nombre, String raza) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.raza = raza;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    @Override
    public String toString() {
        return "(" + identificador + ") " + nombre + " <" + raza + ">";
    }

    @Override
    public boolean equals(Object obj) {
        Perro p = (Perro)obj;
        if (this.identificador.equals(p.getIdentificador()))
            return true;
        return super.equals(obj);
    }
}
