package spring_group1.com.services;

import org.springframework.stereotype.Service;
import spring_group1.com.model.Achievements;
import spring_group1.com.model.AppUser;

import java.util.List;


public interface AchievementService {
    List<Achievements> getAllAchievements(Integer page, Integer size);

    List<Achievements> getAllAchievementForUser(Integer userId, Integer page, Integer size);

    void checkAndUnlock(AppUser user);
}
