package nur.prog3.imagenes.objetos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
    private static final Logger logger = LogManager.getRootLogger();
    private static Configuracion instancia;
    private String carpetaImagenes;
    public static Configuracion getInstancia() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    /**
     * Crea el archivo de configuracion leyendo las propiedades en el archivo
     * resources/config.properties
     */
    private Configuracion() {
        Properties prop = new Properties();

        valoresPorDefecto();

        try (InputStream input = Configuracion.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                logger.warn("No se pudo encontrar el archivo de configuracion, tomando valores por defecto");
                return;
            }

            prop.load(input);

            String leeCarpetaImagenes = prop.getProperty("carpeta.imagenes");
            if (leeCarpetaImagenes != null)
                this.carpetaImagenes = leeCarpetaImagenes;
            logger.info("Carpeta Imagenes: " + carpetaImagenes);

        } catch (IOException ex) {
            logger.error("Error al leer el archivo de configuracion", ex);
        }
    }

    private void valoresPorDefecto() {
        carpetaImagenes = ".";
    }

    public String getCarpetaImagenes() {
        return carpetaImagenes;
    }
}
