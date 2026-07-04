package github.io.matheusfsantos.kr_server.transaction.application.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputStreamUtils {
    private static final Logger logger = Logger.getLogger(InputStreamUtils.class.getSimpleName());

    private InputStreamUtils() { }

    public static String getBase64(InputStream file) {
        try {
            InputStreamUtils.logger.log(Level.INFO, "{0} - getBase64 - message: encrypting transaction file...", new Object[]{ InputStreamUtils.class.getSimpleName() });
            return Base64.getEncoder().encodeToString(file.readAllBytes());
        } catch (IOException e) {
            InputStreamUtils.logger.log(Level.SEVERE, "{0} - getBase64 - message: error encrypting transaction file, e.message: {1}", new Object[]{ InputStreamUtils.class.getSimpleName(), e.getMessage() });
            return null;
        }
    }
}
