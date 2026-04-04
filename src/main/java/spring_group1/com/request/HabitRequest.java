package spring_group1.com.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitRequest {
    @NotBlank(message = "title cannot be blank or empty")
    private String title;
    @NotBlank(message = "description cannot be blank or empty")
    private String description;
    private String frequency;
    private Integer appUserId;
}
