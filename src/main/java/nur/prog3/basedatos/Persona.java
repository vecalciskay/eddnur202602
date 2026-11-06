package nur.prog3.basedatos;

public class Persona {
    private int id;
    private float altura;
    private String nombre;

    public Persona(int id, float altura, String nombre) {
        this.id = id;
        this.altura = altura;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public float getAltura() { return altura; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "Persona{id=" + id + ", altura=" + altura + ", nombre='" + nombre + "'}";
    }
}
