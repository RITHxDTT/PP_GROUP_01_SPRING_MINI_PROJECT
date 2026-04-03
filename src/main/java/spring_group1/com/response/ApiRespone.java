package spring_group1.com.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiRespone <T> {
    private String title;
    private String message;
    private HttpStatus status;
    private LocalDate timestamp;
    private T payload;
}
