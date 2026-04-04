package spring_group1.com.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.http.ResponseEntity;
import spring_group1.com.model.Habit;

import java.util.List;

@Mapper
public interface HabitRepository {


    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "appUserResponse", column = "app_user_id",
                one = @One(select = "spring_group1.com.repository.AppUserRepositoryspring_group1.com.repository.AppUserRepository.getAllRolesByUserId")
            )
    })
    @Select("""
    SELECT * FROM habits
    LIMIT #{size}
    OFFSET(#{page}-1) * #{size}
    """)
    List<Habit> findAllHabit(Integer page, Integer size);


    @ResultMap("habitMapper")
    @Select("""
    SELECT * FROM habits
    WHERE habit_id = #{habitId}
    """)
    Habit findHabitById(Integer habitId);
}
