package spring_group1.com.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiRespone <T> {
    private String title;
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;
    private Boolean success;
    private T payload;
//    private LocalDateTime createdAt;
}
