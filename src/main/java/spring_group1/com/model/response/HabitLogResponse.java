package spring_group1.com.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_group1.com.model.HabitLogStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitLogResponse {

    private Integer habitLogId;
    private LocalDate logDate;
    private HabitLogStatus status;
    private Integer xpEarned;
    private Integer habitId;


}
