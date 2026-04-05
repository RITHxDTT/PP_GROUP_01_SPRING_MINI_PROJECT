package spring_group1.com.repository;

import org.apache.ibatis.annotations.*;
import spring_group1.com.model.HabitLog;
import java.util.List;

@Mapper
public interface HabitLogRepository {
    @Results(id = "habitLogMap", value = {
            @Result(property = "habitLogId", column = "habit_log_id"),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habitId", column = "habit_id")
    })
    @Insert("""
        INSERT INTO habit_logs ( log_date, status, xp_earned, habit_id)
        VALUES (now(), #{log.status}, xp_earned, #{log.habitId})
    """)
    void saveHabitLog(@Param("log") HabitLog habitLog);

    @ResultMap("habitLogMap")
    @Select("SELECT * FROM habit_logs WHERE habit_id = #{habitId}")
    List<HabitLog> selectByHabitId(Integer habitId);
}
