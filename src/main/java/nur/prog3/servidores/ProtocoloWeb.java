package nur.prog3.servidores;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProtocoloWeb {
    private static final Logger logger = LogManager.getRootLogger();
    private final Socket sckCliente;

    public ProtocoloWeb(Socket clt) {
        this.sckCliente = clt;
    }

    public void manejarConexion() {

        try {
            InputStream inputStreamEntrada = sckCliente.getInputStream();
            OutputStream salida = sckCliente.getOutputStream();

            BufferedReader entrada = new BufferedReader(new InputStreamReader(inputStreamEntrada));


            String linea = entrada.readLine();
            logger.info("<<< " + linea);

            //responderHtml(salida);
            responderImagen(salida);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void responderImagen(OutputStream salida) throws IOException {
        String encabezdo = "HTTP/1.0 200 OK\n" +
                "Content-Type: image/png\n" +
                "Content-Length: ";

        String direccion = "E:/Prog3/eddnur202602/practicos/p2/ejemplo.png";
        logger.info("Busca imagen en: " + direccion);

        Path path = Paths.get(direccion);
        byte[] bytes = Files.readAllBytes(path);
        logger.info("Leyo " + bytes.length + " del archivo");
        String h1 = encabezdo + bytes.length + "\n\n";
        byte[] bytesEncabezado = h1.getBytes(StandardCharsets.UTF_8);
        salida.write(bytesEncabezado);
        salida.write(bytes);
        salida.flush();
    }

    private void responderHtml(OutputStream salida) throws IOException {
        String encabezdo = "HTTP/1.0 200 OK\n" +
                "Content-Type: text/html\n" +
                "Content-Length: ";

        String contenido = "<html>" +
                "<head></head>" +
                "<body>" +
                "<h1>Prog 3</h1>" +
                "<p>Hola</p>" +
                "</body>" +
                "</html>";

        String enviar = encabezdo + contenido.length() + "\n\n" + contenido;

        logger.info(">>> " + enviar);

        byte[] bytes = enviar.getBytes(StandardCharsets.UTF_8);
        salida.write(bytes);
        salida.flush();
    }
}
