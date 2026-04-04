package spring_group1.com.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spring_group1.com.model.Habit;
import spring_group1.com.repository.HabitRepository;
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
}
