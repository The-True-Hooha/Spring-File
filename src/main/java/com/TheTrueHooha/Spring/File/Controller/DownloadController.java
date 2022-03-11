package com.TheTrueHooha.Spring.File.Controller;

import com.TheTrueHooha.Spring.File.Utils.DownloadUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

public class DownloadController {

    @GetMapping("/download/{fileCode}")
    public ResponseEntity<?> downloadFile  (@PathVariable("fileCode") String fileCode) {
        DownloadUtils downloadUtils = new DownloadUtils();

        Resource resource = null;

        try {
            resource = downloadUtils.getFileResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("file not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}
