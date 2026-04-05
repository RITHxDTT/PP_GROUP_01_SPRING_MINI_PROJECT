package spring_group1.com.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_group1.com.exception.DuplicateName;
import spring_group1.com.exception.NotFoundExceptionHandler;
import spring_group1.com.model.AppUser;
import spring_group1.com.model.Habit;
import spring_group1.com.repository.AppUserRepository;
import spring_group1.com.repository.HabitRepository;
import spring_group1.com.request.HabitRequest;
import spring_group1.com.services.AchievementService;
import spring_group1.com.services.HabitService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final AppUserRepository appUserRepository;
    private final AchievementService achievementService;

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

        Habit habit = habitRepository.createNewHabit(habitRequest);

        if (habitRequest.getAppUserId() != null) {
            completeHabit(habitRequest.getAppUserId());
        }

        return habit;
    }

    @Override
    public Habit updateHabit(Integer habitId, HabitRequest habitRequest) {
        if (habitRepository.findHabitById(habitId) == null) {
            throw new NotFoundExceptionHandler("Not found Habit ID "+ habitId);
        }
        List<Habit> habits = habitRepository.getAllHabit();

        for(Habit hab : habits){
            if(habitRequest.getTitle() != null){
                throw new DuplicateName("This habit already has!");
            }
        }
        return habitRepository.updateNewHabit(habitId, habitRequest);
    }

    @Override
    public boolean deleteHabit(Integer habitId) {

        if (habitRepository.findHabitById(habitId) == null) {
            throw new NotFoundExceptionHandler("Not found Habit ID "+ habitId);
        }

        int rowEffect = habitRepository.deleteHabitById(habitId);
        return rowEffect > 0;
    }

    @Override
    public void completeHabit(Integer userId) {

        AppUser user = appUserRepository.findUserById(userId);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        int newXp = user.getXp() + 10;
        user.setXp(newXp);

        int level = newXp / 100;
        user.setLevel(level);

        appUserRepository.updateXpAndLevel(user);

        achievementService.checkAndUnlock(user);
    }
}
