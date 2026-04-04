package spring_group1.com.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
}

