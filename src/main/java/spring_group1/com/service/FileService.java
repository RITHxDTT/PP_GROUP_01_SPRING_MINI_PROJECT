package spring_group1.com.service;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile file);
    Resource getFileByFileName(String fileName);
}
