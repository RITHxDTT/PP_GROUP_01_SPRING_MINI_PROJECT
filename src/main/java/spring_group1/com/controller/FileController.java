package spring_group1.com.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring_group1.com.model.FileResponse;
import spring_group1.com.response.ApiRespone;
import spring_group1.com.services.FileService;

import java.io.IOException;
import java.net.URLConnection;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) throws IOException {
        String fileName = fileService.uploadFile(file);
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/files/view/" + fileName)
                .toUriString();
        FileResponse fileResponse = FileResponse.builder()
                .fileName(fileName)
                .fileType(file.getContentType())
                .fileUrl(fileUrl)
                .fileSize(file.getSize())
                .build();


        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiRespone.builder()
                        .success(true)
                        .message("File uploaded successfully to RustFS")
                        .status(HttpStatus.CREATED)
                        .payload(fileResponse)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @GetMapping(value="/view/{fileName}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<?> viewFileByName(@PathVariable String fileName) throws IOException {
        Resource resource = fileService.viewFileByFileName(fileName);
        String contentType = URLConnection.guessContentTypeFromName(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}

