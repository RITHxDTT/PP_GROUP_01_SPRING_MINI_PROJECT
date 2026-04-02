package spring_group1.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habit {
    private Integer habitId;
    private Integer appUserId;
    private String title;
    private String description;
    private String frequency;
    private Boolean isActive;


}
