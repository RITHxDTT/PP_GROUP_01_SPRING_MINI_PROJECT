package spring_group1.com.repository;


import org.apache.ibatis.annotations.*;
import spring_group1.com.model.Achievements;


import java.util.List;




@Mapper
public interface AchievementRepository {




    @Results(id = "achievementMapper", value = {
            @Result(property = "achievementId", column = "achievement_id"),
            @Result(property = "xpRequired", column ="xp_required" )
    })
    @Select("""
           SELECT * FROM achievements
           LIMIT #{size}
           OFFSET (#{page}-1) * #{size};
           """)
    List<Achievements> getAchievements(Integer page, Integer size);


    @ResultMap( "achievementMapper")


    
    List<Achievements> getAllAchievementsForUser(Integer page, Integer size);
}

