package spring_group1.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class achievements {
    private String title;
    private String description;
    private Integer xp;
    private String bagde;
}
