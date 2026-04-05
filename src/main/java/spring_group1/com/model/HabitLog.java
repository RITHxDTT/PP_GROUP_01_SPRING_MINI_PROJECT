package spring_group1.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLog {

    private UUID habitLogId;
    private LocalDate logDate;
    private HabitLogStatus status;
    private Integer xpEarned;
    private Integer habitId;

}
