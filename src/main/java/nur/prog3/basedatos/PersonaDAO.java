package nur.prog3.basedatos;

import nur.prog3.listas.ListaDoble;

public interface PersonaDAO {
    ListaDoble<Persona> listarTodas();
    Persona buscarPorId(int id);
    Persona guardar(Persona persona);
    void eliminar(int id);
}
