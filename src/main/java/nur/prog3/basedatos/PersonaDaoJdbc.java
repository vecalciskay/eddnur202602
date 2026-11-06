package nur.prog3.basedatos;

import nur.prog3.listas.ListaDoble;

import javax.sql.DataSource;
import java.sql.*;

public class PersonaDaoJdbc implements PersonaDAO {

    private final DataSource dataSource; // o un proveedor de conexiones

    public PersonaDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ListaDoble<Persona> listarTodas() {
        ListaDoble<Persona> personas = new ListaDoble<>();
        String sql = "SELECT id, altura, nombre FROM personas";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                personas.insertar(new Persona(rs.getInt("id"), rs.getFloat("altura"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar personas", e);
        }
        return personas;
    }

    @Override
    public Persona buscarPorId(int id) {
        String sql = "SELECT id, altura, nombre FROM personas WHERE id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la persona con id " + id, e);
        }
    }

    private Persona mapear(ResultSet rs) throws SQLException {
        return new Persona(
                rs.getInt("id"),
                rs.getFloat("altura"),
                rs.getString("nombre"));
    }


    @Override
    public Persona guardar(Persona persona) {
        if (persona.getId() == 0) {
            return insertar(persona.getAltura(), persona.getNombre());
        } else {
            actualizar(persona);
            return persona;
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM personas WHERE id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la persona con id " + id, e);
        }
    }

    // ---------- Métodos auxiliares privados ----------

    private Persona insertar(float altura, String nombre) {
        String sql = "INSERT INTO personas (altura, nombre) VALUES (?, ?)";
        Persona resultado = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setFloat(1, altura);
            ps.setString(2, nombre);
            ps.executeUpdate();

            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()) {
                    resultado = new Persona(claves.getInt(1), altura, nombre);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la persona", e);
        }

        return resultado;
    }

    private void actualizar(Persona persona) {
        String sql = "UPDATE personas SET altura = ?, nombre = ? WHERE id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setFloat(1, persona.getAltura());
            ps.setString(2, persona.getNombre());
            ps.setInt(3, persona.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la persona con id " + persona.getId(), e);
        }
    }
}
