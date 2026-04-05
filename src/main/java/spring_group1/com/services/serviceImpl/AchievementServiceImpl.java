package spring_group1.com.services.serviceImpl;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_group1.com.model.Achievements;
import spring_group1.com.repository.AchievementRepository;
import spring_group1.com.services.AchievementService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    @Override
    public List<Achievements> getAllAchievements(Integer page, Integer size) {
        return achievementRepository.getAchievements(page, size);
    }

    @Override
    public List<Achievements> getAllAchievementForUser(Integer userId, Integer page, Integer size) {
        return achievementRepository.getAllAchievementsForUser(userId, page, size);
    }
}
