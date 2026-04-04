package spring_group1.com.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_group1.com.exception.DuplicateName;
import spring_group1.com.exception.NotFoundExceptionHandler;
import spring_group1.com.model.Habit;
import spring_group1.com.model.request.HabitRequest;
import spring_group1.com.repository.HabitRepository;
import spring_group1.com.services.HabitService;
import spring_group1.com.utils.SecurityUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Override
    public List<Habit> getAllHabit(Integer page, Integer size) {
        if(page <= 0 ){
            throw new NotFoundExceptionHandler("Page cannot negative or zero");
        }
        if(size <= 0) {
            throw new NotFoundExceptionHandler("Size cannot negative or zero");
        }
        return habitRepository.findAllHabit(page, size);
    }

    @Override
    public Habit getHabitById(Integer habitId) {
        if (habitRepository.findHabitById(habitId) == null) {
            throw new NotFoundExceptionHandler("Not found Habit ID "+ habitId);
        }else {
            return habitRepository.findHabitById(habitId);
        }
    }

    @Override
    public Habit createHabit(HabitRequest habitRequest) {
        // get current user
        int currentUserId = SecurityUtils.getCurrentUserId();
        // check dup
        List<Habit> habits = habitRepository.getAllHabit();
        for (Habit hab : habits) {
            // Only check duplicates for the CURRENT user
            if (hab.getAppUserResponse().getUserId() == currentUserId &&
                    hab.getTitle().equalsIgnoreCase(habitRequest.getTitle())) {
                throw new DuplicateName("You already have a habit named: " + habitRequest.getTitle());
            }
        }
        return habitRepository.createNewHabit(habitRequest, currentUserId);
    }

    public Habit updateHabit(Integer habitId, HabitRequest habitRequest) {
        Integer currentUserId = SecurityUtils.getCurrentUserId();  // Get user first

        // Check if habit exists AND belongs to current user
        Habit existingHabit = habitRepository.findHabitById(habitId);
        if (existingHabit == null || !existingHabit.getAppUserId().equals(currentUserId)) {
            throw new NotFoundExceptionHandler("Not found Habit ID "+ habitId);
        }

        // Check for duplicates only for THIS user (exclude current habit)
        List<Habit> userHabits = habitRepository.findAllHabitsByUserId(currentUserId);
        for(Habit hab : userHabits) {
            if(!hab.getHabitId().equals(habitId) &&  // Don't check the habit being updated
                    hab.getTitle().equalsIgnoreCase(habitRequest.getTitle())) {
                throw new DuplicateName("You already have a habit with this title!");
            }
        }

        return habitRepository.updateNewHabit(habitId, habitRequest, currentUserId);
    }

    public boolean deleteHabit(Integer habitId) {
        Integer currentUserId = SecurityUtils.getCurrentUserId();

        Habit habit = habitRepository.findHabitById(habitId);
        if (habit == null) {
            throw new NotFoundExceptionHandler("Not found Habit ID "+ habitId);
        }

        int rowEffect = habitRepository.deleteHabitById(habitId, currentUserId);
        return rowEffect > 0;
    }
}
