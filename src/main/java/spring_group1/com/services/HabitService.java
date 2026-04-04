package spring_group1.com.services;

import spring_group1.com.model.Habit;
import spring_group1.com.model.request.HabitRequest;

import java.util.List;

public interface HabitService {


    List<Habit> getAllHabit(Integer page, Integer size);

    Habit getHabitById(Integer habitId);

    Habit createhabit(HabitRequest habitRequest);

    Habit updateHabit(Integer habitId, HabitRequest habitRequest);

    boolean deleteHabit(Integer habitId);

}
