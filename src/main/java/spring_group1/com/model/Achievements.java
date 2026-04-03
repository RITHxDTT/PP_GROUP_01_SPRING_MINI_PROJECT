package spring_group1.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Achievements {
    private Integer achievementId;
    private String title;
    private String description;

    private String badge;
    private Integer xpRequired;







}
