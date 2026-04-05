package spring_group1.com.repository;


import org.apache.ibatis.annotations.*;
import spring_group1.com.model.Achievements;


import java.util.List;


@Mapper
public interface AchievementRepository {


    @Results(id = "achievementMapper", value = {
            @Result(property = "achievementId", column = "achievement_id"),
            @Result(property = "xpRequired", column = "xp_required")
    })
    @Select("""
            SELECT * FROM achievements
            LIMIT #{size}
            OFFSET (#{page}-1) * #{size};
            """)
    List<Achievements> getAchievements(Integer page, Integer size);

    @ResultMap("achievementMapper")
    @Select("""
                SELECT a.*
                FROM achievements a
                INNER JOIN app_user_achievements ua
                ON a.achievement_id = ua.achievement_id
                WHERE ua.app_user_id = #{userId}
                LIMIT #{size}
                OFFSET (#{page}-1) * #{size}
            """)
    List<Achievements> getAllAchievementsForUser(Integer userId, Integer page, Integer size);

    @Select("""
                SELECT COUNT(*) > 0
                FROM app_user_achievements
                WHERE user_id = #{userId} AND achievement_id = #{achievementId}
            """)
    boolean existsUserAchievement(Integer userId, Integer achievementId);

    @Insert("""
                INSERT INTO app_user_achievements(user_id, achievement_id)
                VALUES(#{userId}, #{achievementId})
            """)
    void unlockAchievement(Integer userId, Integer achievementId);
}

