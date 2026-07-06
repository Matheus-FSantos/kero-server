package github.io.matheusfsantos.kr_server.transaction.application.core.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponsesUtils {
    private static final Logger logger = Logger.getLogger(ResponsesUtils.class.getSimpleName());

    private ResponsesUtils() { }

    public static String cleanResponse(String raw) {
        if(raw.contains("```")) {
            ResponsesUtils.logger.log(Level.INFO, "{0} - get - message: markdown code blocks detected, cleaning string...", ResponsesUtils.class.getSimpleName());
            return raw.replaceAll("```json", "").replaceAll("```", "").trim();
        }

        return raw;
    }
}
