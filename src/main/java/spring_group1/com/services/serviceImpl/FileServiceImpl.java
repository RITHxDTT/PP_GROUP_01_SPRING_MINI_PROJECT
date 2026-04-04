package spring_group1.com.services.serviceImpl;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import spring_group1.com.services.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    Path path = Paths.get("src/main/resources/files");

    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        if(!Files.exists(path)){
            Files.createDirectories(path);
        }

        String originalFileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(originalFileName);

        Files.copy(file.getInputStream(), path.resolve(newFileName));
        return newFileName;
    }

    @Override
    public Resource getFileByFileName(String fileName) throws IOException {

        Path filePath = Paths.get("scr/main/resources/files" + fileName);
        Resource resource = new InputStreamResource(Files.newInputStream(filePath));
        return resource;
    }
}
