package spring_group1.com.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(MultipartFile file) throws IOException;
    Resource viewFileByFileName(String fileName) throws IOException;
}

