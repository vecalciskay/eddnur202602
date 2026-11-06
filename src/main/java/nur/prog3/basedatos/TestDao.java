package nur.prog3.basedatos;

import com.mysql.cj.jdbc.MysqlDataSource;

public class TestDao {
    public static void main(String[] args) {
        // Esto solamente se hace una vez en la aplicación
        String url = "jdbc:mysql://localhost:3306/edd";
        String usuario = "root";
        String clave = "amigos123";
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(usuario);
        dataSource.setPassword(clave);
        // -----------------------------------------------

        PersonaDAO dao = new PersonaDaoJdbc(dataSource);
        dao.listarTodas().forEach(System.out::println);
    }
}
