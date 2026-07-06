package github.io.matheusfsantos.kr_server.transaction.application.core.utils;

import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.IllegalFileCastException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputStreamUtils {
    private static final Logger logger = Logger.getLogger(InputStreamUtils.class.getSimpleName());

    private InputStreamUtils() { }

    public static String getBase64(InputStream file) throws IllegalFileCastException {
        InputStreamUtils.logger.log(Level.INFO, "{0} - getBase64 - message: encrypting transaction file...", new Object[]{ InputStreamUtils.class.getSimpleName() });
        byte[] fileByte = InputStreamUtils.readAllByte(file);
        return Base64.getEncoder().encodeToString(fileByte);
    }

    private static byte[] readAllByte(InputStream file) throws IllegalFileCastException {
        try {
            return file.readAllBytes();
        } catch (IOException e) {
            InputStreamUtils.logger.log(Level.SEVERE, "{0} - getBase64 - message: error encrypting transaction file, e.message: {1}", new Object[]{ InputStreamUtils.class.getSimpleName(), e.getMessage() });
            throw new IllegalFileCastException(String.format("Error converting file: %s", e.getMessage()));
        }
    }
}
