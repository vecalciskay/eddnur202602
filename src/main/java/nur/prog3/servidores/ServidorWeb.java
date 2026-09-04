package nur.prog3.servidores;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorWeb {
    private static final Logger logger = LogManager.getRootLogger();
    private final int puerto;
    private ServerSocket sckServer;

    public ServidorWeb(int p) {
        puerto = p;
    }

    public static void main(String[] args) {
        ServidorWeb srv = new ServidorWeb(2425);
        srv.comenzar();
    }

    private void comenzar() {
        try {
            logger.info("Crea el socket servidor para nuestro webserver");
            sckServer = new ServerSocket(puerto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            logger.info("Esperamos conexion de cliente");
            Socket clt = sckServer.accept();
            ProtocoloWeb protocolo = new ProtocoloWeb(clt);
            protocolo.manejarConexion();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
