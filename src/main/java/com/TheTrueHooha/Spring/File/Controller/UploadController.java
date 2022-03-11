package com.TheTrueHooha.Spring.File.Controller;

import com.TheTrueHooha.Spring.File.Model.UploadResponse;
import com.TheTrueHooha.Spring.File.Utils.UploadUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
public class UploadController {

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse>
    uploadFile(@RequestParam("file")MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        long size = multipartFile.getSize();

        String fileCode = UploadUtils.saveFile(fileName, multipartFile);

        UploadResponse response = new UploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadURL("/download/" + fileCode);

        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }
}
