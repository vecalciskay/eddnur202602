package nur.prog3.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Manzana {
    private static final Logger logger = LogManager.getRootLogger();
    public void comer() {
        // la manzana se puede comer
        logger.debug("Esta comiendo la manzana");

        logger.info("Se comio la manzana");

        // error
        logger.error("Error al comer la manzana");
    }
}
