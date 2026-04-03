package spring_group1.com.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;
import spring_group1.com.model.Habit;

import java.util.List;

@Mapper
public interface HabitRepository {


    @Select("SELECT * FROM habit")
    ResponseEntity<List<Habit>> findAllHabit(Integer page, Integer size);
}
