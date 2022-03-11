package com.TheTrueHooha.Spring.File.Utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadUtils {
    private Path pathFile;

    public Resource getFileResource(String fileCode) throws IOException {
        Path uploadPath = Paths.get("uploads");

        Files.list(uploadPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                pathFile = file;
                return;
            }
        });

        if (pathFile != null) {
            return new UrlResource(pathFile.toUri());
        }
        return null;
    }
}
