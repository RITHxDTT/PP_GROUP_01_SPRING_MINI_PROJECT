package spring_group1.com.repository;

import org.apache.ibatis.annotations.*;
import spring_group1.com.model.Habit;
import spring_group1.com.request.HabitRequest;

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

    @ResultMap("habitMapper")
    @Select("""
    INSERT INTO habits (title, description, frequency, app_user_id)
    VALUES (#{title}, #{description},#{frequency}, #{appUserId})
        RETURNING *
   """)
    Habit createNewHabit(HabitRequest habitRequest);

    @ResultMap("habitMapper")
    @Select("""
    UPDATE habits
    SET title = #{title}, description = #{description}, frequency = #{frequency}
    WHERE habit_id = #{habitId}
   """)
    Habit updateNewHabit(Integer habitId, HabitRequest habitRequest);

    @ResultMap("habitMapper")
    @Select("""
    DELETE FROM habits
    WHERE habit_id = #{habitId}
    """)
    int deleteHabitById(Integer habitId);
}
