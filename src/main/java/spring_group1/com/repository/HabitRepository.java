package spring_group1.com.repository;

import org.apache.ibatis.annotations.*;
import spring_group1.com.model.Habit;
import spring_group1.com.model.request.HabitRequest;

import java.util.List;

@Mapper
public interface HabitRepository {


    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "appUserId", column = "app_user_id"),
            @Result(property = "appUserResponse", column = "app_user_id",
                    one = @One(select = "spring_group1.com.repository.AppUserRepository.getUserId")
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
    VALUES (#{req.title}, #{req.description},#{req.frequency}, #{userID})
        RETURNING *
   """)
    Habit createNewHabit(@Param("req") HabitRequest habitRequest, @Param("userID" ) Integer userID);

    @ResultMap("habitMapper")
    @Select("""
    UPDATE habits
    SET title = #{req.title}, description = #{req.description}, frequency = #{req.frequency}
    WHERE habit_id = #{hid} and app_user_id = #{userId} returning * 
   """)
    Habit updateNewHabit(@Param("userId") Integer userId,@Param("req") HabitRequest habitRequest, @Param("hid") Integer habitId );

    @ResultMap("habitMapper")
    @Select("""
    DELETE FROM habits
    WHERE habit_id = #{habitId} AND app_user_id = #{userId} 
""")
    void deleteHabitById(@Param("habitId") Integer habitId, @Param("userId") Integer userId);

    @ResultMap("habitMapper")
    @Select("""
    SELECT * FROM habits
    """)
    List<Habit> getAllHabit();
}
