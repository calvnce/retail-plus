package inventory.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogManager {
    private static final Logger LOGGER = Logger.getLogger(CustomLogManager.class.getName());

    static  {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomFormatter());
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(handler);
    }

    public static void Log( String message) {
        LOGGER.log(Level.INFO, message);
    }

    public static void LogError( String message) {
        LOGGER.log(Level.SEVERE, message);
    }
}
