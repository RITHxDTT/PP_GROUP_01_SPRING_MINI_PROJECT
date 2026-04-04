package spring_group1.com.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitRequest {
    private String title;
    private String description;
    private String frequency;
}
