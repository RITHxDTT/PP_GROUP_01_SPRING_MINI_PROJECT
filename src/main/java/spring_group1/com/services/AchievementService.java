package spring_group1.com.services;

import org.springframework.stereotype.Service;
import spring_group1.com.model.Achievements;

import java.util.List;



public interface AchievementService {
    List<Achievements> getAllAchievements(Integer page, Integer size);

    List<Achievements> getAllAchievementForUser(Integer userId, Integer page, Integer size);
}
