package spring_group1.com.service;

import org.springframework.stereotype.Service;
import spring_group1.com.model.Achievements;

import java.util.List;



public interface AchievementService {
    List<Achievements> getAllAchievements();
}
