package nur.prog3.basedatos;

import java.sql.*;

public class EjemploConexion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/edd";
        String usuario = "root";
        String clave = "amigos123";

        try (Connection conexion = DriverManager.getConnection(url, usuario, clave);
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery("SELECT id, altura, nombre FROM personas")) {

            while (resultado.next()) {
                int id = resultado.getInt("id");
                float altura = resultado.getFloat("altura");
                String nombre = resultado.getString("nombre");

                System.out.println("id: " + id + ", altura: " + altura + ", nombre: " + nombre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
