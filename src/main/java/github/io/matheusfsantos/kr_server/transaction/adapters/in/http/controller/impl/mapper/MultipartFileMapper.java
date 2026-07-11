package github.io.matheusfsantos.kr_server.transaction.adapters.in.http.controller.impl.mapper;

import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.IllegalFileCastException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class MultipartFileMapper {
    private MultipartFileMapper() { }

    public static InputStream toDomain(MultipartFile file) throws IllegalFileCastException {
        try {
            log.info("{} - toDomain - message: extracting input stream from multipart file...", MultipartFileMapper.class.getSimpleName());
            return file.getInputStream();
        } catch (IOException e) {
            log.error("{} - toDomain - message: error extracting input stream from file, e.message: {}", MultipartFileMapper.class.getSimpleName(), e.getMessage());
            throw new IllegalFileCastException(String.format("Error processing uploaded file: %s", e.getMessage()));
        }
    }
}
