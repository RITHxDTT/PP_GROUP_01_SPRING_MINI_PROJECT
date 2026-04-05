package spring_group1.com.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class FileResponse {
        private String fileName;
        private String fileType;
        private String fileUrl;
        private long fileSize;

    }

