package spring_group1.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    private Integer appUserId;
    private String username;
    private Integer xp;
    private Integer level;
    private String email;
    private String password;

    private String profileImg;
    private Boolean isVerified;
    private LocalDateTime timestamp;
}
