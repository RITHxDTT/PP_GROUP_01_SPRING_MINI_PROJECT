package spring_group1.com.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitLogResponse {

    private UUID habitLogId;
    private LocalDate logDate;
    private HabitLogResponse status;
    private Integer xpEarned;
    private Integer habitId;


}
