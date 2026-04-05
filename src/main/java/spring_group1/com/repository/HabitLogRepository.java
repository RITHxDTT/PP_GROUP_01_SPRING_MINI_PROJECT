package spring_group1.com.repository;

import org.apache.ibatis.annotations.*;
import spring_group1.com.model.HabitLog;
import spring_group1.com.model.request.HabitLogRequest;
import spring_group1.com.model.response.HabitLogResponse;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface HabitLogRepository {
    @Results(id = "habitLogMap", value = {
            @Result(property = "habitLogId", column = "habit_log_id"),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habitId", column = "habit_id")
    })
    //Get by ID
    @Select("""
    SELECT * FROM habit_logs 
             WHERE habit_id = #{habitId}
    LIMIT #{size}
    OFFSET(#{page}-1) * #{size}
    """)
    List<HabitLogResponse> findHabitLogById(Integer habitId , Integer page, Integer size);

    //Insert
    @Insert("INSERT INTO habit_logs (status, habit_id, log_date, xp_earned) " +
            "VALUES (#{req.status}, #{req.habitId}, #{req.logDate}, #{req.xpEarned})")
    @Options(useGeneratedKeys = true, keyProperty = "req.habitLogId", keyColumn = "habit_log_id")
    void insert(@Param("req") HabitLogRequest request);
}
