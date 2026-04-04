package spring_group1.com.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_group1.com.model.Habit;
import spring_group1.com.repository.HabitRepository;
import spring_group1.com.request.HabitRequest;
import spring_group1.com.services.HabitService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Override
    public List<Habit> getAllHabit(Integer page, Integer size) {
        return habitRepository.findAllHabit(page, size);
    }

    @Override
    public Habit getHabitById(Integer habitId) {
        return habitRepository.findHabitById(habitId);
    }

    @Override
    public Habit createhabit(HabitRequest habitRequest) {
        return habitRepository.createNewHabit(habitRequest);
    }

    @Override
    public Habit updateHabit(Integer habitId, HabitRequest habitRequest) {
        return habitRepository.updateNewHabit(habitId, habitRequest);
    }

    @Override
    public boolean deleteHabit(Integer habitId) {
        int rowEffect = habitRepository.deleteHabitById(habitId);
        return rowEffect > 0;
    }
}
