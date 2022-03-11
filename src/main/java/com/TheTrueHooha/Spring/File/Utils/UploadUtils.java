package com.TheTrueHooha.Spring.File.Utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadUtils {

    public static  String saveFile(String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get("upload-file");

        String fileCode = RandomStringUtils.randomAlphanumeric(10);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioException) {
            throw new IOException("error, cannot save file at the moment " + fileName, ioException);
        }
        return fileCode;
    }

}
