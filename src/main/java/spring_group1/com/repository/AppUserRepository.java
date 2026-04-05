package spring_group1.com.repository;

import org.apache.ibatis.annotations.*;
import spring_group1.com.model.AppUser;
import spring_group1.com.model.response.ProfileResponse;

import java.time.Instant;
import java.util.List;

@Mapper
public interface AppUserRepository {

    @Results(id = "userMapper", value = {
            @Result(property = "userId", column = "app_user_id"),
            @Result(property = "profileImg", column = "profile_image"),
<<<<<<< HEAD
            @Result(property = "userName", column = "username")
=======
            @Result(property = "userName", column = "username"),
            @Result(property = "isVerified", column = "is_verified")



>>>>>>> c46cdab (test)
    })
    @Select("""
        SELECT * FROM app_users
        WHERE email = #{email}
    """)
    AppUser findUserByEmail(String email);

    @ResultMap("userMapper")
    @Select("""
 SELECT * FROM app_users
        WHERE username = #{username}
""")
    AppUser findUserByUsername(String username);

    @ResultMap("userMapper")
    @Select("""
         SELECT * FROM app_users
       WHERE app_user_id = #{userId}
   """)
    AppUser getUserId (Integer userId);

    @ResultMap("userMapper")
    @Select("""
        insert into app_users(username, email, password, profile_image)
        values(#{req.userName}, #{req.email}, #{req.password}, #{req.profileImg}) returning*
        """)
    AppUser createAppUser(@Param("req") AppUser appUserRequest);


    @Select("""
        DELETE FROM app_users 
        WHERE email = #{email};
        """)
    ProfileResponse deleteProfile(String email);

    @Update("""
            UPDATE app_users 
            SET username = #{userName}, profile_image = #{profileImg}
            WHERE email = #{email}
            """)
    void updateProfile(AppUser user);
}
