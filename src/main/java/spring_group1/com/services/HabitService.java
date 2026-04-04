package spring_group1.com.services;

import org.springframework.http.ResponseEntity;
import spring_group1.com.model.Habit;

import java.util.List;

public interface HabitService {


    List<Habit> getAllHabit(Integer page, Integer size);
}
