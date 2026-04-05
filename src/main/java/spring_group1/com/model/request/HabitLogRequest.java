package spring_group1.com.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_group1.com.model.HabitLogStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitLogRequest {
    private Integer habitLogId;
    private LocalDate logDate;
    private Integer xpEarned;
    private HabitLogStatus status;
    private Integer habitId;
}
