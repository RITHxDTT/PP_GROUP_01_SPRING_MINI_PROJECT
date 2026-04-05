package spring_group1.com.repository;

import org.apache.ibatis.annotations.*;
import spring_group1.com.model.AppUser;

import java.time.Instant;
import java.util.List;

@Mapper
public interface AppUserRepository {

    @Results(id = "userMapper", value = {
            @Result(property = "userId", column = "app_user_id"),
            @Result(property = "profileImg", column = "profile_image"),
            @Result(property = "userName", column = "username"),
            @Result(property = "isVerified", column = "is_verified")


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
                SELECT role_name FROM roles r
                INNER JOIN user_role ur 
                ON r.role_id = ur.role_id
                WHERE user_id = #{userId}
            """)
    List<String> getAllRolesByUserId(Integer userId);

    @ResultMap("userMapper")
    @Select("""
            insert into app_users(username, email, password, profile_image)
            values(#{req.userName}, #{req.email}, #{req.password}, #{req.profileImg}) returning*
            """)
    AppUser createAppUser(@Param("req") AppUser appUserRequest);


    // verify user
    @Update("""
                UPDATE app_users
                SET is_verified = true
                WHERE email = #{email}
            """)
    void updateUserVerification(String email);
}
