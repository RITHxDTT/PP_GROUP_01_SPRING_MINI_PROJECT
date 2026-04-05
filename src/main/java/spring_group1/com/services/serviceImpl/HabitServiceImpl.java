package spring_group1.com.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import spring_group1.com.exception.NotFoundExceptionHandler;
import spring_group1.com.model.AppUser;
import spring_group1.com.model.Habit;
import spring_group1.com.model.request.HabitRequest;
import spring_group1.com.repository.HabitRepository;
import spring_group1.com.services.HabitService;

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
    public Habit createhabit(HabitRequest habitRequest) {
        AppUser getCurrentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Integer getCurrentId = getCurrentUser.getUserId();

        return habitRepository.createNewHabit(habitRequest, getCurrentId);
    }


    @Override
    public Habit updateHabit(Integer habitID, HabitRequest habitRequest ) {
        AppUser getCurrentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Integer currentUserId = getCurrentUser.getUserId();

        Habit existingHabit = habitRepository.findHabitById(habitID);


        if (existingHabit == null) {
            throw new NotFoundExceptionHandler("Habit not found!");
        }
        if (existingHabit.getAppUserId() == null || !existingHabit.getAppUserId().equals(currentUserId)) {
            throw new NotFoundExceptionHandler("You don't have permission to update this habit!");
        }

        return habitRepository.updateNewHabit(currentUserId, habitRequest, habitID);
    }

    @Override
    public Habit deleteHabit(Integer habitId) {
        AppUser getCurrentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Integer currentUserId = getCurrentUser.getUserId();

        Habit existingHabit = habitRepository.findHabitById(habitId);

        if (existingHabit == null) {
            throw new NotFoundExceptionHandler("Habit not found!");
        }


        if (existingHabit.getAppUserId() == null || !existingHabit.getAppUserId().equals(currentUserId)) {
            throw new NotFoundExceptionHandler("You don't have permission to delete this habit!");
        }

        habitRepository.deleteHabitById(habitId, currentUserId);
        return existingHabit;
    }
}
